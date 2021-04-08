package bankProject;

import java.util.HashMap;

public class AccountDetails {

	int accountNo;

	String name;

	static double totalFunds;

	static double chequing;

	static double investment;

	double balance;

	int pin;

	private String password;

	AccountDetails(int a, String n) {
		accountNo = a;
		name = n;
	}

	public int pinValue(int accountNo, int pin) {
		HashMap<Integer, Integer> atmPin = new HashMap<Integer, Integer>();
		this.accountNo = accountNo;
		this.pin = pin;

		atmPin.put(12345, 1234);
		atmPin.put(12346, 2345);
		atmPin.put(12347, 3456);

		return atmPin.get(accountNo);
	}

	public double getTotalFunds(double chequing, double savings, double investment) {

		this.chequing = chequing;

		this.investment = investment;

		this.totalFunds = chequing + savings + investment;

		return totalFunds;
	}

//	public void setPin(int pin) {
//
//		this.pin = pin;
//	}
//
//	public int getPin() {
//		return pin;
//	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getpassword() {
		return password;
	}

}