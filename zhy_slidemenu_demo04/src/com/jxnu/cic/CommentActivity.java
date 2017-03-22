/*package com.jxnu.cic;





import com.jxnu.cic.dbSqlite.MyDatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CommentActivity extends Activity {
	MyDatabaseHelper mydbHelper;
	Button club_commit_comment;
	EditText club_comment;
	ListView comment_result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		club_comment=(EditText) findViewById(R.id.club_comment);
		club_commit_comment=(Button)findViewById(R.id.club_commit_comment);
		comment_result=(ListView)findViewById(R.id.comment_result);
		MyOnClickListerner myOnClickListerner = new MyOnClickListerner();
		club_commit_comment.setOnClickListener(myOnClickListerner);
		
		
	}
	private class MyOnClickListerner implements OnClickListener {
		public void onClick(View v) {
			mydbHelper = new MyDatabaseHelper(CommentActivity.this, "memento.db",
					null, 1);//创建数据库辅助类
			SQLiteDatabase db = mydbHelper.getReadableDatabase();//获取SQLite数据库
			//String subStr = subject.getText().toString();
			String bodyStr = club_comment.getText().toString();
			//String dateStr = date.getText().toString();
			switch (v.getId()) {
			case R.id.club_commit_comment:
				//title.setVisibility(View.INVISIBLE);
				addMemento(db, null, bodyStr, null);
				//Toast.makeText(CommentActivity.this, "添加备忘录成功！", 1000).show();
				comment_result.setAdapter(null);
			case R.id.comment_result:
				//title.setVisibility(View.VISIBLE);
				Log.d("debug",""+comment_result);
				Cursor cursor = queryMemento(db, null, bodyStr, null);
				SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
						CommentActivity.this, R.layout.result, cursor,
						new String[] { "body" },
						new int[] { R.id.memento_body});
				comment_result.setAdapter(resultAdapter);
				Log.d("debug",""+comment_result);
				break;
			default:
				break;
			}
		}
	}
	public void addMemento(SQLiteDatabase db, String subject, String body,
			String date) {
		db.execSQL("insert into memento_tb values(null,?,?,?)", new String[] {
				subject, body, date });
		//this.subject.setText("");
		this.club_comment.setText("");
		//this.date.setText("");
	}

	public Cursor queryMemento(SQLiteDatabase db, String subject, String body,
			String date) {
		Cursor cursor = db.rawQuery(
						"select * from memento_tb where subject like ? and body like ? and date like ?",
						new String[] { "%" + subject + "%", "%" + body + "%",
								"%" + date + "%" });
		return cursor;
	}
	protected void onDestroy() {//关闭数据库连接
		if(mydbHelper!=null){
			mydbHelper.close();
		}
	}


}
*/
package com.jxnu.cic;
import java.util.Calendar;

import com.jxnu.cic.dbSqlite.MyDatabaseHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity {
	private Button chooseDate, add, back;
	private EditText body;
    //private TextView name;
    private ImageView img;
	private ListView result;

	MyDatabaseHelper mydbHelper;
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		
		add = (Button) findViewById(R.id.club_commit_comment);
		back=(Button)findViewById(R.id.btn_comment_back);
		//name=(TextView)findViewById(R.id.memento_name);
	//query = (Button) findViewById(R.id.query);
		//date = (EditText) findViewById(R.id.date);
		//subject = (EditText) findViewById(R.id.subject);
		body = (EditText) findViewById(R.id.club_comment);
		result = (ListView) findViewById(R.id.comment_result);
		//title=(LinearLayout)findViewById(R.id.title);
		//title.setVisibility(View.INVISIBLE);
		/*chooseDate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();//获取当前日期
				new DatePickerDialog(MainActivity.this,//日期选择器对话框
						new DatePickerDialog.OnDateSetListener() {//日期改变监听器
							public void onDateSet(DatePicker view, int year,
									int month, int day) {
								date.setText(year + "-" + (month + 1) + "-"
										+ day);
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});*/
		MyOnClickListerner myOnClickListerner = new MyOnClickListerner();
		add.setOnClickListener(myOnClickListerner);
		back.setOnClickListener(myOnClickListerner);
		//query.setOnClickListener(myOnClickListerner);
	}

	private class MyOnClickListerner implements OnClickListener {
		public void onClick(View v) {

			mydbHelper = new MyDatabaseHelper(CommentActivity.this, "memento.db",
					null, 1);//创建数据库辅助类
			SQLiteDatabase db = mydbHelper.getReadableDatabase();//获取SQLite数据库
			//String subStr = subject.getText().toString();
			//String nameStr=  name.getText().toString();
			
			String bodyStr = body.getText().toString();
			
			//String dateStr = date.getText().toString();
			switch (v.getId()) {
			case R.id.club_commit_comment:
				//title.setVisibility(View.INVISIBLE);
				addMemento(db, null, bodyStr, null);
				Toast.makeText(CommentActivity.this, "评论成功！", 1000).show();
				result.setAdapter(null);
				Log.d("debug",""+result);
			//case R.id.query:
				//title.setVisibility(View.VISIBLE);
				Cursor cursor = queryMemento(db, null, bodyStr,null);
				SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
						CommentActivity.this, R.layout.result, cursor,
						new String[] { "body" },
						new int[] { 
								R.id.memento_body });
				result.setAdapter(resultAdapter);
				Log.d("debug",""+result);
				break;
			case R.id.btn_comment_back:
				finish();
			default:
				break;
			}
		}
	}

	public void addMemento(SQLiteDatabase db, String name, String body,
			String date) {
		db.execSQL("insert into memento_tb values(null,?,?,?)", new String[] {
				name, body});
	//this.name.setText("");
		this.body.setText("");
		//this.date.setText("");
	}

	public Cursor queryMemento(SQLiteDatabase db, String name, String body,
			String date) {
		Cursor cursor = db.rawQuery(
						"select * from memento_tb where body like? ",
						new String[] {"%" + body + "%"
								 });
		return cursor;
	}
	protected void onDestroy() {//关闭数据库连接
		 super.onDestroy();
		if(mydbHelper!=null){
			mydbHelper.close();
		}
	}
	
}
