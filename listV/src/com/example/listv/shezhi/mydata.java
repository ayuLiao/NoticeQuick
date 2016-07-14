package com.example.listv.shezhi;

import android.app.Application;

public class mydata extends Application{
	
	private String massage;
	private String username;
	private String password;
	private String name;
	private String chat;
	private int radius;
	private int time;
	@Override
	public void onCreate() {
		super.onCreate();
		massage = "同学，你的外卖已经到楼下了，请下来拿!!";
		username = "";
		password = "";
		name = "";
		chat = "这家伙很懒,没有留下什么...";
		radius = 2000;
		time = 1000;
	}
	//得到短信内容
	public String getMassage(){
		return this.massage;	
	}
	//设置短信内容
	public void setMassage(String Massage){
		this.massage = Massage;
	}
	
	
	//得到用户，也就是手机号
	public String getUsername(){
		return this.username;
	}
	//修改用户名
	public void setUsername(String Username){
		this.username = Username;
	}
	
	//得到密码
	public String getPassword(){
		return this.password;
	}
	//修改密码
	public void setPassword(String Password){
		this.password = Password;
	}
	//用户昵称
	public String getName(){
		return this.name;
	}
	
	public void setName(String Name){
		this.name = Name;
	}
	
	public String getChat(){
		return this.chat;
	}
	
	
	public void setChat(String Chat){
		this.chat = Chat;
	}
	//雷达半径
	public int getRadius(){
		return this.radius;
	}
	
	public void setRadius(int Radius){
		this.radius = Radius;
	}
	//每隔多少毫秒进行一次GPS请求
	public int getTime(){
		return this.time;
	}
	
	public void setTime(int Time){
		this.time = Time;
	}
	
}
