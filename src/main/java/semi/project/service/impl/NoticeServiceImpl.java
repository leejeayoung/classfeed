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
import semi.project.domain.NoticeVo;
import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;
import semi.project.service.BoardService;
import semi.project.service.ClassService;
import semi.project.service.NoticeService;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

public class NoticeServiceImpl extends EgovAbstractServiceImpl implements NoticeService {

	@Resource(name = "noticeDAO")
	private NoticeDAO noticeDAO;

	@Override
	public List<?> selectNoticeBySucode(String su_code) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.selectNoticeBySucode(su_code);
	}

	@Override
	public void insertNotice(NoticeVo noticeVo) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.insertNotice(noticeVo);
	}

}
