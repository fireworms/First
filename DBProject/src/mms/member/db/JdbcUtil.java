package mms.member.db;
import java.sql.*;

public class JdbcUtil {
	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sqldb", "1234");
			con.setAutoCommit(false);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try{
			stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try{
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con){
		try{
			con.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){
		try{
			con.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
