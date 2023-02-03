package semi.project.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
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
import semi.project.service.UsersService;

@Controller
@Slf4j
public class UsersController {
	//private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
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

	@RequestMapping(value="/userPage/userTest.do")
	public String userPage() throws Exception{
		return "sample/userPage";
	}
}
