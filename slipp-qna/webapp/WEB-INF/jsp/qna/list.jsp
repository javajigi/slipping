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
					<table>
						<tr>
							<td width="400">제목</td>
							<td width="100">글쓴이</td>
							<td width="150">생성일</td>
						</tr>
						<c:forEach items="${questions.content}" var="each">
							<tr>
								<td width="400"><a href="/qna/${each.questionId}">${each.title}</a></td>
								<td width="100">${each.writerName}</td>
								<td width="150"><fmt:formatDate
										pattern="yyyy-MM-dd hh:mm:ss" value="${each.createdDate}" /></td>
							</tr>
						</c:forEach>
						<tr>
						  <td colspan="3">
					        <!--페이지영역 -->
					        <div class="pageBox">
					            <sl:pager
					                prefixURI="/qna"
					                pageHtml='<a href="{url}">{page}</a>'
					                currentPageHtml='<a href="#" class="active">{page}</a>'
					                pageSeparator=" "
					                pagesWrapHtmlHead='<span class="paging">'
					                pagesWrapHtmlTail='</span>'
					                prevGroupHtml=' <a href="{url}" class="page_prev">이전</a> '
					                nextGroupHtml=' <a href="{url}" class="page_next">다음</a> '
					                pageGroupSize="10"
					                page="${questions}" />
					        </div>
					        <!--페이지영역 -->						  
						  </td>
						</tr>
					</table>
				</td>
				<td height="400" valign="top">
					<qna:tags tags="${tags}"/>
				</td>
			</tr>
		</table>

		<br /> <a href="/qna/form">질문하기</a>
	</div>
</body>
</html>