package semi.project.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import semi.project.domain.UsersVo;
import semi.project.service.UsersService;

public class UsersServiceImpl extends EgovAbstractServiceImpl implements UsersService, UserDetailsService {

	@Resource(name = "usersDAO")
	private UsersDAO usersDAO;
	
	@Override
	public List<?> selectUserList() throws Exception {
		// TODO Auto-generated method stub
		return usersDAO.selectUserList();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UsersVo user = usersDAO.getUserById(userName);

		if(user == null) {
			throw new UsernameNotFoundException(userName);
		}

		return user;
	}

	@Override
	public int selectUserIdCheck(String userId) throws Exception {
		// TODO Auto-generated method stub
		// ID 중복체크
		return usersDAO.getUserIdCount(userId);
	}

	@Override
	public int selectUserEmailCheck(String userEmail) throws Exception {
		// TODO Auto-generated method stub
		// EMAIL 중복체크
		return usersDAO.getUserEmailCount(userEmail);
	}

	@Override
	public void insertUesr(UsersVo usersVo) throws Exception {
		// TODO Auto-generated method stub
		usersDAO.insertUser(usersVo);
	}

	@Override
	public List<?> selectUserById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return usersDAO.selectUserById(userId);
	}

}
