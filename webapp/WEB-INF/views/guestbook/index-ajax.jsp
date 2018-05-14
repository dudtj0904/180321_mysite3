<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<style type="text/css">
/*-----------------------------*/
* { /*컨텐츠 너비 가장자리 선 포함하여 계산(width = content-width+padding+border)*/
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-o-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
}
#guestbook ul li {
	position: relative;
}

#guestbook ul li a {

	position: absolute;
	left: 23px;
	top: 50px;
}

#guestbook-img {
	width: 35px;
	float: left;
	margin-right: 5px;
}

#guestbook #add-form {
	width: 540px;
	margin-top: 10px;
}

#guestbook #add-form input, #guestbook #add-form textarea {
	display: block;
	width: 530px;
	margin: 10px 0;
}

#guestbook #add-form input {
	padding: 5px 0 5px 10px;
}

#guestbook #add-form input[type=submit] {
	background-color: white;
	border: 1px solid #999;
	color: #A88068;
	margin-bottom: 20px;
	padding-left: 10px;
}

#guestbook #add-form textarea {
	padding: 30px 0 30px 10px;
}

#guestbook ul li strong, #guestbook ul li a {
	display: block;
}

.list-buttons {
	float: left;
	margin-top: 5%;
}
.list-buttons a.delete-image {
		background: url('${pageContext.request.contextPath}/assets/images/delete.png') 0 0 no-repeat;
		width: 50px;
		height: 50px;
		font-size: 0;

}
.list-buttons img.user-image { /*사람 이미지 크기*/
	width: 3.4em;
	height: 3.6em;
}

.list-contents {
	width: 470px;
	margin: 0 0 20px 60px;
}

.list-contents .list-title {
	display: block;
	margin: 5px 0;
	color: #A8ACB7;
}

.list-contents .list-content {
	border: 1px solid #ECEEEE;
	border-radius: 5px;
	background-color: #FAFAFA;
	padding: 10px;
	line-height: 20px;
}

</style>
<script>
/* jQuery plugin */
(function($) { // $:변수
	$.fn.hello = function() {
		var $element = $(this);
		console.log($element.attr('id') + ':hello~');
	}
})(jQuery);

var isEnd = false;
var ejsListItem = new EJS({
	url: '${pageContext.request.contextPath }/assets/js/ejs/templete/listitem.ejs'	
});

var messageBox = function(title, message, callback) {
	$('#dialog-message').attr('title', title);
	$('#dialog-message p').text(message);
	
	$('#dialog-message').dialog({
		modal: true,
		buttons: {
			"확인": function() {
				$(this).dialog("close");
			}
		},
		//callback 안넣어주면 null로 들어오는데 그 때 빈 함수 리턴, 넣어주면 callback 리턴
		close: callback || function() {} 
		
	})
}

var render = function(mode, vo) {
	var html = ejsListItem.render(vo);
		/* '<li data-no="'+vo.no+'">' +
			'<div class="list-buttons">' +
				'<strong><img class="user-image"' +
					'src="${pageContext.request.contextPath }/assets/images/user.png" /></strong>' +
				'<a href="#" data-no="'+vo.no+'" class="delete-image">삭제</a>' +
			'</div>' +
			'<div class="list-contents">' +
				'<strong class="list-title">'+vo.name+'</strong>' +
				'<p class="list-content">' +
					g:global i:ignore(대소문자 무시)
					vo.content.replace(/\n/gi, "<br>") +
				'</p>' +
			'</div>' +
		'</li>'; */	
	if(mode == true) {
		$('#list-guestbook').prepend(html);
	} else {
		$('#list-guestbook').append(html);	
	}
	// $('#list-guestbook')[mode ? "prepend" : "append"](html);
		
};
var fetchList = function() {
	
	if(isEnd == true) {
		return;
	}
	var startNo = $('#list-guestbook li').last().data('no') || 0; 
	$.ajax({
		url:'/mysite3/api/guestbook/list?no='+startNo,
		type:'GET',
		dataType: 'json',
		success: function(response) {
			
			/* 성공유무 */
			if(response.result != 'success') {
				console.log(response.message);
				return;
			}
			
			/* 끝 감지 */
			if(response.data.length < 5) {
				isEnd = true;
				$("#btn-fetch").prop("disabled", true);
			}
			/* render */
			$.each(response.data, function(index, vo) {
				render(false, vo);
			});
		}
	});
}
$(function() {
	/* 삭제 시 비밀번호 입력 모달 다이얼로그 */
	var deleteDialog = $("#dialog-delete-form").dialog({
		autoOpen : false, //자동으로 띄우지 X
		modal : true,
		buttons : {
			'삭제' : function() {
				var password = $('#password-delete').val();
				var no = $('#hidden-no').val();
				console.log("삭제 "+password+":"+no);
				
				// ajax 통신
				$.ajax({
					url: '/mysite3/api/guestbook/delete',
					type: 'post',
					dataType: 'json',
					data: 'no='+no+'&password='+password,
					success: function(response) {
						if(response.result == 'fail') {
							console.log(response.message);
							return;
						}
						if(response.data == -1) { //비번틀림
							$('.validateTips.normal').hide();
							$('.validateTips.error').show();
							$('#password-delete').val('');
							return;
						}
						$('#list-guestbook li[data-no='+response.data+']').remove();
						deleteDialog.dialog('close');
					}
				});
			},
			'취소' : function() {
				deleteDialog.dialog('close');
			}
		},
		close : function() {
			$('#password-delete').val('');
			$('#hidden-no').val('');
			$('.validateTips.normal').show();
			$('.validateTips.error').hide();
		}
	});//deleteDialog
	
	/* Live Event Listener */
	$(document).on('click', '#list-guestbook li a', function(event) {
		event.preventDefault();
		
		var no = $(this).data('no');
		$('#hidden-no').val(no);
		
		deleteDialog.dialog('open');
	});//
	
	$('#add-form').submit(function(event) {
		event.preventDefault(); // 이벤트의 동작을 멈춤
		/* 
		var queryString = $(this).serialize();
		console.log(queryString);
		*/
		var datas = {};
		$.each($(this).serializeArray(), function( index, obj) {
			datas[obj.name] = obj.value;
			
		});
		if(datas['name'] == '') {
			messageBox('메세지 등록', '이름이 비어 있습니다.', function(){ $('#input-name').focus() });
			return;
		}
		if(datas['password'] == '') {
			messageBox('메세지 등록', '비밀번호가 비어 있습니다.', function(){ $('#input-password').focus() });
			return;
		}
		if(datas['content'] == '') {
			messageBox('메세지 등록', '내용이 비어 있습니다.', function(){ $('#tx-content').focus() });
			return;
		}
		
		$.ajax({
			url: '/mysite3/api/guestbook/insert',
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(datas), //js에서 지원
			success: function(response) {
				render(true, response.data);	
				$('#add-form')[0].reset();
			}
				
		}); 
		
	});//$('#add-form')
	
	$('#btn-fetch').click(function() {
		fetchList();
	});//$('#btn-fetch')
	
	$(window).scroll(function() {
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		//console.log(scrollTop+":"+windowHeight+":"+documentHeight);
		
		/* scrollbar의 thumb가 바닥 전 30px 까지 도달 */
		if(scrollTop + windowHeight + 30 > documentHeight) {
			fetchList();
		}
	});
	
	//최초 리스트 가져오기
	fetchList();
	
	/* plugin test */
	$('#container').hello();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<img alt="guestbook" src="${pageContext.request.contextPath }/assets/images/guestbook.png" id="guestbook-img"><h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름">
					<input type="password" id="input-password" name="password" placeholder="비밀번호">
					<textarea id="tx-content" name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<hr>
				<ul id="list-guestbook">

				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<button id="btn-fetch">가져오기</button>
			
			<div id="dialog-message" title="tets" style="display:none"> 
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbookajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>