package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.mysite.vo.User;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, HandlerExceptionResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, 
								ModelAndViewContainer mavContainer, 
								NativeWebRequest webRequest,
								WebDataBinderFactory binderFactory) throws Exception {
		
		// false�� ���� �����ִ� �Ķ���Ͱ� �ƴ϶�� ��
		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED; //���� �ؼ��� �� �ִ� �Ķ���Ͱ� �ƴϴ�.
		}
		
		// @AuthUser�� �پ� �ְ� parameter type�� User �� �� ����
		HttpServletRequest request =  webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		return session.getAttribute("authUser");
	}

	/** supportsPrameter()
	 	�Ķ���� ������� �������� �ٵ���
		return���� true�� �ָ� ������ �Ķ���͸� �ްڴٴ� ��
	*/
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 1. @AuthUser �� �پ��ִ��� Ȯ��
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// 2. @AuthUser �� �� �پ�����
		if(authUser == null) {
			return false;
		}
		
		// type Ȯ��
		// 3. type�� User VO�� �ƴ�
		if(parameter.getParameterType().equals(User.class) == false) {
			return false;
		}
		return true;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		return null;
	}
}
