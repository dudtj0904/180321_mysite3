package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.Comment;

@Repository
public class CommentDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public boolean deleteGroup(long boardNo) {
		int result = sqlSession.delete("comment.deleteGroup", boardNo);
		return (result==1);
	}//delete()
	
	public boolean delete(long no) {
		int result = sqlSession.delete("comment.delete", no);
		return (result==1);
	}//delete()
	
	public long getOrderNo() {
		return sqlSession.selectOne("comment.getOrderNo");
	}//getOrderNo()
	
	public boolean insert(Comment comment) {
		int result = sqlSession.insert("comment.insert", comment);
		return (result==1);
	}
	
	public Comment get(long no) {
		return sqlSession.selectOne("comment.get", no);
	}
	
	public List<Comment> getList(long boardNo) {
		return sqlSession.selectList("comment.getList", boardNo);	
	}

}
