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
		massage = "ͬѧ����������Ѿ���¥���ˣ���������!!";
		username = "";
		password = "";
		name = "";
		chat = "��һ����,û������ʲô...";
		radius = 2000;
		time = 1000;
	}
	//�õ���������
	public String getMassage(){
		return this.massage;	
	}
	//���ö�������
	public void setMassage(String Massage){
		this.massage = Massage;
	}
	
	
	//�õ��û���Ҳ�����ֻ���
	public String getUsername(){
		return this.username;
	}
	//�޸��û���
	public void setUsername(String Username){
		this.username = Username;
	}
	
	//�õ�����
	public String getPassword(){
		return this.password;
	}
	//�޸�����
	public void setPassword(String Password){
		this.password = Password;
	}
	//�û��ǳ�
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
	//�״�뾶
	public int getRadius(){
		return this.radius;
	}
	
	public void setRadius(int Radius){
		this.radius = Radius;
	}
	//ÿ�����ٺ������һ��GPS����
	public int getTime(){
		return this.time;
	}
	
	public void setTime(int Time){
		this.time = Time;
	}
	
}
