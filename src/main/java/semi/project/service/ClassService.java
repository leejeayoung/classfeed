package semi.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import semi.project.domain.ClassVo;
import semi.project.domain.SubjectVo;
import semi.project.domain.UsersVo;

public interface ClassService {

	void insertClass(ClassVo classVo) throws Exception; // 클래스 추가 
	List<?> selectSubjectBySid(String user_id) throws Exception; // 클래스 조회
	List<?> selectSucodeBySid(String user_id) throws Exception; // 클래스 조회
	
}
