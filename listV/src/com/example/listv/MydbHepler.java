package com.example.listv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.OpenableColumns;

public class MydbHepler extends SQLiteOpenHelper {

	public MydbHepler(Context context, String name) {
		super(context, name, null, 1);//version位初始版本
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists stutb(_id integer primary key autoincrement,number text not null)");
//		db.execSQL("insert into stutb(number) values('13712167969')");
//		db.execSQL("insert into stutb(number) values('11112223334')");
		db.execSQL("create table if not exists duanxin(_id integer primary key autoincrement,message text not null)");
		db.execSQL("insert into duanxin(message) values('同学，你的外卖到了，请到楼下来拿') ");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
