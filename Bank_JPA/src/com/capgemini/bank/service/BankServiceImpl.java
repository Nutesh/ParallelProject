package com.capgemini.bank.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.BankDao;
import com.capgemini.bank.dao.BankDaoImpl;
import com.capgemini.bank.exception.BankException;

public class BankServiceImpl implements BankService {
BankDaoImpl dao= new BankDaoImpl();

public String getAccountName(long accountNo) throws NullPointerException, InputMismatchException {
	String name = "";
	name=dao.getAccountName(accountNo);
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
		throw new BankException("please enter a valid name and length must not be more than 20 letters\n"
				+ "if you don't have middle name then press \"-\" \n");
	} else
		return true;
}

public boolean validateName(String name) throws BankException {
	String nameRegEx = "[a-zA-Z ]{1,20}";
	if (!Pattern.matches(nameRegEx, name)) {
		throw new BankException("please enter a valid name and length must not be more than 20 letters \n");
	} else
		return true;
}

public boolean validatePin(Integer pin) throws BankException,InputMismatchException {
	String nameRegEx = "[0-9]{4}";
	if (!Pattern.matches(nameRegEx, pin.toString())) {
		throw new BankException("please enter a valid pin length must be exact 4 digits only");
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
	double balance=dao.showBalance(accountNo);
	if(type == "debit")
	 {
		 if (amount <= 0 || amount > 20000) {
		
			throw new BankException("Amount must be greater than 0 and not more than 2000");
		}
		 if (amount > balance) {
				
				throw new BankException("You have insufficient balance");
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

	@Override
	public ArrayList<Transaction> showAllTransactions(long accountNo) throws BankException, InputMismatchException {
		
		return dao.showAllTransactions(accountNo);
	}

	@Override
	public long createAccount(Account account) throws BankException {
		
		return dao.insertAccount(account);
	}

	@Override
	public String validateAccount(long accountNo, int pin) throws BankException {
		if(dao.validateAccount(accountNo, pin)==null) {
			throw new BankException("Account Number or Pin is invalid");
			
		}
		else {
			return dao.validateAccount(accountNo, pin);
		}
		
	}

	@Override
	public String deposit(Transaction transaction) throws BankException {
		String transactionNo=dao.insertTransaction(transaction);
		double destAccountBalance = dao.showBalance(transaction.getDestinationAccountNo());
		destAccountBalance = destAccountBalance + transaction.getAmount();
		Account depositedAccount = dao.updateBalance(transaction.getDestinationAccountNo(), destAccountBalance);
		if(depositedAccount == null) {
			throw new BankException("There was some error in transaction ..\nPlease try again!!");
		}
		else
			return transactionNo;
	}

	@Override
	public String fundTransfer(Transaction transaction) throws BankException {
		String transactionNo=dao.insertTransaction(transaction);
		double destAccountBalance = dao.showBalance(transaction.getDestinationAccountNo());
		destAccountBalance = destAccountBalance + transaction.getAmount();
		Account plusAccount = dao.updateBalance(transaction.getDestinationAccountNo(), destAccountBalance);
		double sourceAccountBalance =  dao.showBalance(transaction.getAccountNo());
		sourceAccountBalance = sourceAccountBalance - transaction.getAmount();
		Account minusAccount = dao.updateBalance(transaction.getAccountNo(), sourceAccountBalance);
		if(plusAccount == null || minusAccount == null) {
			throw new BankException("There was some error in transaction ..\nPlease try again!!");
		}
		else
			return transactionNo;
	}

	@Override
	public String withdraw(Transaction transaction) throws BankException {
		String transactionNo=dao.insertTransaction(transaction);
		double destAccountBalance = dao.showBalance(transaction.getAccountNo());
		destAccountBalance = destAccountBalance - transaction.getAmount();
		Account withdawneAccount = dao.updateBalance(transaction.getDestinationAccountNo(), destAccountBalance);
		if(withdawneAccount == null) {
			throw new BankException("There was some error in transaction ..\nPlease try again!!");
		}
		else
			return transactionNo;
	}

	@Override
	public double showBalance(long accountNo) throws BankException, InputMismatchException {
		
		return dao.showBalance(accountNo);
	}
	
	public void continuemethod() {
		boolean flag=false;
		 
		do {
            System.out.println("Want to continue (Yes or No)");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("yes")) {
                flag = true;
            } else if (option.equalsIgnoreCase("no")) {
                System.out.println("Thanks for Banking with us!!");
                flag = false;
                System.exit(0);
            } else {
                flag = false;
                System.err.println("Please enter only yes or no");
            }
        } while (!flag);
	}
}
