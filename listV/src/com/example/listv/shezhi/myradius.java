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

public class myradius extends Activity{
	
	EditText ed;
	Button bt;
	
	mydata Radius;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radiusxml);
		this.context = this;
		ed = (EditText) findViewById(R.id.id_ed_radius);
		bt = (Button) findViewById(R.id.bt_radius);
		Radius = (mydata) getApplication();
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = ed.getText().toString();
				int Number = Integer.parseInt(number);
				Radius.setRadius(Number);
				Toast.makeText(context, "雷达半径已改为"+Radius.getRadius(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
