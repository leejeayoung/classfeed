package semi.project.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.ClassVo;
import semi.project.domain.SubjectVo;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO{

	public List<?> selectBoardBySucode(String su_code) throws Exception{
		return list("boardDAO.selectBoardBySucode", su_code);
	}
	
	
}
