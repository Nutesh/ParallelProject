package com.capgemini.bank.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.BankDAO;

public class BankDB {
	public static void accountInitialize() {
		BankDAO.accounts.put(7412580000001L,
				new Account(7412580000001L, "Ashok", "Kumar", "Sharma", "7418529630", "Male", 25000));
		BankDAO.accounts.put(7412580000002L,
				new Account(7412580000002L, "Shivam", "-", "Reddy", "9638527410", "Male", 45000));
		BankDAO.accounts.put(7412580000003L,
				new Account(7412580000003L, "Tripti", "-", "Srivastava", "7894561230", "Female", 30000));
		BankDAO.accounts.put(7412580000004L,
				new Account(7412580000004L, "Anandi", "Ben", "Patel", "8527419630", "Female", 50000));

	}
	
	public static void transactionInitialize() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		BankDAO.transactions.put("txn28538977", new Transaction("txn28538977",7412580000004L,"debit",5000,dtf.format(now),7412580000002L));
		BankDAO.transactions.put("txn28438977", new Transaction("txn28438977",7412580000003L,"credit",5000,dtf.format(now),7412580000003L));
		BankDAO.transactions.put("txn28558977", new Transaction("txn28558977",7412580000002L,"debit",5000,dtf.format(now),7412580000004L));
		BankDAO.transactions.put("txn28532877", new Transaction("txn28532877",7412580000001L,"credit",5000,dtf.format(now),7412580000004L));
		BankDAO.transactions.put("txn28538707", new Transaction("txn28538707",7412580000002L,"debit",5000,dtf.format(now),7412580000003L));
		BankDAO.transactions.put("txn28538787", new Transaction("txn28538787",7412580000003L,"credit",5000,dtf.format(now),7412580000001L));
	
		
		
	}
}
