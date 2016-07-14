package com.example.listv;

import com.example.listv.shezhi.mydata;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class chooseduanxin extends Activity implements OnItemClickListener{
	//数据库相关变量
	SQLiteDatabase db;
	MydbHepler dbHepler;
	SimpleCursorAdapter adapter;
	
	Context context;
	Cursor d;
	ListView listView;
	
	TextView data;//item数据，短信内容
	String Data;//接收短信
	mydata mes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myshezhi);
		
		this.context = this;
		init();
		dbHepler = new MydbHepler(context, "stu.db");
		findDuanXin();
		listView.setOnItemClickListener(this);
	}
	private void findDuanXin() {
		db = dbHepler.getWritableDatabase();
		d = db.rawQuery("select * from duanxin", null);
		adapter = new SimpleCursorAdapter(context, R.layout.item, d, new String[]{"message"}, new int[]{R.id.textView});
		listView.setAdapter(adapter);
	}
	private void init() {
		listView = (ListView) findViewById(R.id.listView1);
		mes = (mydata) getApplication();
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		data = (TextView) view.findViewById(R.id.textView);
		Data = (String) data.getText();
		mes.setMassage(Data);
		Toast.makeText(context, "短信内容已经替换成"+Data, Toast.LENGTH_SHORT).show();
	}
}
