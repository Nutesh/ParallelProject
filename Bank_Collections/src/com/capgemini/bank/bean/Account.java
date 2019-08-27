package com.capgemini.bank.bean;
/**
 * 
 * @author nurathod
 *
 */
public class Account {
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	private long accountNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String gender;
	private double balance;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", gender=" + gender + ", balance=" + balance
				+ "]";
	}

	public Account(String firstName2, String middleName2, String lastName2, String mobileNo2, String gender2) {
		super();
		
		this.firstName = firstName2;
		this.middleName = middleName2;
		this.lastName = lastName2;
		this.mobileNo = mobileNo2;
		this.gender = gender2;
			}

	public Account(long accountNo, String firstName, String middleName, String lastName, String mobileNo, String gender,
			long balance) {
		super();
		this.accountNo = accountNo;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.balance = balance;
	}

}
