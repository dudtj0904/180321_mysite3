package com.cafe24.mysite.vo;

public class PageInfo {
	private Integer page; 	//현재 페이지
	private String kwd;		//키워드
	private int boardCount;			// 전체 게시글 수
	private int pageBoardCount;		// 한 페이지 당 게시글 수
	private int startPageNumber; 	// 페이지 시작 번호
	private int endPageNumber;		// 페이지 끝 번호
	private boolean previousBar;	// 이전 페이지로 가는 버튼
	private boolean nextBar;		// 다음 페이지로 가는 버튼
	private int totalPageCount;		// 전체 페이지 수
	
	
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}

	public PageInfo(Integer page) {
		super();
		this.page = page;
	}

	public PageInfo(String kwd) {
		super();
		this.kwd = kwd;
	}

	public PageInfo(Integer page, String kwd) {
		super();
		this.page = page;
		this.kwd = kwd;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getKwd() {
		return kwd;
	}

	public void setKwd(String kwd) {
		this.kwd = kwd;
	}
	
	

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getPageBoardCount() {
		return pageBoardCount;
	}

	public void setPageBoardCount(int pageBoardCount) {
		this.pageBoardCount = pageBoardCount;
	}

	public int getStartPageNumber() {
		return startPageNumber;
	}

	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

	public boolean isPreviousBar() {
		return previousBar;
	}

	public void setPreviousBar(boolean previousBar) {
		this.previousBar = previousBar;
	}

	public boolean isNextBar() {
		return nextBar;
	}

	public void setNextBar(boolean nextBar) {
		this.nextBar = nextBar;
	}
	
	

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", kwd=" + kwd + ", boardCount=" + boardCount + ", pageBoardCount="
				+ pageBoardCount + ", startPageNumber=" + startPageNumber + ", endPageNumber=" + endPageNumber
				+ ", previousBar=" + previousBar + ", nextBar=" + nextBar + ", totalPageCount=" + totalPageCount + "]";
	}


}
