package semi.project.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.SubjectVo;

@Repository("subjectDAO")
public class SubjectDAO extends EgovAbstractDAO{

	public void insertSubject(SubjectVo subjectVo) throws Exception{
		insert("subjectDAO.inserSubject", subjectVo);
	}
	
	public List<?> selectSubjectByTid(String user_id) throws Exception{
		return list("subjectDAO.selectSubjectByTid", user_id);
	}
	
	public List<?> selectSubjectKeepList(HashMap<String, Object> map) throws Exception{
		return list("subjectDAO.selectSubjectKeepList", map);
	}
	
	public void updateKeepYn(HashMap<String, Object> map) throws Exception{
		update("subjectDAO.updateKeepYn",map);
	}
	
	public int selectSearchCount(String su_code) throws Exception {
		//String rStr = select("subjectDAO.selectSearchCount", su_code);
		//int rInt = Integer.parseInt((String) rStr);
		return (int) select("subjectDAO.selectSearchCount", su_code);
	}
	
	public List<?> selectSubjectBySucode(String su_code) throws Exception {
		return list("subjectDAO.selectSubjectBySucode", su_code);
	}
	
	
}
