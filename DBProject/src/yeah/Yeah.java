package yeah;
import java.sql.*;

public class Yeah {

	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String userID = "sqlDB";
		String userPass = "1234";
		Connection con = null;
		try{
			con = DriverManager.getConnection(jdbcUrl, userID, userPass);
			System.out.println("connection success");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
