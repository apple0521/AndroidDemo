	package com.jxnu.cic;
	
	
	
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	
	import android.os.Bundle;
	import android.os.Handler;
	import android.os.Message;
	import android.app.Activity;
	import android.app.Dialog;
	import android.content.Intent;
	import android.content.SharedPreferences;
	import android.database.sqlite.SQLiteDatabase;
	import android.view.Gravity;
	import android.view.Menu;
	import android.view.View;
	import android.view.ViewGroup;
	import android.view.Window;
	import android.view.WindowManager;
	import android.view.View.OnClickListener;
	import android.view.ViewGroup.LayoutParams;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.TextView;
	import android.widget.Toast;
	
	import com.google.gson.Gson;
	import com.jxnu.cic.R;
	import com.jxnu.cic.common.ConstApp;
	import com.jxnu.cic.dbSqlite.DBManager;
	import com.jxnu.cic.dbSqlite.DBSQLiteHelper;
	import com.jxnu.cic.entity.userlogin;
import com.jxnu.cic.network.HttpCommon;
	
	public class LoginActivity extends Activity{
	   
	   private DBManager dbm;
		
		private SQLiteDatabase db;
		private DBSQLiteHelper dbHelper;
		Button btn_login_dialog_positive;
		private Button btn_login;
		private EditText et_login_user,et_login_password;
		private TextView tv_login_forget,tv_login_register;
		
		SharedPreferences sp;
		SharedPreferences.Editor spEditor;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_login);
			
			//SharedPreferences缓存文件ulogin
			sp=getSharedPreferences("ulogin", MODE_WORLD_WRITEABLE);
			
			//调用DBManager类：数据库管理的常用方法
			dbm=new DBManager(this);
			
			//SQLite数据库的创建与初始化
			//dbHelper=new DBSQLiteHelper(this,"sqlTest.db",1);
			//db=dbHelper.getReadableDatabase();
			
			initDbData();
			
			//控件的声明与注册
			initWidget();
			//调用事件监听器
			addClickListener();
			
		}
	
		
	
		private void initWidget() {
			// TODO Auto-generated method stub
			btn_login=(Button) findViewById(R.id.btn_login);
			et_login_user=(EditText) this.findViewById(R.id.et_login_user);
			et_login_password=(EditText) this.findViewById(R.id.et_login_password);
			tv_login_forget=(TextView) this.findViewById(R.id.tv_login_forget);
			tv_login_register=(TextView) this.findViewById(R.id.tv_login_register);
		}
	
		private void initDbData() {
			// TODO Auto-generated method stub
		/*	ArrayList<userlogin> uloginArr=new ArrayList<userlogin>();
			userlogin u1=new userlogin("u1","123456",0,"");
			uloginArr.add(u1);
			uloginArr.add(new userlogin("u2","123456",0,""));
			
			dbm.add(uloginArr);*/
		}
		private void addClickListener() {
			// TODO Auto-generated method stub
			btnOnClickListener btncol=new btnOnClickListener();
			
			//给按钮控件绑定事件
			btn_login.setOnClickListener(btncol);
			tv_login_forget.setOnClickListener(btncol);		
			tv_login_register.setOnClickListener(btncol);
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			if (db != null && db.isOpen())
			{
				db.close();
			}
		}
	
		// 内部类
		private class btnOnClickListener implements OnClickListener{
			//public userlogin returnData = new userlogin();
			
			private void changeActivity(){
				Intent intent=new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				
			}
			
			Handler networkHandler=new Handler(){
				public void handleMessage(Message msg){
					if(msg.what==0x11){
						userlogin returnData=(userlogin) msg.obj;
						if(returnData==null){
							//Toast.makeText(getApplication(), "登录失败", 1000).show();
							showDialog();
					
							
						}else{
							Toast.makeText(getApplication(), "登录成功", 1000).show();
							
							//登录成功之后，把数据写入到SharedPreferences缓存文件ulogin中
							spEditor=sp.edit();//开启写入功能
							
							spEditor.putString("uname", returnData.getUser_name());
							spEditor.putString("upwd", returnData.getUser_psd());
							
							spEditor.putInt("loginNum", returnData.getLoginNUm());
							spEditor.putString("loginDatetime", returnData.getLoginDatetime());
							
							spEditor.commit();//提交数据
							
							/*同时，写入或更新SQLite数据库*/
							
							changeActivity();
							
						}
					}
				}
			};
			public void requestInternet(final userlogin transferUserlogin){
				
				new Thread(){
					public void run() {
						Gson postJson=new Gson();
						String strJson=postJson.toJson(transferUserlogin);
						String returnJSONData = HttpCommon.HttpInternet(ConstApp.HttpServerUrl+"/User_CheckForAndroid", strJson);
						System.out.println("从服务器获取数据："+returnJSONData);
						Gson getGson = new Gson();
						userlogin returnData=getGson.fromJson(returnJSONData,userlogin.class);				
						Message message=new Message();
						message.obj=returnData;
						message.what=0x11;
						networkHandler.sendMessage(message);
											
					}
				}.start();
	
			}
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.btn_login:
					
					String strUname=et_login_user.getText().toString().trim();
					String strPwd=et_login_password.getText().toString().trim();
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");       
					Date curDate = new Date(System.currentTimeMillis());//获取系统当前时间   
					//Date currentDate=new Date();
					String strDate = formatter.format(curDate);
					String getStrUsername=sp.getString("uname", "");
					final userlogin inputUser=new userlogin();
					inputUser.setUser_name(strUname);
					inputUser.setUser_psd(strPwd);
					checkInfo();
					if((getStrUsername==null) || (getStrUsername.equals(""))){
						//从SQLite数据库访问中进行登录验证
						
						if(dbm.checkLogin(inputUser)){
							System.out.println("---------------------------------");
							inputUser.setLoginDatetime(strDate);
							userlogin returnUdata=dbm.updateUserloginLog(inputUser);
							
							//登录成功之后，把数据写入到SharedPreferences缓存文件ulogin中
							spEditor=sp.edit();//开启写入功能
							
							spEditor.putString("uname", strUname);
							spEditor.putString("upwd", strPwd);
							spEditor.putInt("loginNum", returnUdata.getLoginNUm());
							spEditor.putString("loginDatetime", strDate);
							
							spEditor.commit();//提交数据
							
							changeActivity();
							
						}else{
							System.out.println("============================");
							//请求访问网络--服务器
							requestInternet(inputUser);
													
						}
					}else{
						
						//从SharedPreferences缓存文件ulogin中进行登录验证
						String getStrPassword=sp.getString("upwd", null);
						if(getStrUsername.equals(strUname) && getStrPassword.equals(strPwd)){
							Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
							changeActivity();
						}else{
							//请求访问网络--服务器
							requestInternet(inputUser);
							
						}
					}
					break;
				case R.id.tv_login_forget:
					Intent intent1=new Intent();
					intent1.setClass(LoginActivity.this, FindBackActivity.class);
					startActivity(intent1);
					
					break;
				case R.id.tv_login_register:
					
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this, RegisterActivity.class);
					startActivity(intent);
					
					break;
				default:
					break;
				}
				
			}		
			
		}
	
	/*	@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
		}*/
		public String checkInfo(){
			 if( et_login_user.getText().toString()==null|| et_login_user.getText().toString().equals("")){
				 return "用户名不能为空";   //对用户名进行验证
			 }
			 if ( et_login_password.getText().toString().trim().length()<6|| et_login_password.getText().toString().trim().length()>15){
				 return"密码位数应该在6~15之间";  //对密码进行验证
			 }
			
			 return null;
		}
		private void showDialog() {
			
			View view = getLayoutInflater().inflate(R.layout.login_dialog,
					null);
			final Dialog dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
			dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			Window window = dialog.getWindow();
			WindowManager.LayoutParams wl = window.getAttributes();
			wl.gravity=	Gravity.CENTER;
			wl.x = 0;
			wl.y =0;
			wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
			wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			dialog.onWindowAttributesChanged(wl);
			dialog.setCanceledOnTouchOutside(true);
			btn_login_dialog_positive=(Button) view.findViewById(R.id.btn_login_dialog_positive);
			btn_login_dialog_positive.setOnClickListener(new View.OnClickListener() {			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 dialog.cancel();
				}
			});
			dialog.show();
		}
		
	
	}
