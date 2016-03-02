package com.supwisdom.service.entity;

public class YktMessage {
	/**
	 * 工号
	 */
	private String cardId;
	/**
	 * 操作时间
	 */
	private String opDate;
	/**
	 * 操作金额
	 */
	private double opFee;
	/**
	 * 更新时间
	 */
	private String upDate;
	/**
	 * 卡状态
	 */
	private String status;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public double getOpFee() {
		return opFee;
	}
	public void setOpFee(double opFee) {
		this.opFee = opFee;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
