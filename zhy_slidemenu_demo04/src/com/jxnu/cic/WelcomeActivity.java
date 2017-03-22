package com.jxnu.cic;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jxnu.cic.R;

public class WelcomeActivity extends Activity{
   Button btnpass;
   Button btnloginwelcome;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		btnpass=(Button) findViewById(R.id.btnpass);
		btnloginwelcome=(Button) findViewById(R.id.btnloginwelcome);
		btnloginwelcome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});
		
		//btnloginwelcome.setOnClickListener(this);
	}


	

}
