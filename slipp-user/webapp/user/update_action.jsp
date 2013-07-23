<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>
<%
	User user = new User(
			request.getParameter("userId"), 
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("email"));
	UserService userService = new UserService();
	userService.update(user);
	
	response.sendRedirect("/");
%>