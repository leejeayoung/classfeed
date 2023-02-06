package semi.project.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;
import semi.project.domain.SubjectRandom;
import semi.project.domain.SubjectVo;
import semi.project.service.SubjectService;

@Controller
@Log4j
public class TeacherController {
	
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/subject/subject.do")
	public String subjectAdd(@RequestParam("tid") String user_id,
            @RequestParam("tname") String user_name,
            @RequestParam("suname") String su_name,
            @RequestParam("ssubname") String ssub_name) throws Exception {
		String sucode = "";
		SubjectRandom random = new SubjectRandom();
		char[] num = random.ran(); // 랜덤 과목코드 생성
		
		if (num == null) {
			this.subjectAdd(user_id, user_name, su_name, ssub_name);
		}
		
		for (int i = 0; i < num.length; i++) {
			sucode += Character.toString(num[i]);
		}
		
		sucode = sucode.trim();
		
		SubjectVo subjectVo = new SubjectVo();
		subjectVo.setSu_code(sucode);
		subjectVo.setUser_id(user_id);
		subjectVo.setUser_name(user_name);
		subjectVo.setSu_name(su_name);
		subjectVo.setSsub_name(ssub_name);
		
		subjectService.insertSubject(subjectVo);
		return "redirect:/login/loginSuccess.do";
	}
	
	@RequestMapping(value = "/main/keepOn.do")
	public String subjectKeepY(@RequestParam("su_code")String su_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("su_code", su_code);
		map.put("keep_yn", "Y");
		subjectService.updateKeepYn(map);
		return "redirect:/login/loginSuccess.do";
	}
	
	@RequestMapping(value = "/main/keepOff.do")
	public String subjectKeepN(@RequestParam("su_code")String su_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("su_code", su_code);
		map.put("keep_yn", "N");
		subjectService.updateKeepYn(map);
		return "redirect:/subject/keep.do";
	}
	
	@RequestMapping(value = "/list/mystream.do")
	public String mystramList(@RequestParam("su_code")String su_code) throws Exception {
		
		return "redirect:/subject/keep.do";
	}
}
