package mms.member.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static mms.member.db.JdbcUtil.*;
import mms.member.dao.MemberDAO;
import mms.member.vo.Member;

public class MemberListService {
	public ArrayList<Member> getMemberList(int index, int pageSize){
		Connection con = getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		
		ArrayList<Member> memberList = memberDAO.selectMemberList(index, pageSize);
		
		close(con);
		return memberList;
	}
}
