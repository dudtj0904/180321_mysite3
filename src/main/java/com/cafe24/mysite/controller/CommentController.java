package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.service.CommentService;
import com.cafe24.mysite.vo.Comment;
import com.cafe24.mysite.vo.PageInfo;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/insert")
	public String insert(Comment comment, Model model, PageInfo pageInfo) {
		long orderNo = commentService.getCommentOrderNo();
		comment.setOrderNo(orderNo+1);
		commentService.join(comment);
		model.addAttribute("comments", commentService.getCommentList(comment.getBoardNo()));
		model.addAttribute("boarddetail", boardService.getBoard(comment.getBoardNo()));

		return "redirect:/board/view?no="+comment.getBoardNo()+"&page="+pageInfo.getPage()
		+"&kwd="+pageInfo.getKwd();
	}
	
	@RequestMapping("/delete")
	public String delete(long no, PageInfo pageInfo) {
		Comment comment = commentService.getComment(no);
		commentService.remove(no);
		
		return "redirect:/board/view?no="+comment.getBoardNo()+"&page="+pageInfo.getPage()
			+"&kwd="+pageInfo.getKwd();
	}
}
