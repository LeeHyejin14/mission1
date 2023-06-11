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
	int id = Integer.parseInt(URLDecoder.decode(request.getParameter("id"), "UTF-8"));

	wifiService wifiService = new wifiService();
	wifiService.deleteBookmark(id);
	%>
</body>
</html>