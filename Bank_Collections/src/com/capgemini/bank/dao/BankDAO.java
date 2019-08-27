package com.capgemini.bank.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;

public interface BankDAO {
	/**
	 * 
	 */
	Map<Long, Account> accounts = new HashMap<>();
	
	Map<String, Transaction> transactions = new LinkedHashMap<>();
	/**
	 * 
	 * @param account
	 * @return
	 */
	public boolean addAccount(Account account);

	public boolean updateAccount(Account account);

	public boolean debit(Transaction transaction);

	public boolean credit(Transaction transaction);

}
