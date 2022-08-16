package mockTest;
import java.util.Scanner;
import java.util.ArrayList;

public class Member {
			private int memberID;
			private String name;
			private int age;
			private String gender;
			private ArrayList<String> courseList = new ArrayList<String>();
			
			Scanner scan = new Scanner(System.in);
//			>>>>> getters and setters <<<<<<
			
			public int getAge() {
				return age;
			}
			public void setAge(int age) {
				this.age = age;
			}
		
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public int getMemberID() {
				return memberID;
			}
			public ArrayList<String> getCourseList() {
				return courseList;
			}
// >>>>>>>>> other methods <<<<<<<<<<
			Member(int tempID){            // constructor
				this.memberID = tempID;
				this.name = "N/A";
				this.age = 0;
				this.gender = "none";
			}

			public void addCourse(String aCourse) {
				
				courseList.add(aCourse);
			}
			public void removeCourse(String aCourse) {
				courseList.remove(aCourse);
			}
			public void inputDetails() {
				String name;
				String gender; 
				int age =-1; 
//			try {
				do {
					System.out.print("enter name: ");
					name = scan.nextLine();
					if(name.matches("[a-zA-z , ]+")) {
					  setName(name); break;
					}
					else {
						System.out.println("invalid name ! try again");
					}
				} while(true);
				
				 do {
					 System.out.print("Enter Gender: ");
				 		gender = scan.nextLine().toLowerCase();
					  if(gender.equals("none") || gender.equals("male") || gender.equals("female")){
						  setGender(gender); break;
					  }
					 		else {
					 						System.out.println("Invalid gender ! enter again ");
					 						}
				 } while(true);

				 do {
					 System.out.print("enter age : ");
					 if(scan.hasNextInt()) {
					 	age = scan.nextInt(); 
						 if(age>=0 && age <= 120) {
							 			 setAge(age);
								break;
						 }
						 else {
							 System.out.println("invalid age ! enter again ");
						 }
					 }	
						 else { 
							 System.out.println("invalid age ! enter again ");
							 scan.next();
				 }
				
			 }	while(true); 

			System.out.println("\n");
			}
		
			public void printMemberDetails() {
				
				System.out.println("\nID= "+memberID+"\nname = "+name+"\ngender= "+gender+"\nage="+age);
				System.out.print("course = ");
				for( int i=0; i<courseList.size(); i++) {
					System.out.print(courseList.get(i)+",");
				
				}
			}
}
