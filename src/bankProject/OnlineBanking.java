package bankProject;

import java.util.Scanner;

public class OnlineBanking extends BankingRules implements StandardProcess {

	static Scanner scan = new Scanner(System.in);

	static int next;
	static double balance;
	int cardNumber;
	String password;

	int dailyWithdrawalLimit = dailyWithdrawalLimit(10000);
	int numberTransactions = noOFTransactions(4);

	public int dailyWithdrawalLimit(int dailyWitndrawalLimit) {
		this.dailyWithdrawalLimit = dailyWitndrawalLimit;
		return dailyWithdrawalLimit;
	}

	public int noOFTransactions(int numberOfTransaction) {
		this.numberTransactions = numberOfTransaction;
		return numberTransactions;
	}

	public void pinChange(String accountholder, int otp, int pin, int newPin) {

	}

	void userIdentification(AccountDetails account) {
		// TODO Auto-generated method stub
		cardNumber = scan.nextInt();

		if (cardNumber == account.accountNo) {
			{
				System.out.println("Welcome " + account.name + ". Please enter the password");
			}
			password = scan.next();

			for (int i = 3; i > 0; i--) {
				if (password.equals(account.getpassword())) {
					transaction();
				} else {
					System.out.println("Enter valid password");
				}

				password = scan.next();
				if (password.equals(account.getpassword())) {
					transaction();
				}
			}
			if (!password.equals(account.getpassword())) {
				System.out.println("Your account is blocked. Please contact customer care for assisstance");
			}
		}
		while (cardNumber != account.accountNo) {
			System.out.println("Enter valid account number");
			userIdentification(account);
		}

	}

	public void transaction() {

		int option;

		System.out.println("Please select the type of transaction: ");
		System.out.println("1: Deposit, 2: Withdrawal, 3: Fund transfer, 4: Investment, 5: Check balance");

		this.numberTransactions--;
		option = scan.nextInt();

		switch (option) {

		case 1:
			deposit();
			break;

		case 2:
			withdraw();
			break;

		case 3:
			fundTransfer();

			break;

		case 4:
			investment();
			break;

		case 5:
			System.out.println("Your chequing balance is: " + (balance + AccountDetails.chequing)
					+ " and your total fund value is: " + AccountDetails.totalFunds);
			newTransaction();
			break;

		default:
			System.out.println("Invalid option");
			newTransaction();
		}
	}

	public void newTransaction() {
		if (this.numberTransactions != 0) {
			{
				System.out.println(
						"Would you like to do a new transaction? Press 1 for another transaction or press 2 to log out");
				next = scan.nextInt();
				if (next == 1) {
					transaction();
				} else if (next == 2) {
					System.out.println("Thanks for banking with us. You are logged out!");
				} else {
					System.out.println("Invalid choice. Try again.");
					newTransaction();
				}
			}
		} else {
			System.out.println(
					"You have exceeded the number of transactions allowed.\nThanks for banking with us. You are logged out.");
		}
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		double deposit;
		System.out.println("Please enter an amount to deposit:");
		deposit = scan.nextDouble();
		balance = balance + AccountDetails.chequing + deposit;
		System.out.println("Thank you. Your new chequing balance is: " + balance);
		System.out.println("Total funds= " + (balance + AccountDetails.totalFunds));
		newTransaction();

	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		double withdraw;
		System.out.println("Please enter an amount to withdraw:");
		withdraw = scan.nextDouble();
		if (withdraw > balance && withdraw < this.dailyWithdrawalLimit) {
			System.out.println("Insufficient funds");
		} else if (withdraw < balance && withdraw > this.dailyWithdrawalLimit) {
			System.out.println("You have exceeded the daily withdrawal limit");
		} else {
			balance = balance + AccountDetails.chequing - withdraw;
			this.dailyWithdrawalLimit = (int) (this.dailyWithdrawalLimit - withdraw);
			System.out.println("Thank you. Your new chequing balance is: " + balance);
		}
		System.out.println("Total funds= " + (balance + AccountDetails.totalFunds));
		newTransaction();

	}

	@Override
	public void fundTransfer() {
		// TODO Auto-generated method stub
		int otherAccount;
		double fund;
		System.out.println("Please enter an account number for fund transfer");
		otherAccount = scan.nextInt();
		System.out.println("Please enter an amount for fund transfer:");
		fund = scan.nextDouble();
		if (fund > balance && fund != 0) {
			System.out.println("Insufficient balance. Please deposit sufficient money to complete your transaction");
		} else {
			balance = balance + AccountDetails.chequing - fund;

			System.out.println("Thank you. Your new chequing balance is: " + balance);
		}
		System.out.println("Total funds= " + (balance + AccountDetails.totalFunds));
		newTransaction();

	}

	@Override
	public void investment() {
		// TODO Auto-generated method stub
		String investType;
		double invest;
		System.out.println("Please enter a registered plan or product for investment: TFSA or RRSP");
		investType = scan.next();

		if (investType.equalsIgnoreCase("tfsa") || investType.equalsIgnoreCase("rrsp")) {
			System.out.println("Please enter an amount for fund transfer:");
		} else {
			System.out.println("Enter valid credentials");
			investment();
		}

		invest = scan.nextDouble();
		if (invest > balance && invest != 0) {
			System.out.println("Insufficient balance. Please deposit sufficient money to complete your transaction");
		} else {
			balance = balance + AccountDetails.investment - invest;
			System.out.println("Thank you. Your new investment balance is: " + balance);
		}
		System.out.println("Total funds= " + (balance + AccountDetails.totalFunds));
		newTransaction();

	}
}
