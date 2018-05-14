<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript" ></script>
<script>
$(function() {
	var passwordcheck = $('#passwordcheck').val();
	var password = $('#password').val();
	$('#passwordcheck').change(function() {
		$.ajax({
			url:'${pageContext.servletContext.contextPath }/api/user/checkpassword?password='+password+'&passwordcheck='+passwordcheck,
			type:'get',
			data:'',
			dataType:'json',
			success: function(response) {
				if(response.result != 'success') {
					console.log(response.message);
					return;
				}
				if(response.message == 'not equal') {
					console.log('not equal');
					$('#p-passwordcheck').show();
					return;
				}
				console.log('equal');
				$('#p-passwordcheck').hide();
			},
			error: function() {
				
			}
		});
		
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/modify">
				<input type="hidden" name="no" value="${sessionScope.authUser.no }">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${sessionScope.authUser.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${sessionScope.authUser.email }" readonly="readonly">
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					<label class="block-label">패스워드확인</label>
					<input id="passwordcheck" name="passwordcheck" type="password" value="">
					<p id="p-passwordcheck" style="display:none;">입력한 패스워드와 다릅니다.</p>
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${sessionScope.authUser.gender == "female" }'>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">	
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<c:if test='${result == "fail" }'>
						<p>
						회원 수정에 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="변경하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="user" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>