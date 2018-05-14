<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td colspan="3">${boarddetail.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td colspan="3">
							<div class="view-content">
								${fn:replace(boarddetail.content,newLine,"<br>") }
							</div>
						</td>
					</tr>
				</table>
				<!-- comment table -->
				<table class="tbl-ex">
					<tr>
						<th colspan="5">댓글</th>
					</tr>
					<c:if test="${comments != null }">
						<c:forEach items="${comments }" var="comment" varStatus="status">
							<tr><!-- comment -->
								<td class="label"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" ></td>
								<td>
									${status.count }
								</td>
								<td>
									<div class="view-content">
										${comment.content }
									</div>
								</td>
								<td>
								 ${comment.userName }
								</td>
								<td>
									<c:if test="${comment.userNo==sessionScope.authUser.no }">
										<a href="${pageContext.servletContext.contextPath }/comment/delete?no=${comment.no }&page=${pageinfo.page}&kwd=${pageinfo.kwd }" class="del">
											<img src="${pageContext.servletContext.contextPath }/assets/images/recycle.png">삭제</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				
				<div class="bottom">
					<c:if test="${sessionScope.authUser != null }">
						<form method="post" action="${pageContext.servletContext.contextPath }/comment/insert" style="float:left;">
							<input type="hidden" name="boardNo" value="${boarddetail.no }">
							<input type="hidden" name="userNo" value="${sessionScope.authUser.no }">
							<input type="hidden" name="userName" value="${sessionScope.authUser.name }">
							<input type="hidden" name="page" value="${pageinfo.page }" >
							<input type="hidden" name="kwd" value="${pageinfo.kwd }" >
							<input type="text" name="content" value="" size="20">
							<input type="submit" value="댓글작성">
						</form>
						<c:if test="${boarddetail.userNo == sessionScope.authUser.no }">
							<a href="${pageContext.servletContext.contextPath }/board/update?no=${boarddetail.no }&page=${pageinfo.page}&kwd=${pageinfo.kwd }">글수정</a>	
						</c:if>
						<a href="${pageContext.servletContext.contextPath }/board/reply?no=${boarddetail.no }&page=${pageinfo.page}&kwd=${pageinfo.kwd }">답글쓰기</a>
					</c:if>
					
					<a href="${pageContext.servletContext.contextPath }/board/list?page=${pageinfo.page}&kwd=${pageinfo.kwd }">글목록</a>
					

				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>