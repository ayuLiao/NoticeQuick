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
	
		//����Ҫ����com.example.listv.R;������ϵͳ��R,����ϵͳ��R,���Ҳ��������ļ�
		setContentView(R.layout.myduanxinxml);
		this.context = this;
		init();
		
		bt_add_dianji();//�����Ӱ�ť
		bt_del_dianji();//���ɾ����ť
	}

	private void bt_del_dianji() {//ɾ������
		
	}

	private void bt_add_dianji() {//�������
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
