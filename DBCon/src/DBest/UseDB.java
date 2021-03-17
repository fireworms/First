package DBest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UseDB {
	
	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public ResultSet select(String tblName){
		String sql = "select * from " + tblName;
		return doSelect(sql);
	}
	
	public void remove(String tblName, String name){
		String sql = "delete from " + tblName + " where name = ?";
		doRemove(tblName, sql, name);
	}
	
	public void insert(String tblName, String name, int age, String job){
		String sql = "insert into " + tblName + " select ?, ?, ?, ? from dual";
		doInsert(sql, name, age, job);
	}
	
	private void doRemove(String tblName, String sql, String name){
		int isSuccess = 0;
		try{
			pstmt = dbconn.conn.prepareStatement(sql);
			pstmt.setString(1, name);
			isSuccess = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(isSuccess == 1){System.out.println("삭제 성공");}
		else {System.out.println("삭제 실패");}
	}
	
	private void doInsert(String sql, String name, int age, String job){
		int isSuccess = 0;
		try{
			pstmt = dbconn.conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, job);
			isSuccess = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(isSuccess == 1){System.out.println("등록 성공");}
		else {System.out.println("등록 실패");}
	}
	
	private ResultSet doSelect(String sql){
		
		try{
			pstmt = dbconn.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
}
