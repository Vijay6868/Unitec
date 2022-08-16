package mockTest;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		Member m1 = new Member(1);
		Member m2 = new Member(2);
		Member m3 = new Member(3);
		Member m4 = new Member(4);
		Member m5 = new Member(5);
		
			ArrayList <Member> members = new ArrayList<Member>();
			
			members.add(m1);
			members.add(m2);
			members.add(m3);
			members.add(m4);
			members.add(m5);
			
				for(Member me: members) {
					me.inputDetails();
				}
				
//			printDetails(members);
			
			m2.addCourse("HIIT");
			m5.addCourse("Strenth");
			m5.addCourse("Endurance");
			
			tableFormPrintDetails(members);
			
			femaleMembers(members);
			
			youngMember(m1, members);
			
			hasCourse(members);
					
	}

	private static void hasCourse(ArrayList<Member> members) {
		System.out.println("\n**************************************\n"
				+ "has at least on course: ");
		for(Member hasCourse: members) {
			
			if((hasCourse.getCourseList().size()!=0)) {
					hasCourse.printMemberDetails();
			}
		}
	}

	private static void tableFormPrintDetails(ArrayList<Member> members) {
		System.out.println("\n***************************************\n"
				+ "ID\tName\t\t\t\tAge\t\tGender");
		for(Member aDetail: members) {
			System.out.println(aDetail.getMemberID()+"\t"+aDetail.getName()
			+"\t\t\t"+aDetail.getAge()+"\t\t"+aDetail.getGender());
		}
	}

	private static void femaleMembers(ArrayList<Member> members) {
		System.out.println("\n****************************************\n"
				+ "Female members are: ");
		for(Member female: members) {
			if(female.getGender().equals("female")) {
					female.printMemberDetails();
			}
		}
	}

	private static void youngMember(Member m1, ArrayList<Member> members) {
		Member minAge= m1;
		for(Member age: members) {
			if(minAge.getAge()>age.getAge()) {
				minAge = age;
			}
		}
		System.out.print("\n*************************************\n"
				+ "youngest member details: ");
		minAge.printMemberDetails();
	}
		

}
