package semi.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.UsersVo;

@Repository("usersDAO")
public class UsersDAO extends EgovAbstractDAO{

	public List<?> selectUserList() throws Exception {
		return list("usersDAO.selectUserList");
	}

	public UsersVo getUserById(String userId) {
		// TODO Auto-generated method stub
		return (UsersVo) select("usersDAO.selectUserId", userId);
	}
}
