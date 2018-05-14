package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.vo.User;

@Repository
public class UserDao {
	
	/*@Autowired
	private DataSource dataSource;*/
	
	@Autowired
	SqlSession sqlSession;
	
	public boolean update(User user) {		
		int count = sqlSession.update("user.update", user);
		return (count==1);
	}
	
	public User get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}//get
	
	public User get(User vo) {
		StopWatch sw = new StopWatch();
		sw.start();
		
		User result = sqlSession.selectOne("user.getByEmailAndPassword", vo);
		
		sw.stop();
		long totalTime = sw.getTotalTimeMillis();

		return result;
	}//get
	
	public User get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}//get
	
	public boolean insert(User vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count==1;
	}//insert()
	
}
