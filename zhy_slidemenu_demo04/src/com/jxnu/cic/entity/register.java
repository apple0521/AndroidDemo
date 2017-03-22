package com.jxnu.cic.entity;

public class register {

	private int register_id;
	private String register_phone;
	private String register_password;
	private String register_university;
	private String register_academe;
	private String register_schoolDate;
	private String register_xueli;
	public register(int register_id, String register_phone,
			String register_password, String register_university,
			String register_academe, String register_schoolDate,
			String register_xueli) {
		super();
		this.register_id = register_id;
		this.register_phone = register_phone;
		this.register_password = register_password;
		this.register_university = register_university;
		this.register_academe = register_academe;
		this.register_schoolDate = register_schoolDate;
		this.register_xueli = register_xueli;
	}
	public register() {
		// TODO Auto-generated constructor stub
		super();
	}
	public int getRegister_id() {
		return register_id;
	}
	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}
	public String getRegister_phone() {
		return register_phone;
	}
	public void setRegister_phone(String register_phone) {
		this.register_phone = register_phone;
	}
	public String getRegister_password() {
		return register_password;
	}
	public void setRegister_password(String register_password) {
		this.register_password = register_password;
	}
	public String getRegister_university() {
		return register_university;
	}
	public void setRegister_university(String register_university) {
		this.register_university = register_university;
	}
	public String getRegister_academe() {
		return register_academe;
	}
	public void setRegister_academe(String register_academe) {
		this.register_academe = register_academe;
	}
	public String getRegister_schoolDate() {
		return register_schoolDate;
	}
	public void setRegister_schoolDate(String register_schoolDate) {
		this.register_schoolDate = register_schoolDate;
	}
	public String getRegister_xueli() {
		return register_xueli;
	}
	public void setRegister_xueli(String register_xueli) {
		this.register_xueli = register_xueli;
	}
	
}
