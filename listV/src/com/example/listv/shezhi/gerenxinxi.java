package com.example.listv.shezhi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listv.R;

public class gerenxinxi extends Activity{
	EditText ed_name;
	EditText ed_phone;
	EditText ed_password;
	EditText ed_word;
	Button bt_sure;
	Button bt_cancel;
	
	Context context;
	
	mydata Name;
	mydata Phone;
	mydata Password;
	mydata Word;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.genrenxingxixml);
		this.context = this;
		init();
		giveinit();
		bt_listen();
	}
	private void giveinit() {
		ed_name.setText(Name.getName());
		ed_password.setText(Password.getPassword());
		ed_phone.setText(Phone.getUsername());
		ed_word.setText(Word.getChat());
	}
	private void bt_listen() {
		bt_sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = ed_name.getText().toString();
				String password = ed_password.getText().toString();
				String word = ed_word.getText().toString();
				Name.setName(name);
				Password.setPassword(password);
				Word.setChat(word);
				Toast.makeText(context, "已保存修改数据", Toast.LENGTH_SHORT).show();
			}
		});
		bt_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				giveinit();
			}
		});
	}
	private void init() {
		ed_name = (EditText) findViewById(R.id.id_nichen);
		ed_phone = (EditText) findViewById(R.id.id_pnm);
		ed_password = (EditText) findViewById(R.id.id_mm);
		ed_word = (EditText) findViewById(R.id.id_gexiqianming);
		bt_sure = (Button) findViewById(R.id.id_baocun);
		bt_cancel = (Button) findViewById(R.id.id_quexiao);
		Name = (mydata) getApplication();
		Password = (mydata) getApplication();
		Word = (mydata) getApplication();
		Phone = (mydata) getApplication();
	}
}
