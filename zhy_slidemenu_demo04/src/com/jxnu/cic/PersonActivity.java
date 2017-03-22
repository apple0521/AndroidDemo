package com.jxnu.cic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonActivity extends Activity {
	private ImageView ivback;
	private TextView tvchange,tvname,tvsex;
	private SharedPreferences sharedPreferences;
	public static final String TEL_TXT="TEL_TXT",TEL_TXT1="TEL_TXT1";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person);
		ivback=(ImageView) this.findViewById(R.id.ivback);
		ivback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PersonActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		tvchange=(TextView) this.findViewById(R.id.tvchange);
		tvchange.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(PersonActivity.this,ChangeActivity.class);
				startActivity(intent);
				finish();
			}
		});
		tvname=(TextView) this.findViewById(R.id.tvname);
		tvsex=(TextView) this.findViewById(R.id.tvsex);
		Intent intent = this.getIntent();
        String tel_txt= intent.getStringExtra(ChangeActivity.TEL_TXT);
        String tel_txt1= intent.getStringExtra(ChangeActivity.TEL_TXT1);
        tvname.setText(tel_txt);
        tvsex.setText(tel_txt1);
	}
	
}
