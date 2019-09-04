package com.capgemini.bank.test;


import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.BankDaoImpl;

public class BankDaoImplTest {
static BankDaoImpl bankDAOImpl ;
Account account;
Transaction transaction;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bankDAOImpl =  new BankDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	bankDAOImpl = null;
	}

	@Before
	public void setUp() throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		account = new Account(7412580000008L, "Shiv", "Prasad", "Tripathi", "7418000030", "Male", 125000);
		transaction = new Transaction("txn28538987",7412580000001L,"debit",5200,dtf.format(now),7412580000003L);
	}

	@After
	public void tearDown() throws Exception {
		account = null;
	}

	@Test
	public void addAccountTest() {
		long accountNo = bankDAOImpl.insertAccount(account);
		assertNotNull(accountNo);
	}
	
	@Test
	public void addTransactionTest() {
		String transactionId = bankDAOImpl.insertTransaction(transaction);
		assertNotNull(transactionId);
	}
	
	@Test
	public void updateBalanceTest() {
		int balanceUpdated = bankDAOImpl.updateBalance(account.getAccountNo(), 500);
		assertEquals(balanceUpdated, 1);
	}
	
	@Test
	public void showBalanceTest() {
		double balance = bankDAOImpl.showBalance(account.getAccountNo());
		assertTrue(balance == 500);
	}

	@Test
	public void validateAccountTest() {
		String accountName = bankDAOImpl.validateAccount(account.getAccountNo(), account.getPin());
		assertEquals(account.getFirstName()+account.getLastName(),accountName);
	}
}
