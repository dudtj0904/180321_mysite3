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
		// 1. handler ���� Ȯ��
		// default handler method�� ���(false)
		if(handler instanceof HandlerMethod == false) {
			return false;
		}
		
		// 2. casting
		// ������̼� ������ �̾Ƴ��� ����
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. @Auth �޾ƿ���
		// ������ null
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		// auth.role(); USER �Ǵ� ADMIN
		
		// 4. Method�� @Auth �� ����  ���
		// @Auth ������ ���� Controller�� ���� ����� �ڵ鷯 ����ض�
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth�� �پ� �ִ� ���, ���� ���� üũ
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
		
		// 6. ���� �㰡
		return true;
	}
	
}
