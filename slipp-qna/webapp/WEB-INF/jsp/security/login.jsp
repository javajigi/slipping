<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>사용자 관리</title>
</head>
<body>
	<div id="main">
		<form name="userForm" method="post" action="/security/login">
			<table>
				<tr>
					<td>사용자 아이디</td>
					<td><input type="text" name="j_username"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="j_password"></td>
				</tr>
			</table>
			<input type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>