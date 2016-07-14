package com.example.listv.shezhi;


import java.util.zip.Inflater;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.a.a.a.a;
import com.example.listv.R;

public class myduanxin extends Activity{
	
	Context context;
	ImageView imageView;
	Button bt_add;
	Button bt_del;
	EditText editText;
	Button queding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		//这里要引入com.example.listv.R;而不是系统的R,若是系统的R,就找不到布局文件
		setContentView(R.layout.myduanxinxml);
		this.context = this;
		init();
		
		bt_add_dianji();//点击添加按钮
		bt_del_dianji();//点击删除按钮
	}

	private void bt_del_dianji() {//删除数据
		
	}

	private void bt_add_dianji() {//添加数据
		bt_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(myduanxin.this,tianjaduanxin.class));
			}
		});
	}

	

	private void init() {
		imageView = (ImageView) findViewById(R.id.image_duanxin);
		bt_add = (Button) findViewById(R.id.id_bt_tianjiaduanxin);
		bt_del = (Button) findViewById(R.id.id_bt_shanchu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
