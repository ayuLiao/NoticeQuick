package com.example.listv;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.listv.shezhi.mydata;
import com.example.listv.shezhi.shezhi;
import com.example.listv.view.ArcMenu;
import com.example.listv.view.ArcMenu.OnMenuItemClikListener;


public class MainActivity extends Activity implements OnItemClickListener{
	Context context;
	EditText editText;
	ListView listView;
	ImageView imageView;
	SQLiteDatabase db;//数据库
	MydbHepler dbHepler;//数据库相关类
	Cursor c;//SQLite全查询得到的数据
	Cursor d;//SQLite模糊查询得到的数据
	String text;
	SimpleCursorAdapter adapter;//Cursor适配器
	//TextView textView;
	String name = "number";
	String[] Alist = {"发送短信","拨打电话","删除"};
	TextView data;//item数据,也就是号码
	SmsManager smsManager;
	//String massage = "同学，你的外卖到了，请尽快到楼下来拿";//短信消息，这是默认值
	String Massage;//短信消息
	String Data;
	//菜单控件
	private ArcMenu mArcMenu;
	
	//全局变量
	mydata news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		 SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.fragment_main);
//        final mydata massage = (mydata) getApplication();
    	this.context = this;
    	//初始化短信信息
    	
    	news = (mydata) getApplication();
  
        init();
        Log.v("start", "执行到onCreate");
       
    	//Massage = news.getMassage();
    	
    	
        initEvent();//监听listView下来事件，当其下拉时，菜单的子菜单收缩
        Log.v("start", "执行到duanxinlist后");
        dbHepler = new MydbHepler(MainActivity.this, "stu.db");//SQLite数据库
         
        editText.addTextChangedListener(watcher);//editText监听事件
        //ListQuery();//将数据库中取出的数据转成List或数据
      
        Log.v("start", "执行到initEvent后");
        findSQLite();
        ListQuery();
        listView.setOnItemClickListener(this);
  
        set_image_OnClick();//点击叉叉图片，监听
        
    }
   


	private void initEvent() {
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				if(mArcMenu.isOpen())
				{
					mArcMenu.toggleMenu(600);
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, 
					int visibleItem, int totalItemCount) {
				
			}
		});
	mArcMenu.setOnMenuItemClikListener(new OnMenuItemClikListener() {
			//点击子菜单，Toast
			
			@Override
			public void onClick(View view, int pos) {
				
				switch (pos) {
				case 1:
					//打开RadarDemo，在本activity中要有 SDKInitializer.initialize(getApplicationContext());权限
					Log.v("start","地图打开" );
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, RadarDemo.class);
					startActivity(intent);
					break;
				case 2:
					Log.v("start", "短信选择打开");
////					Dialog alertDialog1 = new AlertDialog.Builder(context).setTitle("选择短信内容")
////					.setItems(DuanXinList, new DialogInterface.OnClickListener() {//弹出框监听
////						
////						@Override
////						public void onClick(DialogInterface dialog, int which) {
////							Log.v("start", "点击了");
////							news.setMassage(DuanXinList[which]);
////					    	Massage = news.getMassage();
////							Toast.makeText(context, "短信内容已修改成"+DuanXinList[which], Toast.LENGTH_LONG).show();
////						}
////
////					}).create();
////			alertDialog1.show();
//					AlertDialog.Builder builder = new AlertDialog.Builder(context);
//					Log.v("start", "建立了AlertDialog");
//					builder.setItems(Alist, new DialogInterface.OnClickListener() {
//						
//						@Override
//						public void onClick(DialogInterface arg0, int which) {
//							Log.v("start", "点击了");
////							news.setMassage(DuanXinList[which]);
////							Massage = news.getMassage();
////							Toast.makeText(context, "短信内容已修改成"+DuanXinList[which], Toast.LENGTH_LONG).show();
//						}
//					});
//					builder.create().show();
					
					startActivity(new Intent(context,chooseduanxin.class));
					
					break;
				case 3:
					Log.v("start", "跳转到设置页面");
					startActivity(new Intent(MainActivity.this,shezhi.class));
					
				}
			}
		});
	}

	

	public void findSQLite(){
    	
    	db = dbHepler.getWritableDatabase();
    	d = db.rawQuery("select * from stutb where number like '%"+text+"%'", null);
    	adapter = new SimpleCursorAdapter(this, R.layout.item, d, new String[]{name}, new int[]{R.id.textView});
    	listView.setAdapter(adapter);
//    	if(d != null){
//    		String[] cl = d.getColumnNames();
//    		while(d.moveToNext()){
//    			for (String um : cl) {
//					String he = d.getString(d.getColumnIndex(um));
//					textView.setText(he);
//				}
//    		}
//    	}
    }

	public void init(){
    	listView = (ListView) findViewById(R.id.listView);
    	editText = (EditText) findViewById(R.id.editText);
    	//textView = (TextView) findViewById(R.id .textView);
    	imageView = (ImageView) findViewById(R.id.imageView);
    	mArcMenu = (ArcMenu) findViewById(R.id.id_menu);
    }
    
	private void ListQuery() {
		db = dbHepler.getWritableDatabase();	
		c = db.rawQuery("select * from stutb", null);
		adapter = new SimpleCursorAdapter(this, R.layout.item, c, new String[]{name}, new int[]{R.id.textView});
		
//		if(c != null){
//			String[] cols = c.getColumnNames();
//			while(c.moveToNext()){
//			 	for(String Colum : cols){
//					//String he = c.getString(c.getColumnIndex(Colum));
//					System.out.print(c.getString(c.getColumnIndex(Colum)));
//					//adapter = new listAdater(he, this);
//				}
//			}
//		}
		listView.setAdapter(adapter);
		
	}
	
   TextWatcher watcher = new TextWatcher() {
	
	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		text = editText.getText().toString().trim();
	
				
		if(text == ""){//为空说明，这是刚打开软件，应显示全部数据
			ListQuery();
		}else{//不为空，已经输入了数据
			findSQLite();
			if(d != null){//正在输入字符				
				adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.item, d, new String[]{name}, new int[]{R.id.textView});
				listView.setAdapter(adapter);
			}
			//无论是d == null 还是这个else都无法执行里面的语句
//			}else{
//				ContentValues values = new ContentValues();
//				values.put("number", text);
//				db.insert("stutb", null, values);
//				values.clear();	
//				findSQLite();
//			}
			
			
		}
		
	//只要加了d == null的判断条件就无法执行
		
	}
	
	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	} 
	
	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
		if(text.length() == 11){//在这里建立一个弹框，直接取editText中的值进行短信发送
			ContentValues values = new ContentValues();
			values.put("number", text);
			db.insert("stutb", null, values);
			values.clear();	
			findSQLite();//将其cursor转成arrayList，然后再用另一种数据器来加载，这样方便显示查找
		}
		if(arg0.length() == 0){
			imageView.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
		}
		else {
			imageView.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
		}
	
	}
};



public void set_image_OnClick(){//点击删除按钮，置空
	imageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			editText.setText("");
			
		}
	});
}


@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	data = (TextView) view.findViewById(R.id.textView);

	Data = (String) data.getText();
	//textView.setText(data.getText());

	choose();
}

public void choose(){
	//这里的OnClickListener要加DialogInterface，不然会跟button的onClickListener冲突，报错
	Dialog alertDialog = new AlertDialog.Builder(this).setTitle("选择")
			.setItems(Alist, new DialogInterface.OnClickListener() {//弹出框监听
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					switch (which) {
					case 0:
					    smsManager = SmsManager.getDefault();//缺少这条报错
						duanxin();
						Toast.makeText(MainActivity.this, 
								"短信已发送", Toast.LENGTH_LONG).show();
						break;
					case 1:
						
						dianhua();
						Toast.makeText(MainActivity.this, 
								"电话开始拨打", Toast.LENGTH_LONG).show();
						break;
						
					case 2:
						
						dele();
						Toast.makeText(MainActivity.this, 
								"数据已删除", Toast.LENGTH_LONG).show();
						break;
					
					}
					 
				}

			}).create();
	alertDialog.show();
}
public void duanxin(){
	Massage = news.getMassage();//放在这个位置，让其重置
	List<String> divideContents = smsManager.divideMessage(Massage);
	for(String st1: divideContents){
      	smsManager.sendTextMessage(Data, null, st1, null, null);
        }
}

private void dianhua() {
	Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+Data));
	startActivity(intent);
}


public void dele(){
	db = dbHepler.getWritableDatabase();
	Cursor f = db.query("stutb", null, "number = ?", new String[]{Data}, null, null, null);
	if(f != null){
		String [] COL = f.getColumnNames();
		while(f.moveToNext()){
			for (String COLS : COL) {
				String Dele = f.getString(f.getColumnIndex(COLS));
				db.delete("stutb", "number = ?", new String[]{Dele});
			}
		}
	}
	ListQuery();
}


}
