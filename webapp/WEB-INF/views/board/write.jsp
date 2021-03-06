<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/write">
				<input type="hidden" name="userNo" value="${sessionScope.authUser.no }">
				<input type="hidden" name="userName" value="${sessionScope.authUser.name }">
					<c:if test="${replyboard != null }">
						<input type="hidden" name="page" value="${pageinfo.page }">
						<input type="hidden" name="kwd" value="${pageinfo.kwd }">
						<input type="hidden" name = "groupNo" value="${replyboard.groupNo }">
						<input type="hidden" name = "depth" value="${replyboard.depth + 1}">
						<c:choose>
							<c:when test="${replyboard.depth >= 1 }">
								<input type="hidden" name = "orderNo" value="${replyboard.orderNo + 1 }">
							</c:when>
							<c:otherwise>
								<input type="hidden" name = "orderNo" value="${maxorderno + 1 }">
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${writeboard != null }">
						<input type="hidden" name="page" value="0">
						<input type="hidden" name="kwd" value="">
						<input type="hidden" name = "groupNo" value="${writeboard.groupNo + 1 }">
						<input type="hidden" name = "orderNo" value="1">
						<input type="hidden" name = "depth" value="0">
					</c:if>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board/list?page=${pageinfo.page}&kwd=${pageinfo.kwd }">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>