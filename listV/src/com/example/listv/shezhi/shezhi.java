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
	//得到列表
	ListView listView;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> dataList;
	//清空列表
	SQLiteDatabase db;//数据库
	MydbHepler dbHepler;//数据库相关类
	
	SimpleCursorAdapter adapter;//Cursor适配器
	Cursor c;//SQLite全查询得到的数据
	String name = "number";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.myshezhi);
		
		this.context = this;
		
		listView = (ListView) findViewById(R.id.listView1);
		//新建数据
		dataList = new ArrayList<Map<String,Object>>();
		//加载适配器
		simp_adapter = new SimpleAdapter(this, getData(), R.layout.itemshezhi, new String[]{"pic","text"}
		, new int[]{R.id.pic,R.id.text});
		listView.setAdapter(simp_adapter);
		listView.setOnItemClickListener(this);
		
		dbHepler = new MydbHepler(context, "stu.db");//SQLite数据库
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	//刷新所有数据
	private void ListQuery() {
		db = dbHepler.getWritableDatabase();	
		c = db.rawQuery("select * from stutb", null);
		adapter = new SimpleCursorAdapter(this, R.layout.item, c, new String[]{name}, new int[]{R.id.textView});
		

		listView.setAdapter(adapter);
		
	}
	//数据搭建
	private List<Map<String, Object>> getData(){
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("pic", R.drawable.dx);
		map1.put("text", "短信编辑");
		dataList.add(map1);
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("pic", R.drawable.txl);
		map2.put("text", "通讯录导入");
		dataList.add(map2);
		
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("pic", R.drawable.gx);
		map3.put("text", "个人信息");
		dataList.add(map3);
		
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("pic", R.drawable.dw);
		map4.put("text", "雷达设置");
		dataList.add(map4);
		
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("pic", R.drawable.qk);
		map5.put("text", "清空数据");
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
			//点击删除时，弹出警告框
//			new AlertDialog.Builder(context).setTitle("删除提示").setMessage("确认删除全部数据？")
//			.setPositiveButton("删除", new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dele_all();
//					Toast.makeText(context, "列表数据已全部删除", Toast.LENGTH_SHORT).show();
//				}
//			})
//			.setNegativeButton("取消", null).show();
//			break;
			//自定义弹框的运用
			CustomDialog.Builder builder = new CustomDialog.Builder(context);
			builder.setMessage("您确定删除所有号码？？");
			builder.setTitle("删除提示");
			builder.setPositiveButton("清空", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					dele_all();
					Toast.makeText(context, "列表中的号码已全部删除", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				
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
