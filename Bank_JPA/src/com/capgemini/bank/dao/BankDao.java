package com.capgemini.bank.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;

public interface BankDao {

	long insertAccount(Account account);
	
	String validateAccount(long accountNo,int pin);
	
	String insertTransaction(Transaction transaction);
	
	Account updateBalance(long accountNo,double balance);
	
	ArrayList<Transaction> showAllTransactions(Long accountNo);
	
	double showBalance(Long accountNo);
	
	String getAccountName(long accountNo);
	
	
}
