package DBest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UseDB {
	
	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public ResultSet select(){
		String sql = "select * from custmer";
		return doSelect(sql);
	}
	
	public void insert(String name, String grade, int age, String job){
		String sql = "insert into custmer select ?, ?, ?, ? from dual";
		doInsert(sql, name, grade, age, job);
	}
	
	private void doInsert(String sql, String name, String grade, int age, String job){
		int isSuccess = 0;
		try{
			pstmt = dbconn.conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, grade);
			pstmt.setInt(3, age);
			pstmt.setString(4, job);
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
	public static void main(String[] args){
		
		UseDB db = new UseDB();

		db.insert("파", "Gold", 18, "ㅍㅍ");
		ResultSet rs = db.select();
		try{
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
