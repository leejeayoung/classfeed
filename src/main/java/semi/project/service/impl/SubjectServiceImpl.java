package semi.project.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

public class SubjectServiceImpl extends EgovAbstractServiceImpl implements SubjectService {

	@Resource(name = "subjectDAO")
	private SubjectDAO subjectDAO;

	@Override
	public void insertSubject(SubjectVo subjectVo) throws Exception {
		// TODO Auto-generated method stub
		subjectDAO.insertSubject(subjectVo);
	}

	@Override
	public List<?> selectSubjectByTid(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return subjectDAO.selectSubjectByTid(user_id);
	}

	@Override
	public List<?> selectSubjectKeepList(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return subjectDAO.selectSubjectKeepList(map);
	}

	@Override
	public void updateKeepYn(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		subjectDAO.updateKeepYn(map);
	}
	
	

}
