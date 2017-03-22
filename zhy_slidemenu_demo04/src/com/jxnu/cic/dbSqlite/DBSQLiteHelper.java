package com.jxnu.cic.dbSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLiteHelper extends SQLiteOpenHelper {
	//���Ĭ����ں����캯��
	
	//���캯���ĸ�����
	
	public DBSQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	//���캯���������
	public DBSQLiteHelper(Context context,String name,int version){
		//CursorFactory����Ϊnull,ʹ��Ĭ��ֵ
		super(context,name,null, version);
	}

	//ʵ�ַ���:��һ���ص�����
	//������ݿ��ʱ�������ݿ��ļ�������
	//��ݿ��һ�α�����ʱonCreate�ᱻ���� 
	//ֻ����һ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//������ݿ�����
		System.out.println("Creating Database......");
		//���콨�����
		String CreateTableSql="create table if not EXISTS userlogin("
					+"_id integer primary key autoincrement,"
					+"username varchar,"
					+"password varchar,"
					+"loginNum interger,"
					+"loginDatetime text)";
		db.execSQL(CreateTableSql);
		
		CreateTableSql="create table if not EXISTS userlog("
				+"_id integer primary key autoincrement,"
				+"username varchar,"
				+"userdo varchar,"
				+"userip varchar)";
				//+"logDatetime text)";
		db.execSQL(CreateTableSql);
		CreateTableSql="create table if not EXISTS register("
				+"register_id integer primary key autoincrement,"
				+"register_password varchar,"
				+"register_phone varchar,"
				+"register_schoolDate varchar,"
				+"register_academe varchar,"
				+"register_university varchar,"
				+"register_xueli varchar)";
				//+"logDatetime text)";
		db.execSQL(CreateTableSql);
	

		//��ʼ������ ContentValues
        ContentValues cv = new ContentValues();

        cv.put("username","uu");
        cv.put("password", "12345");
        //����id long��  ���ɹ�����-1
        //1-����
        //2-���е�Ĭ��ֵ
        //3-�ֶκ�ֵ��key/value����
        Long l = db.insert("userlogin", null, cv);
	}

	//����ݿ�
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	

}
