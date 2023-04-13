package semi.project.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import semi.project.domain.ThemeVo;
import semi.project.domain.UsersVo;

@Repository("themeDAO")
public class ThemeDAO extends EgovAbstractDAO{
	
	public void insertTheme(ThemeVo themeVo) {
		insert("themeDAO.insertTheme", themeVo);
	}
	
	public List<?> selectThemeBySucode(String su_code) throws Exception {
		return list("themeDAO.selectThemeBySucode",su_code);
	}
	
	public int countThemeByThcode(String th_code) {
		return (int) select("themeDAO.countThemeByThcode", th_code);
	}

}
