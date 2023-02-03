package semi.project.controller;

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

import lombok.extern.log4j.Log4j;
import semi.project.domain.UsersVo;
import semi.project.service.UsersService;

@Log4j
@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;
	
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
		usersVo.setUserId(id);
		usersVo.setPassword(pwd);
		usersVo.setUserName(name);
		usersVo.setUserEmail(email);
		usersVo.setUserTel(phone);
		usersService.insertUesr(usersVo);
		return "1";
	 }
	
	@RequestMapping(value = "/login/loginSuccess.do")
	 public String test(HttpServletRequest request, HttpSession session) throws Exception{
		String userId = (String) session.getAttribute("userId");
		
		    
		return "test";
	 }
}
