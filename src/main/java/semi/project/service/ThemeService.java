package semi.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import semi.project.domain.ClassVo;
import semi.project.domain.SubjectVo;
import semi.project.domain.ThemeVo;
import semi.project.domain.UsersVo;

public interface ThemeService {
	
	void insertTheme(ThemeVo themeVo);
	List<?> selectThemeBySucode(String su_code) throws Exception;
	
}
