package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public boolean join(User vo) {
		return userDao.insert(vo);
	}
	
	public User getUser(User vo) {
		return userDao.get(vo);
	}
	
	public User getUser(long no) {
		return userDao.get(no);
	}
	
	public User getUser(String email) {
		return userDao.get(email);
	}
	
	@Transactional
	public boolean modify(User vo) {
		return userDao.update(vo);
	}
}
