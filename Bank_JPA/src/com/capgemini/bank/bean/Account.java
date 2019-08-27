package com.capgemini.bank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 
 * @author nurathod
 *defining entity for mapping of table and object
 */
@Entity
/**
 * 
 * specifying table name as table name is different in database
 *
 */
@Table(name="ACCOUNTS")
public class Account {
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * defining primary key using id annotation
	 */
	@Id
	@SequenceGenerator(name="accountSeq",sequenceName = "accountSeq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "accountSeq")
	@Column(name="ACCOUNTNO")
	private long accountNo;
	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="MIDDLENAME")
	private String middleName;
	@Column(name="LASTNAME")
	private String lastName;
	@Column(name="MOBILENO")
	private String mobileNo;
	@Column(name="GENDER")
	private String gender;
	@Column(name="PIN")
	private int pin;
	@Column(name="BALANCE")
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

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", gender=" + gender + ", balance=" + balance
				+ "]";
	}

	public Account(String firstName2, String middleName2, String lastName2, String mobileNo2, String gender2,int pin) {
		super();
		this.pin=pin;
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
