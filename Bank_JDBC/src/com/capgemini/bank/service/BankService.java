package com.capgemini.bank.service;

import java.util.InputMismatchException;
import java.util.List;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.exception.BankException;

public interface BankService {
	
	

	public List<Transaction> showAllTransactions(long accountNo) throws BankException, InputMismatchException;

	public long createAccount(Account account) throws BankException;

	public String validateAccount(long accountNo,int pin);

	public String deposit(Transaction transaction) throws BankException;

	public String fundTransfer(Transaction transaction) throws BankException;

	public String withdraw(Transaction transaction) throws BankException;

	double showBalance(long accountNo) throws BankException, InputMismatchException;
}
