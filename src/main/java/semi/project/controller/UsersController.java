package semi.project.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import semi.project.domain.UsersVo;
import semi.project.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping("/test.do")
	public ModelAndView usersList(UsersVo usersVo) throws Exception {
		List<?> usersList = usersService.selectUserList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("usersList", usersList);
		return mav;
	}
	
	@RequestMapping(value="/loginPage.do")
	public String loginPage() throws Exception{
		return "sample/loginPage";
	}

	@RequestMapping(value="/userPage/userTest.do")
	public String userPage() throws Exception{
		return "sample/userPage";
	}
}
