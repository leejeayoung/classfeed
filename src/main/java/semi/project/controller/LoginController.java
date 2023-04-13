package semi.project.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import semi.project.domain.UsersVo;
import semi.project.service.ClassService;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

@Log4j
@Slf4j
@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ClassService classService;
	
	@Inject
    PasswordEncoder passwordEncoder;
	
	/**
	 * 회원가입 > ID 중복검사
	 * param : id, type(teacher / student)
	 * return : String
	 * */
	@RequestMapping(value = "/login/checkId.do", method = RequestMethod.POST)
	@ResponseBody
	 public String checkId(@RequestParam("id") String userId, @RequestParam("type") String type) throws Exception{
		int count = usersService.selectUserIdCheck(userId);
		if(count > 0) {		// ID 존재(중복)
			return "yesId";
		}else {
			return "noId";
		}
	 }
	
	/**
	 * 회원가입 > Email 중복검사
	 * param : email, type(teacher / student)
	 * return : String
	 * */
	@RequestMapping(value = "/login/checkEmail.do", method = RequestMethod.POST)
	@ResponseBody
	 public String checkEmail(@RequestParam("email") String userEmail, @RequestParam("type") String type) throws Exception{
		int count = usersService.selectUserEmailCheck(userEmail);
		
		if(count > 0) {		// Email 존재(중복)
			return "yesEmail";
		}else {
			return "noEmail";
		}
	 }
	
	/**
	 * 회원가입 > 가입완료
	 * param : UsersVo
	 * return : String
	 * */
	@RequestMapping(value = "/login/signUpSuccess.do", method = RequestMethod.POST)
	@ResponseBody
	 public String signUpSuccess(@RequestParam("type") String type,
	            @RequestParam("name") String name,
	            @RequestParam("agency") String agency,
	            @RequestParam("id") String id,
	            @RequestParam("email") String email,
	            @RequestParam("pwd") String pwd,
	            @RequestParam("phone") String phone
	  ) throws Exception{
		String encPassword = passwordEncoder.encode(pwd);
		pwd = encPassword;
		
		log.info("type >>"+type);
		log.info("name >>"+name);
		log.info("agency >>"+agency);
		log.info("id >>"+id);
		log.info("email >>"+email);
		log.info("pwd >>"+pwd);
		
		UsersVo usersVo = new UsersVo();
		usersVo.setUser_id(id);
		usersVo.setPassword(pwd);
		usersVo.setUser_name(name);
		usersVo.setUser_type(type);
		usersVo.setUser_email(email);
		usersVo.setUser_tel(phone);

		usersService.insertUesr(usersVo);
		return "1";
	 }
	
	@RequestMapping(value = "/login/loginSuccess.do")
	 public ModelAndView test(HttpServletRequest request, HttpSession session) throws Exception{
		String user_id = (String) session.getAttribute("userId");
		ModelAndView mav = new ModelAndView("content/main");
		List<UsersVo> userList = (List<UsersVo>) usersService.selectUserById(user_id);
		String user_type = "";
		if((userList.get(0).getUser_type()).equals("student")) {
			user_type = userList.get(0).getUser_type();
			mav.addObject("sList", userList);
			mav.addObject("sSubList", classService.selectSubjectBySid(user_id));
			mav.addObject("user_type",user_type);
		}else {
			user_type = userList.get(0).getUser_type();
			mav.addObject("user_type",user_type);
			mav.addObject("tList", userList);
			mav.addObject("tSubList", subjectService.selectSubjectByTid(user_id));
		}
		
		return mav;
	 }
}
