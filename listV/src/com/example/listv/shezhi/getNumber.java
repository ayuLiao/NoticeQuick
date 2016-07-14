package com.example.listv.shezhi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.listv.MydbHepler;
import com.example.listv.R;


public class getNumber extends Activity{
	
	Button button;
	SQLiteDatabase db;//数据库
	MydbHepler dbHepler;//数据库相关类
	Context context;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mytongxunluxml);
		this.context = this;
		
		button = (Button) findViewById(R.id.id_bt_tongxunlu);
		
		dbHepler = new MydbHepler(context, "stu.db");//SQLite数据库
		db = dbHepler.getWritableDatabase();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Cursor c = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
				if(c != null){
					while (c.moveToNext()){
						String phoneNumber = c.getString(c.getColumnIndex(Phone.NUMBER));
						ContentValues values = new ContentValues();
						values.put("number", phoneNumber);
						db.insert("stutb",null,values);
						values.clear();
					
					}
					Toast.makeText(context, "通讯录导入成功", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
	
	
//	public static void getNumber(Context context){
//		db = dbHepler.getWritableDatabase();
//		Cursor cursor = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
//		while (cursor.moveToNext()){
//			String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
//			ContentValues values = new ContentValues();
//			values.put("number", phoneNumber);
//			db.insert("stutb",null,values);
//			values.clear();
//			
//		}
//		
//	}
}
