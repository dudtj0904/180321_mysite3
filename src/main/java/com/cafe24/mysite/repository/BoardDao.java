package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.service.CommentService;
import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.GuestBook;

@Repository
public class BoardDao {
	@Autowired
	CommentService commentService;
	
	@Autowired
	SqlSession sqlSession;

	public boolean delete(long no) {
		commentService.removeGroup(no);
		
		int result = sqlSession.delete("board.delete", no);
		return (result == 1);
	}//delete()
	
	public boolean update(Board vo) {
		int result = sqlSession.update("board.updateByBoard", vo);
		return (result==1);
	}//update(board)

	
	
	public boolean update(long groupNo, long orderNo) { /* 댓글 그룹번호, 오더번호 수정  */
		Map<String, Object> map = new HashMap<>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		
		int result = sqlSession.update("board.updateByGroupNoAndOrderNo", map);
		return (result==1);
	}//update(groupNo, orderNo)
	
	public boolean update(long no) { /* 조회수 증가 */
		int result = sqlSession.update("board.updateByNo", no);
		return (result==1);
	}//update(no)
	
	public boolean insert(Board board) {
		int result = sqlSession.insert("board.insert", board);
		return (result==1);
	}//insert()
	
	public long getOrderNo(long groupNo) {
		return sqlSession.selectOne("board.getOrderNo", groupNo);
	}//getOrderNo()
	
	public long getGroupNo() {
		return sqlSession.selectOne("board.getGroupNo");
	}//getGroupNo()
	
	public Board get(long no) {
		return sqlSession.selectOne("board.get", no);
	}//get(no)
	
	// paging
	public List<Board> getList(int page, String kwd) {
		int realpage = (page-1)*10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", realpage);
		map.put("kwd", "%"+kwd+"%");
		return sqlSession.selectList("board.getListByPageAndKwd", map);		
	}
	
	public List<Board> getList(String kwd) {
		return sqlSession.selectList("board.getListByKwd", "%"+kwd+"%");	
	}

}
