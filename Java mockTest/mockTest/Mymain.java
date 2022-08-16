package mockTest;

import java.util.ArrayList;

public class Mymain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList <Member> members = new ArrayList<Member>();
		
		for(int i =0; i<5; i++) {
			Member p = new Member(i+1);
			members.add(p);
		}
		for(Member me: members) {
			System.out.println(me.getMemberID());
		}
	}

}
