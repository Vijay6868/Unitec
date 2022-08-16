import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {

	
	public static Connection dbconnect()  {
		try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jAssingment","root","root"); 
		
		return con;
		}
		catch(Exception e2) {
			System.out.println(e2);
			return null;
		}
	}
	
}
