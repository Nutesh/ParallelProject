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
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.service.BankServiceImpl;
import com.capgemini.bank.utility.BankDB;

public class BankServiceImplTest {
static BankServiceImpl bankServiceImpl;
Transaction transaction;
Account account;
BankDB bankDB;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bankServiceImpl = new BankServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bankServiceImpl = null;
	}

	@Before
	public void setUp() throws Exception {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		account = new Account(7412580000001L, "Ashok", "Kumar", "Sharma", "7418529630", "Male", 25000);
		transaction = new Transaction("txn28538977",7412580000004L,"debit",5000,dtf.format(now),7412580000002L);
		BankDB.accountInitialize();
		BankDB.transactionInitialize();
	}

	@After
	public void tearDown() throws Exception {
		account = null;
		transaction = null;
	}

	@Test
	public void showRecentTransactionsTest() {
		assertNotNull(bankServiceImpl.showRecentTransactions(account.getAccountNo()));
	}
	
	@Test
	public void showAllTransactionsTest() {
		assertNotNull(bankServiceImpl.showRecentTransactions(account.getAccountNo()));
	}
	
	@Test
	public void createAccountTest() {
		try {
			assertNotNull(bankServiceImpl.createAccount(account));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fundTransferTest() {
		assertTrue(bankServiceImpl.fundTransfer(transaction));
	}
	
	@Test
	public void withdrawTest() {
		assertTrue(bankServiceImpl.withdraw(transaction));
	}
	
	@Test
	public void showBalanceTest() {
		try {
			assertNotNull(bankServiceImpl.showBalance(account.getAccountNo()));
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
