package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.Board;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<Board> getBoardList(String kwd) {
		return boardDao.getList(kwd);
	}
	
	public List<Board> getBoardList(int page, String kwd) {
		return boardDao.getList(page, kwd);
	}
	
	public long getBoardGroupNo() {
		return boardDao.getGroupNo();
	}
	
	public long getBoardOrderNo(long groupNo) {
		return boardDao.getOrderNo(groupNo);
	}
	
	public Board getBoard(long no) {
		return boardDao.get(no);
	}
	
	public boolean modify(long groupNo, long orderNo) {
		return boardDao.update(groupNo, orderNo);
	}
	
	public boolean modify(long no) {
		return boardDao.update(no);
	}
	
	public boolean modify(Board vo) {
		return boardDao.update(vo);
	}
	
	public boolean join(Board board) {
		return boardDao.insert(board);
	}
	
	public boolean remove(long no) {
		return boardDao.delete(no);
	}
}
