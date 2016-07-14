package com.example.listv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listv.shezhi.mydata;
import com.example.listv.view.CustomDialog;

public class zhuce extends Activity{
	EditText et_name;
	EditText et_phone;
	EditText et_mima;
	EditText et_mima2;
	Button button_zhuce;
	Context context;
	mydata username;
	mydata userpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhucexml);
		this.context = this;
		init();
		et_mima2.addTextChangedListener(watcher);
		bt_listen();
	}
	
	private void init() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_mima = (EditText) findViewById(R.id.et_mima);
		et_mima2 = (EditText) findViewById(R.id.et_mima2);
		button_zhuce = (Button) findViewById(R.id.id_bt_zhuce);
		button_zhuce.setEnabled(false);
		username = (mydata) getApplication();
		userpassword = (mydata) getApplication();
	}

	TextWatcher watcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			button_zhuce.setEnabled(true);
	
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			
			
		}
	};
	
	private void bt_listen(){
		button_zhuce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String mima = et_mima.getText().toString().trim();
				String mima2 = et_mima2.getText().toString().trim();
				String phone = et_phone.getText().toString().trim();
				
				if(phone.length() == 11){
					if(mima.equals(mima2)){
						username.setUsername(phone);
						userpassword.setPassword(mima);
						Toast.makeText(context, "注册成功", Toast.LENGTH_LONG).show();
						//延迟2秒，跳转回登陆界面
						Handler handler = new Handler();
						Runnable denglu = new Runnable() {
							
							@Override
							public void run() {
								Intent intent = new Intent();
								intent.setClass(context, denglu.class);
								context.startActivity(intent);
								finish();
							}
						};
						handler.postDelayed(denglu, 2000);
					}else{
						Toast.makeText(context, "两次输入密码不相同", Toast.LENGTH_LONG).show();
					}	
				}else{
					Toast.makeText(context, "手机号格式错误", Toast.LENGTH_LONG).show();
				}

			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
