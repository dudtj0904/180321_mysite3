package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.User;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	//@Autowired
	//private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		User vo = new User();
		vo.setEmail(email);
		vo.setPassword(password);
		
		//컨테이너 받아오는 객체
		ApplicationContext ac =  WebApplicationContextUtils.
									getWebApplicationContext(request.getServletContext());
		
		// 타입을 주면 타입에 맞춰서 주고 email같은 문자열을 주면 object 리턴
		UserService userService = ac.getBean(UserService.class); 
		User authUser = userService.getUser(vo);
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath()+"/main");
		return false;
	}

}
