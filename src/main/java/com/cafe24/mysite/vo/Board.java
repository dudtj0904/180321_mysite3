package com.cafe24.mysite.vo;

public class Board {
	private long no;
	private String title;
	private String content;
	private long groupNo;
	private long orderNo;
	private long depth;
	private String regDate;
	private long hit;
	private long userNo;
	private String userName;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(String title, String content, long groupNo, long orderNo, long depth, long userNo, String userName) {
		super();
		this.title = title;
		this.content = content;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.userNo = userNo;
		this.userName = userName;
	}
	
	public Board(long no, String title, long groupNo, long orderNo, long depth, String regDate, long hit, long userNo) {
		super();
		this.no = no;
		this.title = title;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.regDate = regDate;
		this.hit = hit;
		this.userNo = userNo;
	
	}
	
	public Board(long no, String title, long groupNo, long orderNo, long depth, String regDate, long hit, long userNo, String userName) {
		super();
		this.no = no;
		this.title = title;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.regDate = regDate;
		this.hit = hit;
		this.userNo = userNo;
		this.userName = userName;
	}


	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getDepth() {
		return depth;
	}

	public void setDepth(long depth) {
		this.depth = depth;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getHit() {
		return hit;
	}

	public void setHit(long hit) {
		this.hit = hit;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth="
				+ depth + ", regDate=" + regDate + ", hit=" + hit + ", userNo=" + userNo+"]";
	}
	
}
