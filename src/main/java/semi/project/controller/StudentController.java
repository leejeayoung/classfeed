package semi.project.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;
import semi.project.domain.ClassVo;
import semi.project.domain.SubjectRandom;
import semi.project.domain.SubjectVo;
import semi.project.service.ClassService;
import semi.project.service.SubjectService;

@Controller
@Log4j
public class StudentController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ClassService classService;
	
	@RequestMapping(value = "/subject/class.do")
    public String addClass(HttpSession session, ClassVo classVo,
                                @RequestParam("su_code")String su_code,
                                @RequestParam("user_id")String inUser_id) throws Exception {
        String user_id = (String)session.getAttribute("userId");

        if(subjectService.selectSearchCount(su_code) == 0) {
            System.out.println("잘못된 수업코드 입니다");
            return null;
        }
        classVo.setSu_code(su_code);
        classVo.setUser_id(user_id);
        
        classService.insertClass(classVo);

        return "redirect:../main/list.do";
    }
}
