package DBest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public Connection conn;
	public DBConn(){
		String dbURL = "jdbc:mysql://localhost:3306/friend?serverTimezone=UTC";
		String id = "root";
		String password = "1111";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB 연결중");
			conn = DriverManager.getConnection(dbURL, id, password);
			System.out.println("데이터베이스 연결 성공");
		}catch(ClassNotFoundException e){
			System.out.println("JDBC 드라이버를 찾을 수 없다");
		}catch(SQLException e){
			System.out.println("데이터베이스 연결 실패");
		}
	}
}
