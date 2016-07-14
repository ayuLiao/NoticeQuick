package com.example.listv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.baidu.mapapi.SDKInitializer;




public class MyBaiduMapMain extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.mymain);

		
		findViewById(R.id.button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyBaiduMapMain.this,RadarDemo.class));
				
			}
		});
		
	}
}
