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
	background-color: #DCDCDC;
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

button {
	text-align: center;
}
</style>
</head>
<body>
	<%
	String name = request.getParameter("name");
	int turn = Integer.parseInt(request.getParameter("turn"));
	%>

	<h1>북마크 그룹 삭제</h1>

	<div>
		<a href="wifi.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="api.jsp">Open API 와이파이 정보 가져오기</a> | <a
			href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크
			그룹 관리</a>
	</div>

	<h4>북마크 그룹 이름을 삭제하시겠습니까?</h4>

	<div>
		<table>
			<colgroup>
				<col style="width: 20%;" />
				<col style="width: 80%;" />
			</colgroup>
			<tbody>
				<tr>
					<th>북마크 이름</th>
					<td><input type="text" id="name" name="name" value="<%=name%>"></td>
				</tr>
				<tr>
					<th>순서</th>
					<td><input type="text" id="turn" name="turn" value="<%=turn%>"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><a
						href="bookmark-group.jsp">돌아가기</a>
						<button onclick="btnBookmarkGroupDelete()">삭제</button></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script>
		function btnBookmarkGroupDelete() {
			var name = encodeURIComponent(document.getElementById("name").value);
			var turn = encodeURIComponent(document.getElementById("turn").value);

			// XMLHttpRequest 객체 생성
			var xhr = new XMLHttpRequest();

			// 요청 완료 후 처리할 콜백 함수 설정
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					// 요청 완료 후 서버의 응답을 처리하는 로직 작성
					var response = xhr.responseText;
					alert("북마크 그룹 정보를 삭제하였습니다.");
					location.href = "bookmark-group.jsp";
				}
			};

			// 요청 설정
			xhr.open("POST", "bookmark-group-delete-submit.jsp", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			// 요청 전송
			xhr.send("name=" + name + "&turn=" + turn);
		}
	</script>

</body>
</html>
