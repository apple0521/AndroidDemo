package com.jxnu.cic.entity;

public class userlogin {

	private int _id;
	private String user_name;
	private String user_psd;
	private String school;
	private String academy;
	private String entry_Date;
	private int loginNUm;
	private String loginDatetime;
	
	
	public userlogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_psd() {
		return user_psd;
	}
	public void setUser_psd(String user_psd) {
		this.user_psd = user_psd;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getEntry_Date() {
		return entry_Date;
	}
	public void setEntry_Date(String entry_Date) {
		this.entry_Date = entry_Date;
	}
	public int getLoginNUm() {
		return loginNUm;
	}
	public void setLoginNUm(int loginNUm) {
		this.loginNUm = loginNUm;
	}
	public String getLoginDatetime() {
		return loginDatetime;
	}
	public void setLoginDatetime(String loginDatetime) {
		this.loginDatetime = loginDatetime;
	}
	public userlogin(int _id, String user_name, String user_psd, String school,
			String academy, String entry_Date, int loginNUm,
			String loginDatetime) {
		super();
		this._id = _id;
		this.user_name = user_name;
		this.user_psd = user_psd;
		this.school = school;
		this.academy = academy;
		this.entry_Date = entry_Date;
		this.loginNUm = loginNUm;
		this.loginDatetime = loginDatetime;
	}
	
}
