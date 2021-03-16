package mms.member.dao;

import static mms.member.db.JdbcUtil.*;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import mms.member.vo.Member;

public class MemberDAO {
	Connection con;
	public MemberDAO(Connection con){
		this.con = con;
	}
	
	public int insertNewMember(Member newMember){
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into member values(member_id_seq.nextval, ?, ?, ?, ?, ?)";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newMember.getName());
			pstmt.setString(2, newMember.getAddr());
			pstmt.setString(3, newMember.getNation());
			pstmt.setString(4, newMember.getEmail());
			pstmt.setInt(5,  newMember.getAge());
			
			insertCount = pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "등록 성공");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "오류로 인해 등록하지 못함");
			e.printStackTrace();
		}finally{
			try{
				close(pstmt);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return insertCount;
	}
	
	public ArrayList<Member> selectMemberList(int index, int pageSize){
		ArrayList<Member> memberList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String range = "where num >= " + ((pageSize * index) + 1) + " and num <= " + (pageSize * (index + 1)); 
		String sql = "select * from (select name, addr, nation, email, age, rownum as num from member) " + range;
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<Member>();
			Member member = null;
			while(rs.next()){
				member = new Member(rs.getString("name"), rs.getString("addr"), rs.getString("nation"), rs.getString("email"), rs.getInt("age"));
				memberList.add(member);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				close(rs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return memberList;
	}
	
	public Member selectOldMember(String name){
		Member oldMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  name);;
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				oldMember = new Member(rs.getString("name"), rs.getString("addr"), rs.getString("nation"), rs.getString("email"), rs.getInt("age"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				close(rs);
				close(pstmt);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return oldMember;
	}
	
	public int updateMember(Member updateMember){
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set addr = ?, nation = ?, email = ?, age = ?" + " where name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateMember.getAddr());
			pstmt.setString(2, updateMember.getNation());
			pstmt.setString(3, updateMember.getEmail());
			pstmt.setInt(4, updateMember.getAge());
			pstmt.setString(5,  updateMember.getName());
			
			updateCount = pstmt.executeUpdate();
						
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				close(pstmt);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return updateCount;
	}
	
	public int deleteMember(String name){
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String sql = "delete member" + " where name = ?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			deleteCount = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				close(pstmt);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return deleteCount;
	}
}
