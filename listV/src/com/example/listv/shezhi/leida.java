package com.example.listv.shezhi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.listv.R;

public class leida extends Activity{
	Button bt_please;
	Button bt_radius;
	mydata Time;
	mydata my_Radius;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leidaxml);
		init();
		bt_listen();
	}
	private void bt_listen() {
		bt_please.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(leida.this,pleasetime.class));
			}
		});
		bt_radius.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(leida.this,myradius.class));
			}
		});
	}
	private void init() {
		bt_please = (Button) findViewById(R.id.id_please);
		bt_radius = (Button) findViewById(R.id.id_radius);
		Time = (mydata) getApplication();
		my_Radius = (mydata) getApplication();
	}
}
