<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>
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
<%
	User user = new User(
			request.getParameter("userId"), 
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("email"));
	UserService userService = new UserService();
	try {
		User joinedUser = userService.join(user);
%>
				<section id="typography">
				<div class="page-header">
					<h1>회원가입</h1>
				</div>
				<div class="messageForm">
					<p><%= joinedUser.getUserId() %> 계정으로 회원가입 완료되었습니다.</p>
				</div>
<%		
	} catch (ExistedUserException e) {
%>
				<div class="error">
					<%= e.getMessage() %>
				</div>	
<%		
	}
%>
			</div>
		</div>
	</div>
</body>
</html>