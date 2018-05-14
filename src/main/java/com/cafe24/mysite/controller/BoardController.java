package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.paging.Paging;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.service.CommentService;
import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.Comment;
import com.cafe24.mysite.vo.PageInfo;
import com.cafe24.mysite.vo.User;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	CommentService commentService;
	@Autowired
	Paging paging;
	
	@RequestMapping("/list")
	public String list(@ModelAttribute PageInfo pageInfo, Model model) {
		System.out.println("[/board/list] : "+pageInfo);
		if(pageInfo.getKwd() == null) pageInfo.setKwd("");
		
		pageInfo = paging.pagingCalculator(pageInfo, 10);
		
		List<Board> list = boardService.getBoardList(pageInfo.getPage(), pageInfo.getKwd());

		model.addAttribute("pageinfo", pageInfo);
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write( HttpSession session, Model model) {
		User authUser = (User) session.getAttribute("authUser"); 
		if(authUser == null) return "redirect:/main";
		
		Board board = new Board();
		board.setGroupNo(boardService.getBoardGroupNo());
		model.addAttribute("writeboard", board);
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute Board board, PageInfo pageInfo) {
		System.out.println("[/board/wirte] "+board);
		boardService.join(board);
		if(board.getDepth() > 1) {
			boardService.modify(board.getGroupNo(), board.getOrderNo());
		}
		return "redirect:/board/list?page="+pageInfo.getPage()+"&kwd="+pageInfo.getKwd();
	}	
	
	@RequestMapping("/view")
	public String view(long no, 
					PageInfo pageInfo, 
					HttpSession session, 
					Model model) {
		Board board = boardService.getBoard(no);
		System.out.println("[/board/view] : "+pageInfo);
		User authUser = (User) session.getAttribute("authUser");
		if(authUser != null) {
			if(board.getUserNo()!=authUser.getNo()) {
				boardService.modify(no);
			}
		}
		List<Comment> list = commentService.getCommentList(no);
		
		model.addAttribute("pageinfo", pageInfo);
		model.addAttribute("comments", list);
		model.addAttribute("boarddetail", board);
		return "board/view";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(long no, 
			 			PageInfo pageInfo, 
			 			Model model) {
		model.addAttribute("updateboard", boardService.getBoard(no));
		model.addAttribute("pageinfo", pageInfo);
		return "board/modify";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute Board board, PageInfo pageInfo) {
		boardService.modify(board);
		
		return "redirect:/board/view?no="+board.getNo()+"&page="+pageInfo.getPage()+"&kwd="+pageInfo.getKwd();
	}
	
	@RequestMapping("/reply")
	public String reply(long no, 
 						PageInfo pageInfo, 
 						Model model) {
		Board board = boardService.getBoard(no);
		long maxOrderNo = boardService.getBoardOrderNo(board.getGroupNo());
		model.addAttribute("replyboard", board);
		model.addAttribute("maxorderno", maxOrderNo);
		model.addAttribute("pageinfo", pageInfo);
		
		return "board/write";
	}
	
	@RequestMapping("/delete")
	public String delete(long no, PageInfo pageInfo) {
		boardService.remove(no);
		
		return "redirect:/board/list?page="+pageInfo.getPage()+"&kwd="+pageInfo.getKwd();
	}
	
	
}
