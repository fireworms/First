package mms.member.svc;

import static mms.member.db.JdbcUtil.*;
import java.sql.Connection;
import mms.member.dao.MemberDAO;
import mms.member.vo.Member;

public class MemberAddService {
	public boolean addMember(Member newMember) throws Exception{
		boolean isInsertSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		
		int insertCount = memberDAO.insertNewMember(newMember);
		
		if(insertCount > 0){
			commit(con);
			isInsertSuccess = true;
		}else{
			rollback(con);
		}
		
		close(con);
		return isInsertSuccess;
	}
	
}
