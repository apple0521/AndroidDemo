package com.jxnu.cic.entity;

public class Sdyw {
	private String infoTitle;   //新闻题目
    private String infoUrl;     //新闻链接地址
   // private String 
    private String infoImg;     //新闻图片
    private String infoTime;    //新闻时间

    public Sdyw(String infoTitle, String infoUrl, String infoImg,String infoTime) {
        this.infoTitle = infoTitle;
        this.infoUrl = infoUrl;
        this.infoImg=infoImg;
        this.infoTime = infoTime;
    }
    public String getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }
	public String getInfoImg() {
		return infoImg;
	}
	public void setInfoImg(String infoImg) {
		this.infoImg = infoImg;
	}
}
