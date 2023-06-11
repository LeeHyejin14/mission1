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
	String oldName = request.getParameter("existingName");
	int oldTurn = Integer.parseInt(request.getParameter("existingTurn"));
	String newName = URLDecoder.decode(request.getParameter("name"), "UTF-8");
	int newTurn = Integer.parseInt(URLDecoder.decode(request.getParameter("turn"), "UTF-8"));

	wifiService wifiService = new wifiService();
	wifiService.updateBookmarkGroup(oldName, oldTurn, newName, newTurn);
	%>
</body>
</html>