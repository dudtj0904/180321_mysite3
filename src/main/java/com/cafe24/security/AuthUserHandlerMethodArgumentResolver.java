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
		
		// false면 내가 관심있는 파라미터가 아니라는 뜻
		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED; //내가 해석할 수 있는 파라미터가 아니다.
		}
		
		// @AuthUser가 붙어 있고 parameter type이 User 일 때 과정
		HttpServletRequest request =  webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		return session.getAttribute("authUser");
	}

	/** supportsPrameter()
	 	파라미터 내용들이 이쪽으로 다들어옴
		return값을 true로 주면 들어오는 파라미터를 받겠다는 뜻
	*/
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 1. @AuthUser 가 붙어있는지 확인
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// 2. @AuthUser 가 안 붙어있음
		if(authUser == null) {
			return false;
		}
		
		// type 확인
		// 3. type이 User VO가 아님
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
