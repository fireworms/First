package DBest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect01 {
	public DBConnect01(){
		String dbURL = "jdbc:mysql://localhost:3306/friend?serverTimezone=UTC";
		String id = "root";
		String password = "1111";
		Connection conn;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB ������");
			conn = DriverManager.getConnection(dbURL, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		}catch(ClassNotFoundException e){
			System.out.println("JDBC ����̹��� ã�� �� ����");
		}catch(SQLException e){
			System.out.println("�����ͺ��̽� ���� ����");
		}
	}

	public static void main(String[] args){
		new DBConnect01();
	}
}
