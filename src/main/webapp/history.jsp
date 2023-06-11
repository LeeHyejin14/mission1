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
	wifiService wifiService = new wifiService();
	List<LibDto> libList = wifiService.selectHistory();
	%>

	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="wifi.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="api.jsp">Open API 와이파이 정보 가져오기</a>
	</div>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
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
				<td><%=libdto.getLnt()%></td>
				<td><%=libdto.getLat()%></td>
				<td><%=libdto.getLookup_date()%></td>
				<td style="text-align: center;">
					<button
						onclick="redirectToPage('history-delete.jsp', '<%=libdto.getId()%>')">삭제</button>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="5" style="text-align: center;">정보가 존재하지 않습니다.</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<script>
		function redirectToPage(pageUrl, id) {
			var url = pageUrl + '?id=' + encodeURIComponent(id);
			location.href = url;
		}
	</script>

</body>
</html>
