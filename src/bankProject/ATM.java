package bankProject;

import java.util.Scanner;

public class ATM extends BankingRules implements StandardProcess {

	static Scanner scan = new Scanner(System.in);

	static int next;
	static double balance;
	int cardNumber;
	int pin;

	int dailyWithdrawalLimit = dailyWithdrawalLimit(500);
	int numberTransactions = noOFTransactions(3);

	public int dailyWithdrawalLimit(int dailyWithdrawLimit) {
		this.dailyWithdrawalLimit = dailyWithdrawLimit;
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
				System.out.println("Welcome " + account.name + ". Please enter the pin");
			}

			pin = scan.nextInt();

			// to ensure user will not be able to access the ATM services if wrong pin is
			// entered 3 times
			for (int i = 2; i > 0; i--) {
				if (pin == (account.pinValue(account.accountNo, account.pin))) {
					transaction();
				} else {
					System.out.println("Enter valid pin");
				}

				pin = scan.nextInt();
				if (pin == (account.pinValue(account.accountNo, account.pin))) {
					transaction();
				}
			}
		} else {
			System.out.println("Wrong account number. The session is terminated. Start again.");
		}
	} // to restart the process if wrong account number is used

	public void transaction() {

		int option;

		System.out.println("Please select the type of transaction: ");
		System.out.println("1: Deposit, 2: Withdrawal, 3: Fund transfer, 4: Investment, 5: Check balance");

		numberTransactions--;
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
		if (numberTransactions != 0) {
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

	public void deposit() {
		// TODO Auto-generated method stub
		double deposit;
		System.out.println("Please enter an amount to deposit:");
		deposit = scan.nextDouble();
		balance += deposit;
		System.out.println("Thank you. Your new balance is: " + balance);
		newTransaction();
	}

	public void withdraw() {
		// TODO Auto-generated method stub
		double withdraw;
		System.out.println("Please enter an amount to withdraw:");
		withdraw = scan.nextDouble();
		if (withdraw > balance && withdraw < dailyWithdrawalLimit) {
			System.out.println("Insufficient funds");
		} else if (withdraw < balance && withdraw > dailyWithdrawalLimit) {
			System.out.println("You have exceeded the daily withdrawal limit");
		} else {
			balance -= withdraw;
			dailyWithdrawalLimit = (int) (dailyWithdrawalLimit - withdraw);
			System.out.println("Thank you. Your new balance is: " + balance);
		}
		newTransaction();
	}

	public void fundTransfer() {
		// TODO Auto-generated method stub

		double fund;
		System.out.println("Please enter an account number for fund transfer");
		int otherAccount = scan.nextInt();
		System.out.println("Please enter an amount for fund transfer:");
		fund = scan.nextDouble();
		if (fund > balance && fund != 0) {
			System.out.println("Insufficient balance. Please deposit sufficient money to complete your transaction");
		} else {
			balance -= fund;
			System.out.println("Thank you. Your new balance is: " + balance);
		}
		newTransaction();
	}

	public void investment() {
		// TODO Auto-generated method stub

		double invest;
		System.out.println("Please enter a registered plan or product for investment");
		String investType = scan.next();
		System.out.println("Please enter an amount for fund transfer:");
		invest = scan.nextDouble();
		if (invest > balance && invest != 0) {
			System.out.println("Insufficient balance. Please deposit sufficient money to complete your transaction");
		} else {
			balance -= invest;
			System.out.println("Thank you. Your new balance is: " + balance);
		}
		newTransaction();

		scan.close();
	}

}
