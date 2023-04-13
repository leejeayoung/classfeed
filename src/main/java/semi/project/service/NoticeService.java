package semi.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import semi.project.domain.ClassVo;
import semi.project.domain.NoticeVo;
import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;

public interface NoticeService {
 
	List<?> selectNoticeBySucode(String su_code) throws Exception;
	void insertNotice(NoticeVo noticeVo) throws Exception;
}
