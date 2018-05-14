package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		// default handler method일 경우(false)
		if(handler instanceof HandlerMethod == false) {
			return false;
		}
		
		// 2. casting
		// 어노테이션 정보를 뽑아내기 위해
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. @Auth 받아오기
		// 없으면 null
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		// auth.role(); USER 또는 ADMIN
		
		// 4. Method에 @Auth 가 없는  경우
		// @Auth 없으면 뒤의 Controller로 가서 사용할 핸들러 사용해라
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 붙어 있는 경우, 인증 여부 체크
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		User authUser = (User) session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// 6. 접근 허가
		return true;
	}
	
}
