package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestBook;

@Service
public class GuestbookService {
	
	@Autowired
	GuestbookDao dao;
	
	public List<GuestBook> getGuestbookList() {
		return dao.getList();
	}
	public List<GuestBook> getGuestbookList(long no) {
		return dao.getList(no);
	}
	
	public GuestBook join(GuestBook vo) {
		GuestBook result = null;
		boolean flag = dao.insert(vo);
		if(flag) {
			result = dao.get(vo.getNo());
		}
		return result;
	}
	
	public GuestBook getGuestbook(long no) {
		return dao.get(no);
	}
	
	public boolean remove(long no) {
		return dao.delete(no);
	}
	public boolean remove(GuestBook vo) {
		return dao.delete(vo);
	}
	
}
