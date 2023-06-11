<%@ page import="java.net.URLDecoder"%>
<%@page import="db.wifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
	int turn = Integer.parseInt(URLDecoder.decode(request.getParameter("turn"), "UTF-8"));

	wifiService wifiService = new wifiService();
	wifiService.deleteBookmarkGroup(name, turn);
	%>
</body>
</html>