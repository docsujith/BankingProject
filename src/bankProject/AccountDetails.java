package bankProject;

public class AccountDetails {

	long accountNo;
	
	String name;
		
	double totalFunds;
	
	//String accountType;
	
	double balance;

	private int pin;
	
	private String password;
	
	AccountDetails(long a, String n) {
		accountNo= a;
		name= n;
	}
	
	public void setPin(int pin) {
		
		this.pin = pin;
	}
	
	public int getPin() {
		return pin;	}
	
     public void setPassword(String password) {
    	
		this.password = password;
	}
	
	public String getpassword() {
		return password;	}
	
}