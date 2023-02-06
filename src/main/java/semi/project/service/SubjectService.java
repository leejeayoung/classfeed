package semi.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;

public interface SubjectService {

	void insertSubject(SubjectVo subjectVo) throws Exception; // 클래스 추가 
	List<?> selectSubjectByTid(String user_id) throws Exception; // 클래스 조회
	List<?> selectSubjectKeepList(HashMap<String, Object> map) throws Exception; // 보관함 조회
	void updateKeepYn(HashMap<String, Object> map) throws Exception;	//보관
	
}
