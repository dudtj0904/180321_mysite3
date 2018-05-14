package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestBook;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	GuestbookService gbService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestBook> list = gbService.getGuestbookList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestBook vo) {
		gbService.join(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam(name="no") int no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBook vo) {
		GuestBook guestbook = gbService.getGuestbook(vo.getNo());
		if(vo.getPassword().equals(guestbook.getPassword())) {
			gbService.remove(vo.getNo());
		}
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/ajax")
	public String ajax() {
		return "guestbook/index-ajax";
	}
	
	
	
}
