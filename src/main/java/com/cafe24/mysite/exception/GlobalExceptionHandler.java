package com.cafe24.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request,
								HttpServletResponse response,
								Exception e) throws Exception {
		// 1. logging
		StringWriter error = new StringWriter(); // e.printStackTrace() -> string
		e.printStackTrace(new PrintWriter(error));
		request.setAttribute("errors", error.toString());
		
		e.printStackTrace();
		
		LOG.debug( error.toString() );
		LOG.info(  error.toString() );
		LOG.warn(  "warn"+error.toString() );
		LOG.error(  "error"+error.toString() );
		
		
		// request는 브라우저에서 보내는 정보 / response는 서버가 브라우저에게 보내는 것 
		// 브라우저에서 json형식으로 보냈으면 응답도 json으로 해주어야 함
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			// 2. 실패 JSON 응답
			JSONResult jsonResult = JSONResult.fail(error.toString());
			String json = new ObjectMapper().writeValueAsString(jsonResult);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(json);
			
		} else {
			// 2. 사과 page
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		
		
	}
}
