package com.jxnu.cic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeActivity extends Activity{
	private ImageView ivchangeback;
	private TextView tvfinish;
	private EditText etname,etsex;
	public static final String TEL_TXT="TEL_TXT",TEL_TXT1="TEL_TXT1";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		etsex = (EditText) this.findViewById(R.id.etsex);
		etname = (EditText) this.findViewById(R.id.etname);
		etname.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
	
			}
		});
		ivchangeback=(ImageView) this.findViewById(R.id.ivchangeback);
		ivchangeback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ChangeActivity.this,PersonActivity.class);
				startActivity(intent);
				finish();
			}
		});
		tvfinish=(TextView) this.findViewById(R.id.tvfinish);
		tvfinish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChangeActivity.this,PersonActivity.class);
			
		        String tel_txt = etname.getText().toString();
		        String tel_txt1 = etsex.getText().toString();
		        intent.putExtra(TEL_TXT,tel_txt );
		        intent.putExtra(TEL_TXT1,tel_txt1 );
		        startActivity(intent);
			}
				
			});

	}

}
		
		/*
		  tv = (TextView) this.findViewById(R.id.textView);
        et = (EditText) this.findViewById(R.id.editText);
        Resources res = this.getResources();
        et.setOnKeyListener(new EditText.OnKeyListener() {
   
   @Override
   public boolean onKey(View v, int keyCode, KeyEvent event) {
    // TODO Auto-generated method stub
    tv.setText(et.getText());
    return false;
   }
  });
		 */
	


