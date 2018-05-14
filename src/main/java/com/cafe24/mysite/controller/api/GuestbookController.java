package com.cafe24.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestBook;

@Controller("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	/*
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list() {
		List<GuestBook> list = guestbookService.getGuestbookList();
		return JSONResult.success(list);
	}
	*/
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value="no", required=true, defaultValue="0") long no) {
		List<GuestBook> list = guestbookService.getGuestbookList(no);
		System.out.println(no);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public JSONResult insert( @RequestBody GuestBook vo) {
		GuestBook guestbook = guestbookService.join(vo);
		return JSONResult.success(guestbook);
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@ModelAttribute GuestBook guestbook) {
		System.out.println("deleteajax");
		boolean result = guestbookService.remove(guestbook);
		return JSONResult.success(result ? guestbook.getNo() : -1);
	}
	
}
