<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/WEB-INF/jsp/include/tags.jspf"%><%@
taglib prefix="qna" tagdir="/WEB-INF/tags/qnas"%>
<!DOCTYPE html>
<html>
<head>
<title>질문 답변 게시판</title>
</head>
<body>
	<div id="main">
		<table>
            <tr>
                <td height="400" valign="top">
                    <table border="1">		      
			            <tr>
			                <td>제목</td>
			                <td width="650">${question.title}</td>
			            </tr>
			            <tr>
			                <td>내용</td>
			                <td>${sf:br(question.contents)}</td>
			            </tr>
			            <tr>
			                <td>태그</td>
			                <td>
			                <c:forEach items="${question.tags}" var="each" >
			                ${each.name}&nbsp;&nbsp;
			                </c:forEach>
			                </td>
			            </tr>
                    </table>
                </td>
                <td height="400" valign="top">
                    <qna:tags tags="${tags}"/>
                </td>
            </tr>		
		</table>

		<br /> <a href="/qna">목록으로</a>&nbsp;&nbsp;<a href="/qna/${question.questionId}/form">수정하기</a>
	</div>
</body>
</html>