package com.cafe24.mysite.vo;

public class Reply {
	private long no;
	private String content;
	private String regDate;
	private long userNo;
	private long boardNo;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(String content, long userNo, long boardNo) {
		super();
		this.content = content;
		this.userNo = userNo;
		this.boardNo = boardNo;
	}

	public Reply(long no, String content, String regDate, long userNo, long boardNo) {
		super();
		this.no = no;
		this.content = content;
		this.regDate = regDate;
		this.userNo = userNo;
		this.boardNo = boardNo;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Reply [no=" + no + ", content=" + content + ", regDate=" + regDate + ", userNo=" + userNo + ", boardNo="
				+ boardNo + "]";
	}
	
	
	
}
