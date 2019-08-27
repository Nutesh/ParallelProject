package com.capgemini.bank.bean;

public class Transaction {
	private String transactionNo;
	private long accountNo;
	private String type;
	private double amount;
	private String time;
	private long destinationAccountNo;

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getDestinationAccountNo() {
		return destinationAccountNo;
	}

	public void setDestinationAccountNo(long destinationAccountNo) {
		this.destinationAccountNo = destinationAccountNo;
	}

	public Transaction( long accountNo, String type, double amount,
			long destinationAccountNo) {
		super();
		this.accountNo = accountNo;
		this.type = type;
		this.amount = amount;
		this.destinationAccountNo = destinationAccountNo;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(String transactionNo, long accountNo, String type, double amount, String time,
			long destinationAccountNo) {
		super();
		this.transactionNo = transactionNo;
		this.accountNo = accountNo;
		this.type = type;
		this.amount = amount;
		this.time = time;
		this.destinationAccountNo = destinationAccountNo;
	}

	@Override
	public String toString() {
		return  transactionNo  +"\t|"+ accountNo +"\t|" + type +"\t|"+ amount +"\t|" + time +"|" + destinationAccountNo ;
	}

}
