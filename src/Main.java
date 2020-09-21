import java.sql.*;


public class Main {

	public static void main(String[] args)  {
		try {
		
		System.out.println("Database Connected");
		App myApp=new App();
		myApp.run(connect);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Not Connected");
		}
	}
	
}
