package com.capgemini.bank.presentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Transaction;
import com.capgemini.bank.dao.BankDaoImpl;
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.service.BankServiceImpl;

public class MainUI {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BankServiceImpl serviceImpl = new BankServiceImpl();
		String transactionNo = "";

		do {
			try {
				System.out.println("***********************Welcome to XYZ Bank*************************");
				System.out.println("1.Create Account \n2.Login\n3.Exit");
				int userInput = 0;
				scanner = new Scanner(System.in);
				userInput = scanner.nextInt();
				switch (userInput) {
				case 1: {

					String firstName, middleName, lastName, gender;
					String mobileNo = "";
					int pin = 0;

					System.out.println("Please enter user details : \n");
					boolean firstNameValidated = false;
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter First Name : ");
						firstName = scanner.nextLine();

						try {

							firstNameValidated = serviceImpl.validateName(firstName);
						} catch (BankException e) {
							System.err.println(e.getMessage());
						}
					} while (!firstNameValidated);

					boolean middleNameValidated = false;
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter Middle Name : ");
						middleName = scanner.nextLine();
						try {
							middleNameValidated = serviceImpl.validateMiddleName(middleName);
						} catch (BankException e) {

							System.err.println(e.getMessage());
						}
					} while (!middleNameValidated);

					boolean lastNameValidated = false;
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter Last Name : ");
						lastName = scanner.nextLine();
						try {
							lastNameValidated = serviceImpl.validateName(lastName);
						} catch (BankException e) {
							System.err.println(e.getMessage());
						}
					} while (!lastNameValidated);

					boolean mobileValidated = false;
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter MobileNo. : ");
						mobileNo = scanner.nextLine();
						try {
							mobileValidated = serviceImpl.validateMobile(mobileNo);
						} catch (BankException e) {
							System.err.println(e.getMessage());
						}
					} while (!mobileValidated);

					boolean genderValidated = false;
					do {
						scanner = new Scanner(System.in);
						System.out.println("Enter Gender : ");
						gender = scanner.nextLine();
						try {
							genderValidated = serviceImpl.validateGender(gender);
						} catch (BankException e) {
							System.err.println(e.getMessage());
						}
					} while (!genderValidated);

					boolean pinValidated = false;
					int reenteredPin = 0;
					do {
						scanner = new Scanner(System.in);

						try {
							System.out.println("Enter Pin : ");
							pin = scanner.nextInt();
							pinValidated = serviceImpl.validatePin(pin);
							boolean repinValidated = false;
							do {
								scanner = new Scanner(System.in);

								try {
									System.out.println("Reenter Pin : ");
									reenteredPin = scanner.nextInt();
									repinValidated = serviceImpl.validatePin(reenteredPin);
									if (reenteredPin != pin) {
										repinValidated = false;
										System.err.println("Please enter same pin again");
									}
									System.out.println("Your pin is successfully created !!");
								} catch (BankException e) {

									System.err.println(e.getMessage());
								} catch (InputMismatchException e) {

									System.err.println("Please enter numeric value !!");
								}

							} while (!repinValidated);
						} catch (BankException e) {

							System.err.println(e.getMessage());
						} catch (InputMismatchException e) {

							System.err.println("Please enter numeric value !!");
						}

					} while (!pinValidated);

					Account account = new Account(firstName, middleName, lastName, mobileNo, gender, pin);
					try {

						long accountNo = serviceImpl.createAccount(account);
						System.out.println("Your account is created with account no : " + accountNo);
					} catch (BankException e) {
						System.err.println(e.getMessage());
					}
				}
					break;

				case 2:
					
					BankDaoImpl dao = new BankDaoImpl();
					/*dao.stopTrans();*/
					long accountNo = 0;
					boolean accountValidated = false;
					do {
						try {
							scanner = new Scanner(System.in);
							System.out.println("Enter Account : ");
							accountNo = scanner.nextLong();
							String accountName = serviceImpl.getAccountName(accountNo);

							if (!accountName.equals("")) {
								accountValidated = true;
							}
						} catch (NullPointerException e) {

							System.err.println("This Account doesn't exists");
						} catch (InputMismatchException e) {
							System.err.println("Please enter numeric value");
						}
					} while (!accountValidated);
					int pin = 0;
					String accountName = "";
					boolean pinValidated = false;
					do {
						scanner = new Scanner(System.in);
						int choice = 0;
						try {
							System.out.println("Enter Pin : ");

							pin = scanner.nextInt();
							pinValidated = serviceImpl.validatePin(pin);
							accountName = serviceImpl.validateAccount(accountNo, pin);

							if (accountName == "") {
								System.err.println("Invalid Account Number or Pin");
							} else {
								pinValidated = true;

								System.out.println("Account Name : " + accountName);
								do {
									try {
										long destinationAccountNo = 0;
										System.out.println(
												"\n\n1.Show Balance\n2.Deposit\n3.Withdraw\n4.Fund Transfer\n5.Show Recent Transactions\n6.Show All Transactions\n7.Logout");
										scanner = new Scanner(System.in);
										choice = scanner.nextInt();
										switch (choice) {

										case 1: {

											try {

												double balance = serviceImpl.showBalance(accountNo);
												System.out.println("Your Balance is : " + balance);
											} catch (InputMismatchException e) {
												System.err.println("Please enter a valid account number");
											} catch (BankException e) {
												System.err.println(e.getMessage());
											}
										}
											break;

										case 2: {

											double amount = 0;
											boolean amountValidated = false;
											do {

												try {
													scanner = new Scanner(System.in);
													System.out.println("Enter Amount : ");
													amount = scanner.nextDouble();
													amountValidated = serviceImpl.validateAmount(amount, accountNo,
															"credit");
												} catch (BankException e) {

													System.err.println(e.getMessage());
												} catch (InputMismatchException e) {

													System.err.println("Please enter numeric value");
												}
											} while (!amountValidated);
											String type = "credit";

											Transaction transaction = new Transaction(accountNo, type, amount,
													accountNo);
											try {
												transactionNo = serviceImpl.deposit(transaction);
												System.out.println("Your account is credited with Rs : " + amount
														+ "\n And your transaction id is : " + transactionNo);
											} catch (BankException e) {
												System.err.println(e.getMessage());
											}

										}

											break;

										case 3: {

											double amount = 0;
											boolean amountValidated = false;
											do {

												try {
													scanner = new Scanner(System.in);
													System.out.println("Enter Amount : ");
													amount = scanner.nextDouble();
													amountValidated = serviceImpl.validateAmount(amount, accountNo,
															"debit");
												} catch (BankException e) {

													System.err.println(e.getMessage());
												} catch (InputMismatchException e) {
													System.err.println("Please enter numeric value");
												}
											} while (!amountValidated);
											String type = "debit";

											Transaction transaction = new Transaction(accountNo, type, amount,
													accountNo);

											try {
												transactionNo = serviceImpl.withdraw(transaction);
												System.out.println("Your account is debited with Rs : " + amount
														+ "\n And your transaction id is : " + transactionNo);
											} catch (BankException e) {
												System.err.println(e.getMessage());
											}

										}

											break;

										case 4: {
											
											boolean recieverAccountValidated = false;
											do {

												try {
													System.out.println("Enter reciever account no. :");
													scanner=new Scanner(System.in);
													destinationAccountNo = scanner.nextLong();
													if(destinationAccountNo==accountNo) {
														System.err.println("You can only deposit to your own account not fund transfer");
														continue;
													}
													String destAccountName = serviceImpl
															.getAccountName(destinationAccountNo);
													System.out.println("Account Name : " + destAccountName);
													if (!destAccountName.equals("")) {
														recieverAccountValidated = true;
													}
												} catch (NullPointerException e) {

													System.err.println("This Account doesn't exists");
												} catch (InputMismatchException e) {
													System.err.println("Please enter numeric value");
												}
											} while (!recieverAccountValidated);
											double amount = 0;
											boolean amountValidated = false;
											do {

												try {
													scanner = new Scanner(System.in);
													System.out.println("Enter Amount : ");
													amount = scanner.nextDouble();
													amountValidated = serviceImpl.validateAmount(amount, accountNo,
															"debit");
												} catch (BankException e) {

													System.err.println(e.getMessage());
												} catch (InputMismatchException e) {
													System.err.println("Please enter numeric value");
												}
											} while (!amountValidated);
											String type = "debit";

											Transaction transaction = new Transaction(accountNo, type, amount,
													destinationAccountNo);
											try {
												transactionNo = serviceImpl.fundTransfer(transaction);
												System.out.println("Your have transferred Rs : " + amount + " to "
														+ serviceImpl.getAccountName(destinationAccountNo)
														+ "\n And your transaction id is : " + transactionNo);
											} catch (BankException e) {
												System.err.println(e.getMessage());
											}

										}
											break;
										case 5: {

											List<Transaction> transactions = new ArrayList<>();
											try {
												transactions = serviceImpl.showAllTransactions(accountNo);
											} catch (InputMismatchException | BankException e) {
												System.err.println(e.getMessage());
											}
											System.out.println("\nTransactionId\t|SourceAccount\t|Type\t|Amount\t|Date\t   Time\t\t|DestinationAccount");
											System.out.println("________________|_______________|_______|_______|_______________________|__________________");
											int count = 0;
											for (Iterator<Transaction> iterator = transactions.iterator(); iterator
													.hasNext() && count < 10;) {
												Transaction transaction = (Transaction) iterator.next();
												System.out.println(transaction);
												System.out.println("-------------------------------------------------------------------------------------------");
												count++;
											}
										}
											break;

										case 6: {

											ArrayList<Transaction> transactions = null;
											try {
												transactions = serviceImpl.showAllTransactions(accountNo);
												System.out.println("\nTransactionId\t|SourceAccount\t|Type\t|Amount\t|Date\t   Time\t\t|DestinationAccount");
												System.out.println("________________|_______________|_______|_______|_______________________|__________________");
												for (Iterator<Transaction> iterator = transactions.iterator(); iterator
														.hasNext();) {
													Transaction transaction = (Transaction) iterator.next();
													System.out.println(transaction);
													System.out.println("-------------------------------------------------------------------------------------------");
												}
											} catch (InputMismatchException | BankException e) {
												System.err.println(e.getMessage());
											}

										}
											break;
										}
									}

									catch (InputMismatchException e) {

										System.err.println("Please enter numeric value !!");
									}
								
								}
								
								while (choice != 7);
								///

							}
						} catch (BankException e) {

							System.err.println(e.getMessage());
						} catch (InputMismatchException e) {

							System.err.println("Please enter numeric value !!");
						}

					} while (!pinValidated);

					break;

				case 3: {
					scanner.close();
					System.out.println("Thanks Visit Again !!!!!!");
					System.exit(0);
				}
					break;
				

				default:
					System.err.println("Please enter input between 1 or 2 or 3 only !!");
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter numeric value !!");
			}
		} while (true);

	}
}
