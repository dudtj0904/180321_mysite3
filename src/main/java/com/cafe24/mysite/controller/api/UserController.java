package com.cafe24.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.User;

@Controller("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") 
								String email) {
		User user = userService.getUser(email);
		
		return JSONResult.success(user == null ? "not exist" : "exist");
	}
	
	@ResponseBody
	@RequestMapping("/checkpassword")
	public JSONResult checkPassword(@RequestParam(value="password", required=true, defaultValue="") 
								String password, @RequestParam(value="passwordcheck", required=true, defaultValue="") 
								String passwordCheck) {
		
		return JSONResult.success(passwordCheck.equals(password) ? "equal" : "not equal");
	}
}
