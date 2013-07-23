<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>
<%
User loginUser = (User)session.getAttribute("loginUser");
UserService userService = new UserService();
User user = userService.findByUserId(loginUser.getUserId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>

<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>회원가입</h1>
				</div>
				<form class="form-horizontal" action="/user/update_action.jsp" method="post">
					<input type="hidden" name="userId" value="<%= user.getUserId() %>" />
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
							<%= user.getUserId() %>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="password" name="password" placeholder="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<input type="text" id="name" name="name" placeholder="" value="<%= user.getName() %>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<input type="text" id="email" name="email" placeholder="" value="<%= user.getEmail() %>" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">개인정보수정</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>