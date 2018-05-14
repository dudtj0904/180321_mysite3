package com.cafe24.mysite.paging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.PageInfo;

@Component
public class Paging {
	
	@Autowired
	BoardService boardService;
	
	public PageInfo pagingCalculator(PageInfo pageinfo, int onePageBoardCount) {
		pageinfo.setPageBoardCount(onePageBoardCount);
		
		pageinfo.setBoardCount(maxBoardCount(pageinfo.getKwd()));
		
		pageinfo.setTotalPageCount(totalPageCountSetting(pageinfo));
		
		pageinfo.setPage(currentPageNumberSetting(pageinfo));
		
		pageinfo.setEndPageNumber(endPageNumberSetting(pageinfo));
				
		pageinfo.setStartPageNumber(startPageNumberSetting(pageinfo));
		
		pageinfo = barSetting(pageinfo);
		
		return pageinfo;
		
	}//end
	
	public int totalPageCountSetting(PageInfo pageinfo) {
		int totalCountDiv = pageinfo.getBoardCount()%pageinfo.getPageBoardCount();
		int totalCount = 0;
		if(totalCountDiv == 0) {
			totalCount = pageinfo.getBoardCount()/pageinfo.getPageBoardCount();
		} else {
			totalCount = pageinfo.getBoardCount()/pageinfo.getPageBoardCount()+1;
		}
		return totalCount;
	}
	
	public PageInfo barSetting(PageInfo pageinfo) {
		if(pageinfo.getPage() <= 5) pageinfo.setPreviousBar(false);
		else pageinfo.setPreviousBar(true);
		
		if(pageinfo.getTotalPageCount() > pageinfo.getEndPageNumber()) {
			pageinfo.setNextBar(true);
		} else {
			pageinfo.setNextBar(false);
		}
		
		return pageinfo;
	}
	
	public int startPageNumberSetting(PageInfo pageinfo) {
		return pageinfo.getEndPageNumber() - 4;
	}//end
	
	public int endPageNumberSetting(PageInfo pageinfo) {
		int page = pageinfo.getPage();
		int endPageNumber = 0;
		
		if(page % 5 == 0) {
			endPageNumber = page;
		} else {
			endPageNumber = ((page / 5) + 1) * 5;
		}
		
		return endPageNumber;
	}
	
	public int maxBoardCount(String kwd) {
		List<Board> list = boardService.getBoardList(kwd);
		return list.size();
	}//end
	
	public int currentPageNumberSetting(PageInfo pageinfo) {
		if(pageinfo.getPage() == 0) {
			return 1;
		}

		return pageinfo.getPage();
	}//end
}
