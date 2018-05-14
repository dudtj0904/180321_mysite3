package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.Comment;

@Service
public class CommentService {
	
	@Autowired
	CommentDao dao;
	
	public long getCommentOrderNo() {
		return dao.getOrderNo();
	}
	
	public boolean join(Comment comment) {
		return dao.insert(comment);
	}
	
	public List<Comment> getCommentList(long boardNo) {
		return dao.getList(boardNo);
	}
	
	public Comment getComment(long no) {
		return dao.get(no);
	}
	
	public boolean remove(long no) {
		return dao.delete(no);
	}
	
	public boolean removeGroup(long boardNo) {
		return dao.deleteGroup(boardNo);
	}
}
