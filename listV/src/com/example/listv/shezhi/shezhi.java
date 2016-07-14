package com.example.listv.shezhi;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.listv.MydbHepler;
import com.example.listv.R;
import com.example.listv.view.CustomDialog;

public class shezhi extends Activity implements OnItemClickListener{

	Context context;
	//�õ��б�
	ListView listView;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> dataList;
	//����б�
	SQLiteDatabase db;//���ݿ�
	MydbHepler dbHepler;//���ݿ������
	
	SimpleCursorAdapter adapter;//Cursor������
	Cursor c;//SQLiteȫ��ѯ�õ�������
	String name = "number";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.myshezhi);
		
		this.context = this;
		
		listView = (ListView) findViewById(R.id.listView1);
		//�½�����
		dataList = new ArrayList<Map<String,Object>>();
		//����������
		simp_adapter = new SimpleAdapter(this, getData(), R.layout.itemshezhi, new String[]{"pic","text"}
		, new int[]{R.id.pic,R.id.text});
		listView.setAdapter(simp_adapter);
		listView.setOnItemClickListener(this);
		
		dbHepler = new MydbHepler(context, "stu.db");//SQLite���ݿ�
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	//ˢ����������
	private void ListQuery() {
		db = dbHepler.getWritableDatabase();	
		c = db.rawQuery("select * from stutb", null);
		adapter = new SimpleCursorAdapter(this, R.layout.item, c, new String[]{name}, new int[]{R.id.textView});
		

		listView.setAdapter(adapter);
		
	}
	//���ݴ
	private List<Map<String, Object>> getData(){
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("pic", R.drawable.dx);
		map1.put("text", "���ű༭");
		dataList.add(map1);
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("pic", R.drawable.txl);
		map2.put("text", "ͨѶ¼����");
		dataList.add(map2);
		
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("pic", R.drawable.gx);
		map3.put("text", "������Ϣ");
		dataList.add(map3);
		
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("pic", R.drawable.dw);
		map4.put("text", "�״�����");
		dataList.add(map4);
		
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("pic", R.drawable.qk);
		map5.put("text", "�������");
		dataList.add(map5);
		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String text = listView.getItemAtPosition(position)+"";
		Toast.makeText(this, "Position"+position+"text"+text, Toast.LENGTH_SHORT).show();
	
		switch (position+1) {
		case 1:
			startActivity(new Intent(context,myduanxin.class));
		break;
		case 2:
			startActivity(new Intent(context,getNumber.class));
		break;
		case 3:
			startActivity(new Intent(context,gerenxinxi.class));
		break;
		case 4:
			startActivity(new Intent(context,leida.class));
		break;
		case 5:
			//���ɾ��ʱ�����������
//			new AlertDialog.Builder(context).setTitle("ɾ����ʾ").setMessage("ȷ��ɾ��ȫ�����ݣ�")
//			.setPositiveButton("ɾ��", new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dele_all();
//					Toast.makeText(context, "�б�������ȫ��ɾ��", Toast.LENGTH_SHORT).show();
//				}
//			})
//			.setNegativeButton("ȡ��", null).show();
//			break;
			//�Զ��嵯�������
			CustomDialog.Builder builder = new CustomDialog.Builder(context);
			builder.setMessage("��ȷ��ɾ�����к��룿��");
			builder.setTitle("ɾ����ʾ");
			builder.setPositiveButton("���", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					dele_all();
					Toast.makeText(context, "�б��еĺ�����ȫ��ɾ��", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("ȡ��", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
	
	}

	private void dele_all() {
		db = dbHepler.getWritableDatabase();
		db.delete("stutb", null, null);
	}
}
