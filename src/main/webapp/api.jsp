<%@page import="db.LibDto"%>
<%@page import="db.apiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	text-align: center;
}
</style>
</head>
<body>
	<%
	apiService apiService = new apiService();
	int count = apiService.api();
	%>

	<div>
		<h3><%=count%>개의 WIFI 정보를 정상적으로 저장하였습니다.
		</h3>
	</div>


	<div>
		<a href="wifi.jsp">홈으로 가기</a>
	</div>

</body>
</html>