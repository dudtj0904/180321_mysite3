package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.User;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute User vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid User vo, BindingResult result) {
		
		if(result.hasErrors()) {

			return "user/join";
		}
		
		userService.join(vo);
		//userDao.insert(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
	/*
	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {		
		return "error/exception";
	}*/

	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@AuthUser User authUser, Model model) {
		//User authUser = (User) session.getAttribute("authUser");
		System.out.println(authUser);
		
		User vo = userService.getUser(authUser.getNo());
		model.addAttribute("vo", vo);
		return "user/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute User vo, Model model, HttpSession session) {
		if(vo.getPasswordcheck().equals(vo.getPassword())) {
			userService.modify(vo);
			session.removeAttribute("authUser");
			session.setAttribute("authUser", vo);
		} else {
			model.addAttribute("result", "fail");
			return "user/modify";
		}
		return "redirect:/main";
	}
	
}
