import java.util.Scanner;

public class Explorer1 {

	String name;
	int batteryLife;
	float xPos;
	float yPos;
	String position;

public static void main(String[] args) {
		Explorer1 TR1 = new Explorer1();
		TR1.inputExplorerDetails();
		TR1.outputExplorerDetails();
		
	}		
		public Explorer1() {
			this.name = "";
			this.batteryLife = 100;
			this.xPos = 0F;
			this.yPos = 0F;
		}

		public Explorer1(String name, int batteryLife, float xPos, float yPos) {
			this.name = name;
			this.batteryLife = batteryLife;
			this.xPos = xPos;
			this.yPos = yPos;	
		}
		public String getName() {
			System.out.print("\nEnter name: ");
			this.name = new Scanner(System.in).nextLine();
			return name;
		}
		public float getXPos(){
			return xPos;
		}
		public float getYPos(){
			return yPos;
		}

		public void moveLeft(float distance) {
			System.out.println("Robot has moved to " + distance + " m to left, battery life is " + batteryLife );	
		}

		public void moveRight(float distance) {
			if (xPos == 5)
				System.out.println("*Insufficient battery life*");
			if (xPos == 40)
				System.out.println("Robot has moved  " + xPos + " m to right, battery life is " + batteryLife);
			else
				System.out.println("Robot has moved 60m to right, battery life is " + batteryLife);
		}

		public int getBatteryLife() {
			batteryLife = (int)(int)(batteryLife - xPos);
		return batteryLife;
		}

		public void charge() {
			batteryLife = 100;
		}
		public void inputExplorerDetails(){
			System.out.print("\nEnter how many meters robot moved: ");
			this.xPos = new Scanner(System.in).nextInt();
		}

		public void outputExplorerDetails(){
		System.out.println("Name: " + getName());
		System.out.println("x position: " + getXPos() + "m");
		System.out.println("Battery Life: " + getBatteryLife());

	}
	
}