<%@page import="db.LibDto"%>
<%@page import="java.util.List"%>
<%@page import="db.wifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

th, td {
	padding: 5px;
}

th {
	background-color: #3CB371;
	color: #ffffff;
}

tbody tr:nth-child(2n) {
	background-color: #F5F5F5;
}

tbody tr:nth-child(2n+1) {
	background-color: #ffffff;
}
</style>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String selectedGroup = request.getParameter("selectedGroup");
	String main_nm = request.getParameter("main_nm");

	wifiService wifiService = new wifiService();
	wifiService.insertBookmark(selectedGroup, main_nm);
	List<LibDto> libList = wifiService.selectBookmark();
	%>

	<h1>북마크 목록</h1>

	<div>
		<a href="wifi.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="api.jsp">Open API 와이파이 정보 가져오기</a> | <a
			href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크
			그룹 관리</a>
	</div>

	<button onclick="location='bookmark-group-add.jsp'">북마크 그룹 이름
		추가</button>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (libList != null && !libList.isEmpty()) {
				for (LibDto libdto : libList) {
			%>
			<tr>
				<td><%=libdto.getId()%></td>
				<td><%=libdto.getName()%></td>
				<td><%=libdto.getMain_nm()%></td>
				<td><%=libdto.getRegistration_date()%></td>
				<td style="text-align: center;"><a
					href="bookmark-delete.jsp?id=<%=libdto.getId()%>&name=<%=libdto.getName()%>&main_nm=<%=libdto.getMain_nm()%>&registration_date=<%=libdto.getRegistration_date()%>">삭제</a>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="6" style="text-align: center;">정보가 존재하지 않습니다.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

</body>
</html>
