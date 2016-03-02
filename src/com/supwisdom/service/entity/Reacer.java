package com.supwisdom.service.entity;

/**
 * 借阅图书欠费信息 实体类
 * 
 * @author fei.chen
 *
 */
public class Reacer {
	/** 
	 * 工号
	 */
	private String READERBARCODE;
	/**
	 * 借书时间
	 */
	private String BRROWTIME;
	/**
	 * 到期时间
	 */
	private String RETURNTIME;
	/**
	 * 到期图书数量
	 */
	private String expireCount;
	/**
	 * 图书条形码
	 */
	private String barcode;
	/**
	 * 图书名称
	 */
	private String title;
	/** 借阅证号 */
	private String CARDNO;
	/** 欠费金额 */
	private String FINE;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBRROWTIME() {
		return BRROWTIME;
	}

	public void setBRROWTIME(String bRROWTIME) {
		BRROWTIME = bRROWTIME;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getExpireCount() {
		return expireCount;
	}

	public void setExpireCount(String expireCount) {
		this.expireCount = expireCount;
	}

	public String getREADERBARCODE() {
		return READERBARCODE;
	}

	public void setREADERBARCODE(String rEADERBARCODE) {
		READERBARCODE = rEADERBARCODE;
	}

	public String getRETURNTIME() {
		return RETURNTIME;
	}

	public void setRETURNTIME(String rETURNTIME) {
		RETURNTIME = rETURNTIME;
	}

	public String getCARDNO() {
		return CARDNO;
	}

	public void setCARDNO(String cARDNO) {
		CARDNO = cARDNO;
	}

	public String getFINE() {
		return FINE;
	}

	public void setFINE(String fINE) {
		FINE = fINE;
	}

}
