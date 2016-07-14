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
	SQLiteDatabase db;//���ݿ�
	MydbHepler dbHepler;//���ݿ������
	Cursor c;//SQLiteȫ��ѯ�õ�������
	Cursor d;//SQLiteģ����ѯ�õ�������
	String text;
	SimpleCursorAdapter adapter;//Cursor������
	//TextView textView;
	String name = "number";
	String[] Alist = {"���Ͷ���","����绰","ɾ��"};
	TextView data;//item����,Ҳ���Ǻ���
	SmsManager smsManager;
	//String massage = "ͬѧ������������ˣ��뾡�쵽¥������";//������Ϣ������Ĭ��ֵ
	String Massage;//������Ϣ
	String Data;
	//�˵��ؼ�
	private ArcMenu mArcMenu;
	
	//ȫ�ֱ���
	mydata news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		 SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.fragment_main);
//        final mydata massage = (mydata) getApplication();
    	this.context = this;
    	//��ʼ��������Ϣ
    	
    	news = (mydata) getApplication();
  
        init();
        Log.v("start", "ִ�е�onCreate");
       
    	//Massage = news.getMassage();
    	
    	
        initEvent();//����listView�����¼�����������ʱ���˵����Ӳ˵�����
        Log.v("start", "ִ�е�duanxinlist��");
        dbHepler = new MydbHepler(MainActivity.this, "stu.db");//SQLite���ݿ�
         
        editText.addTextChangedListener(watcher);//editText�����¼�
        //ListQuery();//�����ݿ���ȡ��������ת��List������
      
        Log.v("start", "ִ�е�initEvent��");
        findSQLite();
        ListQuery();
        listView.setOnItemClickListener(this);
  
        set_image_OnClick();//������ͼƬ������
        
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
			//����Ӳ˵���Toast
			
			@Override
			public void onClick(View view, int pos) {
				
				switch (pos) {
				case 1:
					//��RadarDemo���ڱ�activity��Ҫ�� SDKInitializer.initialize(getApplicationContext());Ȩ��
					Log.v("start","��ͼ��" );
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, RadarDemo.class);
					startActivity(intent);
					break;
				case 2:
					Log.v("start", "����ѡ���");
////					Dialog alertDialog1 = new AlertDialog.Builder(context).setTitle("ѡ���������")
////					.setItems(DuanXinList, new DialogInterface.OnClickListener() {//���������
////						
////						@Override
////						public void onClick(DialogInterface dialog, int which) {
////							Log.v("start", "�����");
////							news.setMassage(DuanXinList[which]);
////					    	Massage = news.getMassage();
////							Toast.makeText(context, "�����������޸ĳ�"+DuanXinList[which], Toast.LENGTH_LONG).show();
////						}
////
////					}).create();
////			alertDialog1.show();
//					AlertDialog.Builder builder = new AlertDialog.Builder(context);
//					Log.v("start", "������AlertDialog");
//					builder.setItems(Alist, new DialogInterface.OnClickListener() {
//						
//						@Override
//						public void onClick(DialogInterface arg0, int which) {
//							Log.v("start", "�����");
////							news.setMassage(DuanXinList[which]);
////							Massage = news.getMassage();
////							Toast.makeText(context, "�����������޸ĳ�"+DuanXinList[which], Toast.LENGTH_LONG).show();
//						}
//					});
//					builder.create().show();
					
					startActivity(new Intent(context,chooseduanxin.class));
					
					break;
				case 3:
					Log.v("start", "��ת������ҳ��");
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
	
				
		if(text == ""){//Ϊ��˵�������Ǹմ������Ӧ��ʾȫ������
			ListQuery();
		}else{//��Ϊ�գ��Ѿ�����������
			findSQLite();
			if(d != null){//���������ַ�				
				adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.item, d, new String[]{name}, new int[]{R.id.textView});
				listView.setAdapter(adapter);
			}
			//������d == null �������else���޷�ִ����������
//			}else{
//				ContentValues values = new ContentValues();
//				values.put("number", text);
//				db.insert("stutb", null, values);
//				values.clear();	
//				findSQLite();
//			}
			
			
		}
		
	//ֻҪ����d == null���ж��������޷�ִ��
		
	}
	
	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	} 
	
	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
		if(text.length() == 11){//�����ｨ��һ������ֱ��ȡeditText�е�ֵ���ж��ŷ���
			ContentValues values = new ContentValues();
			values.put("number", text);
			db.insert("stutb", null, values);
			values.clear();	
			findSQLite();//����cursorת��arrayList��Ȼ��������һ�������������أ�����������ʾ����
		}
		if(arg0.length() == 0){
			imageView.setVisibility(View.GONE);//���ı���Ϊ��ʱ��������ʧ
		}
		else {
			imageView.setVisibility(View.VISIBLE);//���ı���Ϊ��ʱ�����ֲ��
		}
	
	}
};



public void set_image_OnClick(){//���ɾ����ť���ÿ�
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
	//�����OnClickListenerҪ��DialogInterface����Ȼ���button��onClickListener��ͻ������
	Dialog alertDialog = new AlertDialog.Builder(this).setTitle("ѡ��")
			.setItems(Alist, new DialogInterface.OnClickListener() {//���������
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					switch (which) {
					case 0:
					    smsManager = SmsManager.getDefault();//ȱ����������
						duanxin();
						Toast.makeText(MainActivity.this, 
								"�����ѷ���", Toast.LENGTH_LONG).show();
						break;
					case 1:
						
						dianhua();
						Toast.makeText(MainActivity.this, 
								"�绰��ʼ����", Toast.LENGTH_LONG).show();
						break;
						
					case 2:
						
						dele();
						Toast.makeText(MainActivity.this, 
								"������ɾ��", Toast.LENGTH_LONG).show();
						break;
					
					}
					 
				}

			}).create();
	alertDialog.show();
}
public void duanxin(){
	Massage = news.getMassage();//�������λ�ã���������
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
