package com.jxnu.cic.entity;

public class Sdtg {
  private String showTitle;
  private String showUrl;
  private String showTime;
public String getShowTitle() {
	return showTitle;
}
public void setShowTitle(String showTitle) {
	this.showTitle = showTitle;
}
public String getShowUrl() {
	return showUrl;
}
public void setShowUrl(String showUrl) {
	this.showUrl = showUrl;
}
public String getShowTime() {
	return showTime;
}
public void setShowTime(String showTime) {
	this.showTime = showTime;
}
public Sdtg(String showTitle, String showUrl, String showTime) {
	super();
	this.showTitle = showTitle;
	this.showUrl = showUrl;
	this.showTime = showTime;
}

  
}
