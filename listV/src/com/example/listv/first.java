package com.example.listv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class first extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.first);
		Handler handler = new Handler();
		Runnable first = new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(first.this,denglu.class));
				finish();
			}
		};
		handler.postDelayed(first, 2000);
	
	}
}
