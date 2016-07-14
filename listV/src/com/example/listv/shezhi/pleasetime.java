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

public class pleasetime extends Activity{
	
	EditText ed;
	Button bt;
	
	mydata Time;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pleasetime);
		this.context = this;
		ed = (EditText) findViewById(R.id.id_ed_time);
		bt = (Button) findViewById(R.id.bt_get);
		Time = (mydata) getApplication();
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = ed.getText().toString();
				int Number = Integer.parseInt(number);
				Time.setTime(Number);
				Toast.makeText(context, "请求间隔已改为"+Time.getTime(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
