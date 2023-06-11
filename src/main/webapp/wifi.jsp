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
	background-color: #F5F5F5;
	text-align: center;
}

th, td {
	padding: 3px;
}

thead tr {
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
	<h1>와이파이 정보 구하기</h1>
	<%
	String lnt = "0";
	String lat = "0";
	boolean showTable = false; // 테이블 표시 여부를 저장할 변수
	%>

	<div>
		<a href="wifi.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="api.jsp">Open API 와이파이 정보 가져오기</a> | <a
			href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크
			그룹 관리</a>
	</div>

	<label> LAT : <input type="text" placeholder="0.0" id="lat"
		name="lat">
	</label>
	<label>, LNT : <input type="text" placeholder="0.0" id="lnt"
		name="lnt">
	</label>

	<button onclick="getLocation()">내 위치 가져오기</button>
	<button id="wifi">근처 WIFI 정보 보기</button>

	<script>
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				console.log("Geolocation is not supported by this browser.");
			}
		}

		function showPosition(position) {
			document.getElementById("lat").value = position.coords.latitude;
			document.getElementById("lnt").value = position.coords.longitude;
		}

		// WIFI 정보 보기 버튼 클릭 시 테이블 표시
		document
				.getElementById("wifi")
				.addEventListener(
						"click",
						function() {
							var lat = document.getElementById("lat").value;
							var lnt = document.getElementById("lnt").value;
							if (lat !== "" && lnt !== "") {
								showTable = true;
								document.getElementById("wifiTable").style.display = "block";
								// AJAX 요청 및 결과 표시
								var xhttp = new XMLHttpRequest();
								xhttp.onreadystatechange = function() {
									if (this.readyState == 4
											&& this.status == 200) {
										document
												.getElementById("wifiTableBody").innerHTML = this.responseText;
									}
								};
								xhttp.open("POST", "process.jsp", true);
								xhttp.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded");
								xhttp.send("lat=" + lat + "&lnt=" + lnt);
							} else {
								alert("위치 정보를 입력해 주세요.");
							}
						});
	</script>

	<div id="wifiTable">
		<table>
			<thead>
				<tr>
					<th>거리(Km)</th>
					<th>관리번호</th>
					<th>자치구</th>
					<th>와이파이명</th>
					<th>도로명주소</th>
					<th>상세주소</th>
					<th>설치위치(층)</th>
					<th>설치유형</th>
					<th>설치기관</th>
					<th>서비스구분</th>
					<th>망종류</th>
					<th>설치년도</th>
					<th>실내외구분</th>
					<th>WIFI접속환경</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>작업일자</th>
				</tr>
			</thead>
			<tbody id="wifiTableBody">
				<tr>
					<td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
