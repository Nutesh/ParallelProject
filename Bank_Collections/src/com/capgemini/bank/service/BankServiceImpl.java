package com.capgemini.bank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.BankDAO;
import com.capgemini.bank.dao.BankDAOImpl;
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.utility.BankDB;

public class BankServiceImpl implements BankService {

	static long accountNo = 7412580000005L;
	double newBalance = 0, oldBalance = 0;
	BankDAO bank = new BankDAOImpl();

	public void initializeBank() {
		BankDB.accountInitialize();
		BankDB.transactionInitialize();
	}

	/**
	 * 
	 */
	@Override
	public List<Transaction> showRecentTransactions(long accountNo) {
		
		List<Transaction> transactions = new ArrayList<>();
		for (Map.Entry<String, Transaction> entry : BankDAO.transactions.entrySet()) {
			if (entry.getValue().getAccountNo() == accountNo
					|| entry.getValue().getDestinationAccountNo() == accountNo) {
				transactions.add(entry.getValue());
			}
			
		}

		Collections.reverse(transactions);
		return transactions;
	}

	/**
	 * @param
	 */
	@Override
	public List<Transaction> showAllTransactions(long accountNo) {
		List<Transaction> transactions = new ArrayList<>();
		for (Map.Entry<String, Transaction> entry : BankDAO.transactions.entrySet()) {
			if (entry.getValue().getAccountNo() == accountNo
					|| entry.getValue().getDestinationAccountNo() == accountNo) {
				transactions.add(entry.getValue());
			}

		}
		
		Collections.reverse(transactions);
		return transactions;
	}

	@Override
	public double showBalance(long accountNo) throws BankException {
		boolean exists = false;
		for (Entry<Long, Account> entry : BankDAO.accounts.entrySet()) {
			if (entry.getKey() == accountNo)
				exists = true;
		}
		if (exists) {
			System.out.println(BankDAO.accounts.get(accountNo).getBalance());
			return BankDAO.accounts.get(accountNo).getBalance();
		} else
			throw new BankException("This Bank Account Doesn't exists");
	}

	@Override
	public long createAccount(Account account) throws BankException {
		
		++accountNo;
		account.setAccountNo(accountNo);
		if (bank.addAccount(account)) {

			
			return accountNo;
		} else {
			throw new BankException("There was some error in creating account please try again");
		}

	}

	

	@Override
	public boolean deposit(Transaction transaction) {

		if (transaction != null) {
			if (transaction.getType() == "credit") {
				oldBalance = BankDAO.accounts.get(transaction.getAccountNo()).getBalance();
				newBalance = oldBalance + transaction.getAmount();
				BankDAO.accounts.get(transaction.getAccountNo()).setBalance(newBalance);
				BankDAO.transactions.put(transaction.getTransactionNo(), transaction);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean fundTransfer(Transaction transaction) {
		if (transaction != null) {
			if (transaction.getType() == "debit") {
				oldBalance = BankDAO.accounts.get(transaction.getAccountNo()).getBalance();
				newBalance = oldBalance - transaction.getAmount();
				BankDAO.accounts.get(transaction.getAccountNo()).setBalance(newBalance);
				oldBalance = BankDAO.accounts.get(transaction.getDestinationAccountNo()).getBalance();
				newBalance = oldBalance + transaction.getAmount();
				BankDAO.accounts.get(transaction.getDestinationAccountNo()).setBalance(newBalance);
				BankDAO.transactions.put(transaction.getTransactionNo(), transaction);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean withdraw(Transaction transaction) {
		if (transaction != null) {
			if (transaction.getType() == "debit") {
				oldBalance = BankDAO.accounts.get(transaction.getAccountNo()).getBalance();
				newBalance = oldBalance - transaction.getAmount();
				BankDAO.accounts.get(transaction.getAccountNo()).setBalance(newBalance);
				BankDAO.transactions.put(transaction.getTransactionNo(), transaction);
			}
			return true;
		}
		return false;
	}

	public String getAlphaNumericString() {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(7);

		for (int i = 0; i < 7; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public String getTransactionNo() {
		Long transactionNo;
		transactionNo = (long) (Math.random() * 10000000000L);
		String fullTransactionNo = "txn" + transactionNo.toString();
		return fullTransactionNo;
	}

	public String getAccountName(long accountNo) throws NullPointerException, InputMismatchException {
		String name = "";
		name = BankDAO.accounts.get(accountNo).getFirstName() + " " + BankDAO.accounts.get(accountNo).getLastName();
		return name;
	}

	public boolean validateMobile(String mobile) throws BankException, InputMismatchException {
		String mobilePtn = "[6-9]{1}[0-9]{9}";
		if (!Pattern.matches(mobilePtn, mobile)) {
			throw new BankException("Mobile number should be in digits and have exactly 10 digits");
		} else
			return true;
	}

	public boolean validateMiddleName(String name) throws BankException {
		String nameRegEx = "[A-Za-z- ]{1,20}";
		if (!Pattern.matches(nameRegEx, name)) {
			throw new BankException("first letter should be capital and length must be in between 5 to 10\n"
					+ "if you don't have middle name then press \"-\" \n");
		} else
			return true;
	}

	public boolean validateName(String name) throws BankException {
		String nameRegEx = "[a-zA-Z ]{1,20}";
		if (!Pattern.matches(nameRegEx, name)) {
			throw new BankException("please enter a valid name and length must be more than 20 letters \n");
		} else
			return true;
	}

	public boolean validateGender(String name) throws BankException {

		if (name.equalsIgnoreCase("Male") || name.equalsIgnoreCase("Other") || name.equalsIgnoreCase("Female")) {
			return true;

		} else
			throw new BankException("please enter male female or other \n");
	}

	public boolean validateAmount(double amount, long accountNo,String type) throws BankException, InputMismatchException {
		boolean amountValidated = false; 
		if(type == "debit")
		 {
			 if (amount <= 0 || amount > 20000) {
			
				throw new BankException("Amount must be greater than 0 and not more than 2000");
			}
			if (BankDAO.accounts.get(accountNo).getBalance() < amount) {
				throw new BankException("Your balance is less than entered amount");
			}
			amountValidated = true;
		 }
		 else if(type == "credit")
		 {
			 if (amount <= 0 || amount > 20000) {
			
				throw new BankException("Amount must be greater than 0 and not more than 2000");
			}
			amountValidated = true;
		 }
		return amountValidated;
	}

	

}
