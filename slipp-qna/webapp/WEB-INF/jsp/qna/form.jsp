<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><!DOCTYPE html>
<html>
<head>
<title>질문 답변 게시판</title>
</head>
<body>
	<div id="main">
		<c:set var="method" value="post" />
		<c:if test="${not empty question.questionId}">
		<c:set var="method" value="put" />
		</c:if>
		
		<form:form modelAttribute="question" action="/qna" method="${method}">
			<form:hidden path="questionId"/>
			<table>
				<tr>
					<td width="80">제목</td>
					<td><form:input path="title" size="70"/></td>
				</tr>
				<tr>
					<td width="80">내용</td>
					<td><form:textarea path="contents" rows="5" cols="130"></form:textarea></td>
				</tr>
				<tr>
					<td width="80">태그</td>
					<td><form:input path="plainTags" size="70"/></td>
				</tr>
			</table>
			<input type="submit" value="질문하기" />		
		</form:form>
	</div>
</body>
</html>