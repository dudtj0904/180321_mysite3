package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBook;

@Repository
public class GuestbookDao {
	
	/*@Autowired
	private DataSource dataSource;*/
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean delete(long no) {
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("pwd", vo.getPassword());*/
		int count = sqlSession.delete("guestbook.delete", no);
		
		return count==1;
	}
	public boolean delete(GuestBook vo) {
		int count = sqlSession.delete("guestbook.deleteByVo", vo);
		
		return count==1;
	}

	public boolean insert(GuestBook vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return (count==1);
	}
	
	public GuestBook get(long num) {
		return sqlSession.selectOne("guestbook.getGuestBook", num);
	}
	
	public List<GuestBook> getList() {
		return sqlSession.selectList("guestbook.getList");
	}
	
	public List<GuestBook> getList(long no) {
		return sqlSession.selectList("guestbook.getListByNo", no);
	}
}
