package semi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserDeniedHandler implements AccessDeniedHandler {

	private String errorPage = "/WEB-INF/jsp/egovframework/example/sample/errorPage.jsp";

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res,
			  AccessDeniedException ade) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*
		 * req.setAttribute("errMsg",ade.getMessage()); req.getRequestDispatcher(
		 * "/WEB-INF/jsp/egovframework/example/sample/errorPage.jsp").forward(req, res);
		 */


		String ajaxHeader = req.getHeader("X-Ajax-call");
		String result = "";

		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		res.setCharacterEncoding("UTF-8");

		// null로 받은 경우는 X-Ajax-call 헤더 변수가 없다는 의미이기 때문에
		// ajax가 아닌 일반적인 방법으로 접근했음을 의미한다.
		if(ajaxHeader == null) {
			log.info("ajaxHeader In");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			log.info("ajaxHeader auth >>"+auth);
			Object principal = auth.getPrincipal();

			req.setAttribute("username", principal);
			req.setAttribute(("errormsg"), ade);
			req.getRequestDispatcher(errorPage).forward(req, res);


			result = "{\"result\" : \"fail\", \"message\" : \"" + ade.getMessage() + "\"}";
			System.out.println("result :::: " + result);

		}else {

			if("true".equals(ajaxHeader)) {
				// true로 받았다는 것은 ajax로 접근
				result = "{\"result\" : \"fail\", \"message\" : \"" + ade.getMessage() + "\"}";

			}else {
				// 헤더 변수는 있으나 값이 틀린 경우이므로 헤더값이 틀렸다는 의미로 돌려준다
				result = "{\"result\" : \"fail\", \"message\" : \"Access Denied(Header Value Mismatch)\"}";

			}

			res.getWriter().print(result);
			res.getWriter().flush();

		}

	}
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		this.errorPage = errorPage;
	}



}