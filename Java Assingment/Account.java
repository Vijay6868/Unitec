
public class Account {
		private int accountID;
		private Customer customerID;
		private double balance;
		private int accNum;
		public int getAccNum() {
			return accNum;
		}
		public void setAccNum(int accNum) {
			this.accNum = accNum;
		}
		private String accountType;
		public Customer getCustomerID() {
			return customerID;
		}
		public Account(int accountID, Customer customerID) {
			super();
			this.accountID = accountID;
			this.customerID = customerID;
			this.balance = 0;
		}
		public void setCustomerID(Customer customerID) {
			this.customerID = customerID;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}
		public String getAccountType() {
			return accountType;
		}
		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}
		public int getAccountID() {
			return accountID;
		}
}
