package semi.project.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.ClassVo;
import semi.project.domain.NoticeVo;
import semi.project.domain.SubjectVo;

@Repository("noticeDAO")
public class NoticeDAO extends EgovAbstractDAO{

	public List<?> selectNoticeBySucode(String su_code) throws Exception{
		return list("boardDAO.selectNoticeBySucode", su_code);
	}
	
	public void insertNotice(NoticeVo noticeVo) throws Exception {
		insert("noticeDAO.insertNotice",noticeVo);
	}
	
	
}
