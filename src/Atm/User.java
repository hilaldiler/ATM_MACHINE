package Atm;
public class User {
	private String accountNumber ;
	private int password;
	private double amount;
	

	public User(String accountNumber , int password) {
		this.accountNumber = accountNumber;
		this.password = password;
	}
	
	public User(String accountNumber , int password , double amount) {
		this.accountNumber = accountNumber;
		this.password = password;
		this.amount = amount;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}