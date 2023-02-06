package semi.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiDevice.Info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibatis.common.logging.Log;

import lombok.extern.slf4j.Slf4j;
import semi.project.domain.UsersVo;
import semi.project.service.SubjectService;
import semi.project.service.UsersService;

@Controller
@Slf4j
public class UsersController {
	//private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private UsersService usersService;
	
	
	
	
	@RequestMapping("/main.do")
	public String usersList(UsersVo usersVo) throws Exception {
		System.out.println("UsersController usersList In");
		return "content/login";
	}
	
	@RequestMapping("sign_up.do")
    public String login() {
        return "content/sign_up";
    }
	
	 @RequestMapping("/member/signUp")
	 public String signUp(UsersVo usersVo){
		 System.out.println("UsersController signUp In");
		 return "ok";
	 }
	 
	 @RequestMapping(value = "/subject/keep.do")
		public ModelAndView classKeepList(HttpServletRequest request, HttpSession session) throws Exception {
			String user_id = (String) session.getAttribute("userId");
			
			ModelAndView mav = new ModelAndView();
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<UsersVo> userList = (List<UsersVo>) usersService.selectUserById(user_id);
			
			if(user_id == null) {
				mav.setViewName("redirect:/");
				return mav;
			}
			
			if(userList.get(0).getUser_type().equals("teacher")) {
				mav.setViewName("content/keep");
				mav.addObject("tList", userList);
				map.put("user_id", user_id);
				map.put("keep_yn", "Y");
				mav.addObject("tSubList", subjectService.selectSubjectKeepList(map));
				return mav;
			}else {
				mav.setViewName("content/keep");
				mav.addObject("sList", userList);
				mav.addObject("sSubList", subjectService.selectSubjectByTid(user_id));
				return mav;
			}
		}

	 
}
