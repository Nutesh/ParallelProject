package demos;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.dao.BankDAO;
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.utility.BankDB;

public class FuncTest {

	public boolean validateMobile(String mobile) throws BankException, InputMismatchException {
		String mobilePtn = "[6-9]{1}[0-9]{9}";
		if (!Pattern.matches(mobilePtn, mobile)) {
			throw new BankException("Mobile number should be in digits and have exactly 10 digits");
		} else
			return true;
	}

	public boolean validateName(String name) throws BankException {
		String nameRegEx = "[A-Z]{1}[a-zA-Z]{2,10}";
		if (!Pattern.matches(nameRegEx, name)) {
			throw new BankException("first letter should be capital and length must be in between 5 to 10 \n");
		} else
			return true;
	}

	public boolean validateMiddleName(String name) throws BankException {
		String nameRegEx = "[A-Z-]{1}[a-zA-Z]*";
		if (!Pattern.matches(nameRegEx, name)) {
			throw new BankException("first letter should be capital and length must be in between 5 to 10\n"
					+ "if you don't have middle name then press enter\n");
		} else
			return true;
	}
	
	public boolean validateEmail(String email) throws BankException {
		String emailRegEx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		if (!Pattern.matches(emailRegEx, email)) {
			throw new BankException("first letter should be capital and length must be in between 5 to 10\n"
					+ "if you don't have middle name then press enter\n");
		} else
			return true;
	}

	public boolean validateAmount(int amount) throws BankException, InputMismatchException {

		if (amount <= 0 || amount > 20000) {
			throw new BankException("Amount must be greater than 0 and not more than 2000");
		} else
			return true;
	}

	public String validateAccount(long accountNo) throws BankException {
		boolean validateAmount = false;
		String name = "";
		for (Map.Entry<Long, Account> entry : BankDAO.accounts.entrySet()) {
			if (entry.getKey() == accountNo) {
				validateAmount = true;
				System.out.println("abcs");
				name = BankDAO.accounts.get(accountNo).getFirstName() + " " +BankDAO.accounts.get(accountNo).getLastName();
			}
		}
		if (!validateAmount) {
			throw new BankException("This Bank Account Doesn't Exists");
		}
		return name;
	}
	
	public boolean userContinue(String input) throws BankException  {
		boolean willContinue = false;
		Scanner scanner = new Scanner(System.in);
		
		if(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
			scanner.close();
			throw new BankException("Please enter only yes or no");
		}
		else 
		{
			willContinue = true;
			scanner.close();
		}
		return willContinue;
	}
	
	
	public String getAccountName(long accountNo) throws InputMismatchException,NullPointerException{
		String name = "";
		name = BankDAO.accounts.get(accountNo).getFirstName() + " " +BankDAO.accounts.get(accountNo).getLastName(); 
		
		return name;
	}

	public static void main(String[] args) {
		FuncTest funcTest = new FuncTest();
		Scanner scanner = new Scanner(System.in);
		BankDB.accountInitialize();
		
		 Logger LOGGER =  
	            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
		
		do {
			LOGGER.log(Level.INFO, "My first Log Message \n");
			LOGGER.info("Hello\n");
			System.out.println(
					"Enter 1.Mobile Validation : \n2.Name Validation\n3.Middle Name Validation\n4.Amount Validation\n5.Account Validation\n6.Exit");
			int choice = scanner.nextInt();
			switch (choice) {

			case 1: {
				boolean mobileValidated = false;
				do {
					
					System.out.println("Enter MobileNo. : ");
					String mobile = scanner.next();
					try {
						mobileValidated = funcTest.validateMobile(mobile);
						System.out.println("Mobile Validated");
						System.out.println(mobileValidated);
						
					} catch (BankException e) {
						System.err.println(e.getMessage());
					}
				} while (!mobileValidated);
				String continueChoice;
				boolean continueValue;
				do {
	                scanner = new Scanner(System.in);
	                System.out.println("do you want to continue again [yes/no]");
	                continueChoice = scanner.nextLine();
	                if (continueChoice.equalsIgnoreCase("yes")) {
	                    continueValue = true;
	                    break;
	                } else if (continueChoice.equalsIgnoreCase("no")) {
	                    System.out.println("thank you");
	                    System.exit(0);
	                    continueValue = false;
	                    break;
	                } else {
	                    System.out.println("enter yes or no");
	                    continueValue = false;
	                    continue;
	                }
	            } while (!continueValue);
				
				
			}
				break;

			case 2: {
				boolean nameValidated = false;
				do {

					System.out.println("Enter First Name only : ");
					String name = scanner.next();
					try {
						nameValidated = funcTest.validateName(name);
						System.out.println("Name Validated");
						System.out.println(nameValidated);
					} catch (BankException e) {
						System.err.println(e.getMessage());
					}
				} while (!nameValidated);

			}
				break;

			case 3: {
				boolean middleNameValidated = false;
				do {

					System.out.println("Enter Middle Name only : ");
					String name = scanner.next();
					try {
						middleNameValidated = funcTest.validateMiddleName(name);
						System.out.println("Middle Name Validated");
						System.out.println(middleNameValidated);
					} catch (BankException e) {
						System.out.println(middleNameValidated);
						System.err.println(e.getMessage());
					}
				} while (!middleNameValidated);

			}
				break;

			case 4: {
				boolean amountValidated = false;
				do {

					try {
						System.out.println("Enter Amount : ");
						int amount = scanner.nextInt();
						amountValidated = funcTest.validateAmount(amount);
						System.out.println("Amount Validated");
						System.out.println(amountValidated);
					} catch (BankException e) {
						System.out.println(amountValidated);
						System.err.println(e.getMessage());
					} catch (InputMismatchException e) {
						scanner.next();
						System.out.println(amountValidated);
						System.err.println("Please enter numeric value");
					}
				} while (!amountValidated);

			}
				break;

			case 5: {
				boolean accountValidated = false;
				do {

					try {
						System.out.println("Enter Account : ");
						long account = scanner.nextLong();
						String accountName = funcTest.getAccountName(account);
						System.out.println("Account Name : "+accountName);
						if (!accountName.equals("")) {
							accountValidated = true;
							System.out.println("Account Validated");
							System.out.println(accountValidated);
						}
					} catch (NullPointerException e) {
						System.out.println(accountValidated);
						System.err.println("This Account doesn't exists");
					} catch (InputMismatchException e) {
						scanner.next();
						System.out.println(accountValidated);
						System.err.println("Please enter numeric value");
					}
				} while (!accountValidated);

			}
				break;
			
			case 6 :
			{
				scanner.close();
				System.exit(0);
			}
				break;
				
			case 7: {
				boolean mobileValidated = false;
				boolean userContinue = false;
				do {
					
				do {
					
					System.out.println("Enter MobileNo. : ");
					String mobile = scanner.next();
					try {
						mobileValidated = funcTest.validateMobile(mobile);
						System.out.println("Mobile Validated");
						System.out.println(mobileValidated);
						
					} catch (BankException e) {
						System.err.println(e.getMessage());
					}
				} while (!mobileValidated);
				try {
					System.out.println("Do you wish to continue");
					String input = scanner.next();
					userContinue = funcTest.userContinue(input);
				} catch (BankException e) {
					System.err.println(e.getMessage());
				}
				}while(!userContinue);
				
			}
				break;
				
			default:
				break;
			}
		} while (true);
	}

}