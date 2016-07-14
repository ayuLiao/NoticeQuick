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
	MydbHepler dbHepler;//���ݿ������
	SQLiteDatabase db;//���ݿ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tianjaduanxinxml);
		
		this.context = this;
		
		init();
		tianja();
		editText.addTextChangedListener(watcher);//editText�����¼�
		button.setEnabled(false);
	}
	

	private void init() {
		textView = (TextView) findViewById(R.id.id_tj);
		button = (Button) findViewById(R.id.id_button);
		editText = (EditText) findViewById(R.id.id_ed_duanxin);
		dbHepler = new MydbHepler(context, "stu.db");//SQLite���ݿ�
	}
	
	private void tianja() {
		db = dbHepler.getWritableDatabase();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("onclick", "��ť���");
				String st = editText.getText().toString();
				ContentValues values = new ContentValues();
				values.put("message", st);
				db.insert("duanxin", null, values);
				values.clear();
				Toast.makeText(context, "�¶�����ӳɹ�����ȥ�˵�����", Toast.LENGTH_LONG).show();
				
				//���������Ƿ���Ĵ����ȥ��
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
				
				
				
//				//�������д��һֱ���ᱨ�������Ѵ��ڡ�
//				if(c != null){
//				Toast.makeText(context, "�����Ѵ���", Toast.LENGTH_LONG).show();
//				}else{
//					ContentValues values = new ContentValues();
//					values.put("message", st);
//					db.insert("duanxin", null, values);
//					values.clear();
//					Toast.makeText(context, "�¶�����ӳɹ�����ȥ�˵�����", Toast.LENGTH_LONG).show();
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
