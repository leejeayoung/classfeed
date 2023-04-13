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
import semi.project.domain.ClassVo;
import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;
import semi.project.service.ClassService;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

public class ClassServiceImpl extends EgovAbstractServiceImpl implements ClassService {

	@Resource(name = "classDAO")
	private ClassDAO classDAO;

	@Override
	public void insertClass(ClassVo classVo) throws Exception {
		// TODO Auto-generated method stub
		classDAO.insert("classDAO.inserSubject", classVo);
	}

	@Override
	public List<?> selectSubjectBySid(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return classDAO.list("classDAO.selectClassBySid", user_id);
	}

	@Override
	public List<?> selectSucodeBySid(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return classDAO.list("classDAO.selectSucodeBySid", user_id);
	}

}
