package semi.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import semi.project.domain.UsersVo;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

@Controller
@Log4j
public class UserController {
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UsersService usersService;
	
	
	
}
