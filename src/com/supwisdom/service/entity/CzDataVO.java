package com.supwisdom.service.entity;

public class CzDataVO {
	
	/**充值时间*/
	private String transtime;
	
	/**充值金额*/
	private String amount;
	
	/**充值位置*/
	private String position;
	
	/**充值后金额*/
	private String aftbala;
	
	/**充值前金额*/
	private String befbala;
	
	/**类型*/
	private String type;
	
	/**充值消费标志    1--充值      0--消费*/
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTranstime() {
		return transtime;
	}

	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAftbala() {
		return aftbala;
	}

	public void setAftbala(String aftbala) {
		this.aftbala = aftbala;
	}

	public String getBefbala() {
		return befbala;
	}

	public void setBefbala(String befbala) {
		this.befbala = befbala;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		return "CzDataVO [transtime=" + this.transtime + ", amount=" + this.amount
				+ ", position=" + this.position + ", aftbala=" + this.aftbala
				+ ", befbala=" + this.befbala + ", type=" + this.type + "]";
	}
}
