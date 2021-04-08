package bankProject;

import java.util.Scanner;

public class MainOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ATM atm = new ATM();

		OnlineBanking onlineBanking = new OnlineBanking();

		AccountDetails account = new AccountDetails(12346, "John");

		account.getTotalFunds(1000.0, 2500.75, 6048.89);

		// account.setPin(1234);//setting pin for ATM trans

		atm.pin = account.pinValue(account.accountNo, account.pin); // comparing the pin value with account key

		account.setPassword("abcd"); // setting password for online

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to ABC bank. Please use our ATM or Online banking services for your transactions!");
		System.out.println("Please opt for ATM or Online Banking");

		String UI = scan.next();

		if (UI.trim().equalsIgnoreCase("ATM")) {

			System.out.println("Insert card");

			atm.userIdentification(account);

		}

		else if (UI.trim().equalsIgnoreCase("Online")) {

			System.out.println("Enter account number");

			onlineBanking.userIdentification(account);

		}

		scan.close();
	}

}
