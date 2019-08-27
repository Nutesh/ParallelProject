package com.capgemini.bank.dao;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;

public class BankDAOImpl implements BankDAO {
	
	
	
	
	@Override
	public boolean addAccount(Account account) {
		if (account != null) {
			accounts.put(account.getAccountNo(), account);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAccount(Account account) {
		if (account != null) {
			accounts.put(account.getAccountNo(), account);
			return true;
		} else {
			return false;
		}
	}

	

	@Override
	public boolean debit(Transaction transaction) {
		if (transaction != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean credit(Transaction transaction) {
		if (transaction != null) {
			return true;
		} else {
			return false;
		}
	}

}
