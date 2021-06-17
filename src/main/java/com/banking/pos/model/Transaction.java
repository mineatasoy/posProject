package com.banking.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="TRANSACTIONS")
@Entity
public class Transaction {
	
	
	public static final String MESSAGE_TYPE_PAYMENT="PAYMENT";
	public static final String MESSAGE_TYPE_ADJUSTMENT="ADJUSTMENT";

	private static final String ORIGIN_VISA="VISA";
	private static final String ORIGIN_MASTER="MASTER";
	
	private static final double ORIGIN_COMMISION_RATE_VISA=0.1;
	private static final double ORIGIN_COMMISION_RATE_MASTER=0.2;
	

//	@Id
//	@GeneratedValue
//	@Column(name="ID",updatable = false)
//	int Id;
	
	@Column(name="MESSAGETYPE",updatable = true)
	private String messageType;
	
	@Id
	@Column
	private String transactionId;
	
	@Column
	private Long accountId;
	
	@Column
	private String origin;
	
	@Column
	private double amount;
	
	@Column
	private double commission; //??

	public void calculateCommision() {
		double commisionRate=0;
		
		switch (this.origin) {
		case ORIGIN_VISA:
			commisionRate=ORIGIN_COMMISION_RATE_VISA;
			break;
		case ORIGIN_MASTER:
			commisionRate=ORIGIN_COMMISION_RATE_MASTER;
			break;
		}

		this.commission=this.amount*commisionRate;
	}
	
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	
}
