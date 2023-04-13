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
import semi.project.domain.StudentRandom;
import semi.project.domain.SubjectVo;
import semi.project.domain.ThemeVo;
import semi.project.domain.UsersVo;
import semi.project.service.BoardService;
import semi.project.service.ClassService;
import semi.project.service.NoticeService;
import semi.project.service.SubjectService;
import semi.project.service.ThemeService;
import semi.project.service.UsersService;

public class ThemeServiceImpl extends EgovAbstractServiceImpl implements ThemeService {

	@Resource(name = "themeDAO")
	private ThemeDAO themeDAO;

	@Override
	public List<?> selectThemeBySucode(String su_code) throws Exception {
		// TODO Auto-generated method stub
		return themeDAO.selectThemeBySucode(su_code);
	}

	@Override
	public void insertTheme(ThemeVo themeVo) {
		// TODO Auto-generated method stub
		String th_code="";
		StudentRandom r = new StudentRandom();
		char[] num = r.ran();
		if(num==null){
			this.insertTheme(themeVo);
		}
		for(int i=0; i<num.length;i++){
			th_code += Character.toString(num[i]);
		}
		th_code = th_code.trim();
		if(themeDAO.countThemeByThcode(th_code) > 0){
			this.insertTheme(themeVo);
		}
		themeVo.setTh_code(th_code);
		themeDAO.insertTheme(themeVo);
	}


}
