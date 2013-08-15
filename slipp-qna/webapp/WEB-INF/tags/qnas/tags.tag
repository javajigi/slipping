<%@tag language="java" pageEncoding="utf-8"%><%@ tag body-content="empty"
%><%@attribute name="tags" required="true" rtexprvalue="true" type="java.lang.Object" description="태그 목록"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
    <tr>    
        <td>            
        <c:forEach items="${tags}" var="each">
            ${each.name} X ${each.taggedCount} <br/>
        </c:forEach>
        </td>
    </tr>
</table>