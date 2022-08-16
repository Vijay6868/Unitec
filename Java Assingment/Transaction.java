

public class Transaction{
	private String id;
	private String name;
	private int balance =0;

	public Transaction() {
		
	}
	
public Transaction(String id, String name,int balance) {
		
		this.id = id;
		this.name = name;
		this.balance = balance;
}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int credit(int amount) {

		balance = balance + amount;
		return balance;
		
	}
	public int debit(int amount) {
		if(amount<=balance) {
			balance = balance - amount;
			return balance;
		}
			
		else {
			System.out.println("amount exceded");
			return balance;
		}
		
	}
	public String toString(){
		return "  id = " + id+" name = "+name+" balance = "+balance;
	}
	
	public  int transfer( Transaction another, int amount) {
		
		if(amount<= balance) {
			
		another.balance = amount;
		balance = balance -amount;
		System.out.println("  ex if block  ");
		}
		else {
			System.out.print("amount exceeded");
		}
		return balance;
	}
	public void transfer(Transaction saving) {
		
	}
	public void saving(Transaction save){
		System.out.println(save.toString());
	}
}

