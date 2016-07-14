package com.example.listv.shezhi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class mySQLite extends SQLiteOpenHelper{

	public mySQLite(Context context, String name) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists duanxin(_id integer primary key autoincrement,message text not null)");
		db.execSQL("insert into duanxin(message) values('同学，你的外卖到了，请到楼下来拿') ");
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
