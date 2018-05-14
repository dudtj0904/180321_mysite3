package com.cafe24.mysite.vo;

public class Comment {
	private long no;
	private String content;
	private long orderNo;
	private String regDate;
	private long boardNo;
	private long userNo;
	private String userName;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Comment [no=" + no + ", content=" + content + ", orderNo=" + orderNo + ", regDate=" + regDate
				+ ", boardNo=" + boardNo + ", userNo=" + userNo + ", userName=" + userName + "]";
	}
	
	
	
}
