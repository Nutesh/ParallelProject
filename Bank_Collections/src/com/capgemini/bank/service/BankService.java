package com.capgemini.bank.service;
/**
 * 
 */
import java.util.InputMismatchException;
import java.util.List;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.exception.BankException;

public interface BankService {
	
	/**
	 * 
	 * @param accountNo
	 * @return
	 * @throws BankException
	 * @throws InputMismatchException
	 */
	public List<Transaction> showRecentTransactions(long accountNo) throws BankException, InputMismatchException;

	public List<Transaction> showAllTransactions(long accountNo) throws BankException, InputMismatchException;

	public long createAccount(Account account) throws BankException;



	public boolean deposit(Transaction transaction) throws BankException;

	public boolean fundTransfer(Transaction transaction) throws BankException;

	public boolean withdraw(Transaction transaction) throws BankException;

	double showBalance(long accountNo) throws BankException, InputMismatchException;
}
