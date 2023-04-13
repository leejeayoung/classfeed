package semi.project.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.ClassVo;
import semi.project.domain.SubjectVo;

@Repository("classDAO")
public class ClassDAO extends EgovAbstractDAO{

	public void insertClass(ClassVo classVo) throws Exception{
		insert("classDAO.inserSubject", classVo);
	}
	
	public List<?> selectSubjectBySid(String user_id) throws Exception{
		return list("classDAO.selectClassBySid", user_id);
	}
	
	public List<?> selectSucodeBySid(String user_id) throws Exception{
		return list("classDAO.selectSucodeBySid", user_id);
	}
	
	
}
