package com.jxnu.cic;

import com.google.gson.Gson;
import com.jxnu.cic.common.ConstApp;
import com.jxnu.cic.dbSqlite.DBManager;
import com.jxnu.cic.dbSqlite.DBSQLiteHelper;
import com.jxnu.cic.entity.register;
import com.jxnu.cic.entity.userlogin;
import com.jxnu.cic.network.HttpCommon;

import android.os.Bundle;
import android.os.Message;
import android.os.Handler;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
    TextView  tvfinish;
    Button register_back;
    EditText et_shoujihao,et_mima;

    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register2);
		tvfinish=(TextView) findViewById(R.id.tvfinish);
		et_shoujihao=(EditText) findViewById(R.id.et_shoujihao);
		et_mima=(EditText) findViewById(R.id.et_mima);
		register_back=(Button)findViewById(R.id.register_back);
		tvfinish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//db.execSQL("INSERT INTO userlogin(Null,?,?,?,?)", new Object[]{useregister.username,useregister.password});
				//register(useregister);
				System.out.println("+++++++++++++++");
				checkInfo();
				Intent intent=new Intent(RegisterActivity.this,CodeActivity.class);
				startActivity(intent);
			}
			
		});
		register_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	public String checkInfo(){
		 if(et_shoujihao.getText().toString()==null||et_shoujihao.getText().toString().equals("")){
			 return "用户名不能为空";   //对用户名进行验证
		 }
		 if (et_mima.getText().toString().trim().length()<6||et_mima.getText().toString().trim().length()>15){
			 return"密码位数应该在6~15之间";  //对密码进行验证
		 }
		
		 return null;
	}
 
	
	

}
