package semi.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import semi.project.domain.BoardVo;
import semi.project.domain.NoticeVo;
import semi.project.domain.SubjectRandom;
import semi.project.domain.SubjectVo;
import semi.project.domain.ThemeVo;
import semi.project.domain.UsersVo;
import semi.project.service.BoardService;
import semi.project.service.ClassService;
import semi.project.service.NoticeService;
import semi.project.service.SubjectService;
import semi.project.service.ThemeService;
import semi.project.service.UsersService;

@SuppressWarnings("unchecked")
@Controller
@Log4j
public class TeacherController {
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private ThemeService themeService;
	
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
	@ResponseBody
	public ModelAndView mystramList(@RequestParam("su_code")String su_code,
								@RequestParam("user_type") String user_type,
								HttpSession session, ModelAndView mav) throws Exception {
		String user_id = (String)session.getAttribute("userId");

        session.setAttribute("su_code", su_code); // key=sucode, value=sucode 세션에 셋팅
        
        if("teacher".equals(user_type)) {
        	this.setTeacherDefaultInfo(user_id, mav);
        }else if ("student".equals(user_type)) {
        	this.setStudentDefaultInfo(user_id, mav, su_code);
		}else {
			
		}
        List<SubjectVo> subList = (List<SubjectVo>) subjectService.selectSubjectBySucode(su_code); //수업코드로 subject 테이블 호출
        List<BoardVo> boardList = (List<BoardVo>) boardService.selectBoardBySucode(su_code); //수업코드로 board 테이블 호출(테마 테이블 거쳐서)
        List<NoticeVo> noticeList = (List<NoticeVo>) noticeService.selectNoticeBySucode(su_code); //수업코드로 공지테이블 호출
        
        mav.addObject("subList",subList);
        mav.addObject("boardList", boardList);
        mav.addObject("noticeList", noticeList);
        mav.addObject("user_type", user_type);
        
        mav.setViewName("content/stream");
		return mav;
	}
	
	
	private void setTeacherDefaultInfo(String user_id, ModelAndView mav) throws Exception{
        List<UsersVo> tList= (List<UsersVo>) usersService.selectUserById(user_id);
        List<SubjectVo> tSubList = (List<SubjectVo>) subjectService.selectSubjectByTid(user_id);
        mav.addObject("tSubList",tSubList);
        mav.addObject("tList", tList);
        mav.addObject("tLogin",user_id);
    }

	
    private void setStudentDefaultInfo(String user_id, ModelAndView mav, String su_code) throws Exception{        
		List<UsersVo> sList = (List<UsersVo>) usersService.selectUserById(user_id);
        List<SubjectVo> sSubList = (List<SubjectVo>) subjectService.selectSubjectBySucode(su_code);
        mav.addObject("sSubList",sSubList);
        mav.addObject("sList", sList);
        mav.addObject("sLogin",user_id);
    }
    
    // !!! 수업 탭의 리스트 출력(왼쪽 주제리스트, 중앙에 주제 안에 과제,자료)
    @RequestMapping(value = "/list/myclass.do")
    @ResponseBody
    public ModelAndView myClass(@RequestParam("su_code")String su_code,
							@RequestParam("user_type") String user_type,
                          HttpSession session,
                          ModelAndView mav) throws Exception {
    	
    	String user_id = (String)session.getAttribute("userId");

        if(!ObjectUtils.isEmpty(session.getAttribute("su_code"))){
        	su_code = (String) session.getAttribute("su_code");
        }

        if(su_code.equals("")) throw new Exception("sucode가 존재하지 않음");
        session.setAttribute("su_code", su_code); // key=sucode, value=sucode 세션에 셋팅

        if("teacher".equals(user_type)) {
            this.setTeacherDefaultInfo(user_id, mav);
        }else if("student".equals(user_type)){
            this.setStudentDefaultInfo(user_id, mav, user_type);
        }else {
        	
        }

        List<SubjectVo> subList = (List<SubjectVo>) subjectService.selectSubjectBySucode(su_code); //수업코드로 subject 테이블 호출
        List<BoardVo> boardList = (List<BoardVo>) boardService.selectBoardBySucode(su_code); //수업코드로 board 테이블 호출(테마 테이블 거쳐서)
        List<ThemeVo> thlist = (List<ThemeVo>) themeService.selectThemeBySucode(su_code); //수업코드로 theme 테이블 호출
        mav.addObject("subList",subList);
        mav.addObject("blist",boardList);
        mav.addObject("thlist",thlist);
        mav.setViewName("content/class");

        return mav;
    }
    
    @RequestMapping(value = "/list/notice.do")
	public String noticeInput(@RequestParam("ncontent")String ncontent,
							HttpSession session) throws Exception {
    	
        String user_id	= (String)session.getAttribute("userId");
        String su_code = (String) session.getAttribute("su_code");
        String user_type = "";
        
        NoticeVo noticeVo = new NoticeVo();
        
        if(null != user_id && null != su_code) {
        	List<UsersVo> uList = (List<UsersVo>) usersService.selectUserById(user_id);
        	user_type = uList.get(0).getUser_type();
        	noticeVo.setUser_id(user_id);
        	noticeVo.setSu_code(su_code);
        	noticeVo.setNotice_content(ncontent);
        }else {
        	
        }
        
        if("teacher".equals(user_type)) {
            noticeService.insertNotice(noticeVo);
        }else if("student".equals(user_type)) {
            noticeService.insertNotice(noticeVo);
        }else {
        	
        }
        
//        return "redirect:mystream.do?sucode="+sucode;

		return "";
	}
    
    @RequestMapping(value = "/list/theme.do") //주제 생성
    public String addTheme(HttpSession session, ThemeVo themeVo) throws Exception {
        String user_id = (String)session.getAttribute("userId"); //선생님 만이 주제 추가 가능
        String su_code = (String) session.getAttribute("su_code"); //세션에서 수업코드를 가져옴.

//        themeVo.setTid(tid);
//        themeVo.setSucode(sucode);
          themeService.insertTheme(themeVo);

        //return "redirect:../list/myclass.do?sucode="+sucode;
        return null;
    }
    
}
