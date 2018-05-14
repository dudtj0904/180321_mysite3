<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<!-- board 리스트 시작 -->
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/list" method="post">
					<input type="hidden" name="page" value="0">
					<input type="text" id="kwd" name="kwd" value="${pageinfo.kwd }"> 
					<input type="submit" value="찾기">
				</form>
				<!-- table -->
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<%-- <c:set var="listsize" value="${boardCount }" /> --%>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${pageinfo.boardCount-(pageinfo.page-1)*10-status.index }</td>
							<td style="text-align:left; padding-left:${20*vo.depth}px">
								<c:if test="${vo.depth != 0 }">
									<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" />
								</c:if> <a href="${pageContext.servletContext.contextPath }/board/view?no=${vo.no }&page=${pageinfo.page}&kwd=${pageinfo.kwd }">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${vo.userNo==sessionScope.authUser.no }">
									<a href="${pageContext.servletContext.contextPath }/board/delete?no=${vo.no }&page=${pageinfo.page }&kwd=${pageinfo.kwd }" class="del">
										<img src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
				<!-- table end -->
				<!-- page -->
				<div class="pager">

					<ul>
						<c:if test="${pageinfo.previousBar == true}">
							<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${pageinfo.startPageNumber-1 }&kwd=${pageinfo.kwd }">◀</a></li>
						</c:if>
						<c:forEach begin="${pageinfo.startPageNumber }"
									end="${pageinfo.endPageNumber }" step="1" var="i">
									<c:choose>
										<c:when test="${i==pageinfo.page }">
											<li class="selected">${i }</li>
										</c:when>
										<c:when test="${i > pageinfo.totalPageCount }">
											<li>${i }</li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${i }&kwd=${pageinfo.kwd }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						<c:if test="${pageinfo.nextBar == true }">
							<li><a href="${pageContext.servletContext.contextPath }/board/list?page=${pageinfo.endPageNumber+1 }&kwd=${pageinfo.kwd }">▶</a></li>
						</c:if>
					</ul>


				</div>
				<!-- page end -->

				<div class="bottom">
					<c:if test="${sessionScope.authUser != null }">
						<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
					</c:if>
					
				</div>
			</div>
			<!-- board end -->
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>