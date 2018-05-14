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

		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
			// 2. fail JSON response
			JSONResult jsonResult = JSONResult.fail(error.toString());
			String json = new ObjectMapper().writeValueAsString(jsonResult);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(json);
			
		} else {
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		
		
	}
}
