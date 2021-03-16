package mms.member.svc;

import static mms.member.db.JdbcUtil.*;
import java.sql.Connection;
import mms.member.dao.MemberDAO;


public class MemberRemoveService {
	public boolean removeMember(String name){
		boolean isRemoveSuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = new MemberDAO(con);
		int deleteCount = memberDAO.deleteMember(name);
		if(deleteCount > 0){
			isRemoveSuccess = true;
			commit(con);
		}else{
			rollback(con);
		}
		close(con);
		
		return isRemoveSuccess;
	}
}
