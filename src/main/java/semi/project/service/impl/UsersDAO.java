package semi.project.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.UsersVo;

@Repository("usersDAO")
public class UsersDAO extends EgovAbstractDAO{

	public List<?> selectUserList() throws Exception {
		return list("usersDAO.selectUserList");
	}

	public UsersVo getUserById(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return (UsersVo) select("usersDAO.selectUserId", userId);
	}
	
	public int getUserIdCount(String userId) throws Exception {
		return (int) select("usersDAO.selectUserIdCheck", userId);
	}
	
	public int getUserEmailCount(String userEmail) throws Exception {
		return (int) select("usersDAO.selectUserEmailCheck", userEmail);
	}
	
	public void insertUser(UsersVo usersVo) throws Exception{
		insert("userDAO.inserUser", usersVo);
	}
	
	public List<?> selectUserById(String userId) throws Exception {
		return list("usersDAO.selectUserById",userId);
	}

}
