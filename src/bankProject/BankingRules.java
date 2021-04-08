package bankProject;

public abstract class BankingRules {
	int dailyWithdrawalLimit;
	int numberOfTransaction;

	abstract void userIdentification(AccountDetails account);

	public int dailyWithdrawalLimit(int dailyLimit) {
		dailyWithdrawalLimit = dailyLimit;
		return dailyLimit;
	}

	public int noOFTransactions(int numberTransaction) {
		numberOfTransaction = numberTransaction;
		return numberTransaction;
	}

	public void pinChange(String accountholder, int otp, int pin, int newPin) {

	}

}
