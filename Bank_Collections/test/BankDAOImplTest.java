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
import com.capgemini.bank.dao.BankDAOImpl;

public class BankDAOImplTest {
static BankDAOImpl bankDAOImpl ;
Account account;
Transaction transaction;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bankDAOImpl =  new BankDAOImpl();
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
		boolean accountAdded = bankDAOImpl.addAccount(account);
		assertTrue(accountAdded);
	}
	
	@Test
	public void updateAccountTest() {
		boolean accountUpdated = bankDAOImpl.updateAccount(account);
		assertTrue(accountUpdated);
	}
	
	@Test
	public void debitTest() {
		boolean accountDebited = bankDAOImpl.debit(transaction);
		assertTrue(accountDebited);
	}
	
	@Test
	public void creditTest() {
		boolean accountCredited = bankDAOImpl.credit(transaction);
		assertTrue(accountCredited);
	}

}
