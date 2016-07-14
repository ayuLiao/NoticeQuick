package com.example.listv;

import com.example.listv.shezhi.mydata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class denglu extends Activity{
	
	ImageView user_del;
	ImageView password_del;
	Button user_denglu;
	Button user_zhuce;
	EditText user_et;
	EditText password_et;
	Context context;
	mydata username;
	mydata userpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dengluxml);
		this.context = this;
		init();
		String name1 = username.getUsername();
		String word1 = userpassword.getPassword();
		user_et.setText(name1);
		password_et.setText(word1);
		bt_listen();//按钮监听
	}

	private void bt_listen() {
		user_denglu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String user = user_et.getText().toString();
				String name = username.getUsername();
				String word = userpassword.getPassword();
				String password = password_et.getText().toString();
				
				if(name.equals(user)&&word.equals(password)&&name != "" && word != ""){
					startActivity(new Intent(context,MainActivity.class));
					finish();
//				}else if(name == ""){//全局数据没有用户名
//					Toast.makeText(context, "请去注册账号", Toast.LENGTH_SHORT).show();
//				}else if(user == ""&&name != ""){
//					Toast.makeText(context, "请输入账号", Toast.LENGTH_SHORT).show();
//				}else if(password == ""&&name != ""){
//					Toast.makeText(context, "请去输入密码", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(context, "账号或密码错误", Toast.LENGTH_SHORT).show();
				}
					
				
			}
		});
	
	
		user_zhuce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {//跳转至注册页面
				startActivity(new Intent(context,zhuce.class));
				finish();
			}
		});
	}

	private void init() {
		user_del = (ImageView) findViewById(R.id.im_user_del);
		password_del = (ImageView) findViewById(R.id.im_password_del);
		user_denglu = (Button) findViewById(R.id.id_denglu);
		user_zhuce = (Button) findViewById(R.id.id_zhuce);
		user_et = (EditText) findViewById(R.id.et_user);
		password_et = (EditText) findViewById(R.id.et_password);
		username = (mydata) getApplication();
		userpassword = (mydata) getApplication();
	}

	
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
}
