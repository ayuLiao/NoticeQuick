package com.example.listv.shezhi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listv.MainActivity;
import com.example.listv.MydbHepler;
import com.example.listv.R;

public class tianjaduanxin extends Activity{
	
	
	Context context;
	TextView textView;
	Button button;
	EditText editText;
	MydbHepler dbHepler;//数据库相关类
	SQLiteDatabase db;//数据库

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tianjaduanxinxml);
		
		this.context = this;
		
		init();
		tianja();
		editText.addTextChangedListener(watcher);//editText监听事件
		button.setEnabled(false);
	}
	

	private void init() {
		textView = (TextView) findViewById(R.id.id_tj);
		button = (Button) findViewById(R.id.id_button);
		editText = (EditText) findViewById(R.id.id_ed_duanxin);
		dbHepler = new MydbHepler(context, "stu.db");//SQLite数据库
	}
	
	private void tianja() {
		db = dbHepler.getWritableDatabase();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("onclick", "按钮点击");
				String st = editText.getText().toString();
				ContentValues values = new ContentValues();
				values.put("message", st);
				db.insert("duanxin", null, values);
				values.clear();
				Toast.makeText(context, "新短信添加成功，请去菜单调用", Toast.LENGTH_LONG).show();
				
				//检验数据是否真的储存进去了
//				Cursor c = db.rawQuery("select * from duanxin where message=?", new String[]{st});
//				if(c != null){
//					String[] cl = c.getColumnNames();
//					while(c.moveToNext()){
//						for (String um : cl) {
//							String he = c.getString(c.getColumnIndex(um));
//							Toast.makeText(context, he, Toast.LENGTH_SHORT).show();
//						}
//					}
//				}
				
				
				
//				//如果这样写，一直都会报“短信已存在”
//				if(c != null){
//				Toast.makeText(context, "短信已存在", Toast.LENGTH_LONG).show();
//				}else{
//					ContentValues values = new ContentValues();
//					values.put("message", st);
//					db.insert("duanxin", null, values);
//					values.clear();
//					Toast.makeText(context, "新短信添加成功，请去菜单调用", Toast.LENGTH_LONG).show();
//				}
			}
		});
	}
	
	
	TextWatcher watcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			button.setEnabled(true);
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
