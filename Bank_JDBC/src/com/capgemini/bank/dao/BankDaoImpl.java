package com.capgemini.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import com.capgemini.bank.utility.DBConnection;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;

public class BankDaoImpl implements BankDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean status = false;
	int row = -1;
	long accountNo = 0;
	

	@Override
	public long insertAccount(Account account) {
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select accountSeq.NEXTVAL from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				accountNo = resultSet.getLong(1);
				statement = connection.prepareStatement("insert into accounts values(?,?,?,?,?,?,?,?)");
				statement.setLong(1, accountNo);
				statement.setString(2, account.getFirstName());
				statement.setString(3, account.getMiddleName());
				statement.setString(4, account.getLastName());
				statement.setLong(5, Long.parseLong(account.getMobileNo()));
				statement.setString(6, account.getGender());
				statement.setInt(7, account.getPin());
				statement.setDouble(8, account.getBalance());
				row = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountNo;
	}

	@Override
	public String insertTransaction(Transaction transaction) {
		String transactionNo=null;
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("select transactionSeq.NEXTVAL from dual");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				transactionNo = "txn" + resultSet.getInt(1);
				statement = connection.prepareStatement(
						"insert into transactions(transactionId,srcAccount,type,amount,destAccount) values(?,?,?,?,?)");
				statement.setString(1, transactionNo);
				statement.setLong(2, transaction.getAccountNo());
				statement.setString(3, transaction.getType());
				statement.setDouble(4, transaction.getAmount());
				statement.setLong(5, transaction.getDestinationAccountNo());
				row = statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionNo;

	}

	@Override
	public LinkedList<Transaction> showAllTransactions(Long accountNo) {
		LinkedList<Transaction> transactions = new LinkedList<>();
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection
					.prepareStatement("select * from transactions where srcaccount = ? or destaccount = ? order by timestamp desc");
			statement.setLong(1, accountNo);
			statement.setLong(2, accountNo);
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				transactions.add(new Transaction(resultSet.getString(1), resultSet.getLong(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getLong(6)));
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public int updateBalance(long accountNo, double balance) {
		try (Connection connection = DBConnection.getConnection();) {

			statement = connection.prepareStatement("update accounts set balance = ? where accountNo = ?");
			statement.setDouble(1, balance);
			statement.setLong(2, accountNo);
			row = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;

	}

	@Override
	public double showBalance(Long accountNo) {
		Double balance=0.0;
		try (Connection connection = DBConnection.getConnection();) {
			
			statement = connection.prepareStatement("select balance from accounts where accountNo = ?");
			statement.setLong(1, accountNo);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				balance=resultSet.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public String validateAccount(long accountNo, int pin) {
		String accountName="";
		try (Connection connection = DBConnection.getConnection();) {
			
			statement = connection.prepareStatement("select firstName,lastname from accounts where accountNo = ? and pin = ?");
			statement.setLong(1, accountNo);
			statement.setInt(2, pin);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				accountName=resultSet.getString(1)+" "+resultSet.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountName;
	}

	@Override
	public String getAccountName(long accountNo) {
		String accountName="";
		try (Connection connection = DBConnection.getConnection();) {
			
			statement = connection.prepareStatement("select firstName,lastname from accounts where accountNo = ?");
			statement.setLong(1, accountNo);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				accountName=resultSet.getString(1)+" "+resultSet.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountName;
	}

}
