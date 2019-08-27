package com.capgemini.bank.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;

public class BankDaoImpl implements BankDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-PU");
	EntityManager entityManager = factory.createEntityManager();
	@Override
	public long insertAccount(Account account) {
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		return account.getAccountNo();
	}
	@Override
	public String validateAccount(long accountNo, int pin)  {
		
		TypedQuery<Account> query = 
				entityManager.createQuery("from Account where accountNo=? and pin=?", Account.class);
		query.setParameter(1, accountNo);
		query.setParameter(2, pin);
		Account account = null;
		List<Account> accounts=query.getResultList();
		if(accounts.size()!=0) {
			for (Iterator iterator = accounts.iterator(); iterator.hasNext();) {
				account = (Account) iterator.next();
				}
			String fullName= account.getFirstName()+" "+account.getLastName();
			return fullName;
		}
		else {
			return null;
		}
		
	}

	@Override
	public String insertTransaction(Transaction transaction) {
		Query query = entityManager.createNativeQuery("select transactionseq.nextval from dual");
		BigDecimal transint =(BigDecimal) query.getSingleResult();
		String transactionNo="txn"+transint.toString();
		transaction.setTransactionNo(transactionNo);
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		return transaction.getTransactionNo();
	}

	@Override
	public Account updateBalance(long accountNo, double balance) {
		entityManager.getTransaction().begin();
		Account account = entityManager.find(Account.class,accountNo);
		account.setBalance(balance);
		account=entityManager.merge(account);
		entityManager.getTransaction().commit();
		return account;
	}

	@Override
	public ArrayList<Transaction> showAllTransactions(Long accountNo) {
		entityManager.getTransaction().begin();
		entityManager.clear();
		TypedQuery<Transaction> query = entityManager.createQuery("from Transaction where accountNo=? or destinationAccountNo=? order by time desc", Transaction.class);
		query.setParameter(1, accountNo);
		query.setParameter(2, accountNo);
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) query.getResultList();
		
		entityManager.getTransaction().commit();
		return transactions;
	}

	@Override
	public double showBalance(Long accountNo) {
		entityManager.getTransaction().begin();
		Account account = entityManager.find(Account.class,accountNo); 
		double balance=account.getBalance();
		entityManager.getTransaction().commit();
		return balance;
	}

	@Override
	public String getAccountName(long accountNo) {
		Account account = entityManager.find(Account.class,accountNo); 
		String fullName= account.getFirstName()+" "+account.getLastName();
		return fullName;
	}

}
