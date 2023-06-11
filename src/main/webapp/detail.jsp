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
</style>
</head>
<body>

	<%
	String dis = request.getParameter("dis");
	String mgr_no = request.getParameter("mgr_no");
	String main_nm = request.getParameter("main_nm");

	wifiService wifiService = new wifiService();
	LibDto libdto = wifiService.selectDtail(mgr_no, main_nm);
	List<LibDto> libList = wifiService.selectBookmarkGroup();
	%>

	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="wifi.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="api.jsp">Open API 와이파이 정보 가져오기</a> | <a
			href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크
			그룹 관리</a>
	</div>

	<select name="bookmarkbox">
		<option value="">북마크 그룹 이름 선택</option>
		<%
		for (LibDto libdto2 : libList) {
		%>
		<option value="<%=libdto2.getName()%>"><%=libdto2.getName()%></option>
		<%
		}
		%>
	</select>

	<button onclick="btnDetail()">북마크 추가하기</button>

	<table>
		<colgroup>
			<col style="width: 20%;" />
			<col style="width: 80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th>거리(Km)</th>
				<td><%=dis%></td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td><%=libdto.getMgr_no()%></td>
			</tr>
			<tr>
				<th>자치구</th>
				<td><%=libdto.getWrdofc()%></td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><%=libdto.getMain_nm()%></td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td><%=libdto.getAdres1()%></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><%=libdto.getAdres2()%></td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td><%=libdto.getInstl_floor()%></td>
			</tr>
			<tr>
				<th>설치유형</th>
				<td><%=libdto.getInstl_ty()%></td>
			</tr>
			<tr>
				<th>설치기관</th>
				<td><%=libdto.getInstl_mby()%></td>
			</tr>
			<tr>
				<th>서비스구분</th>
				<td><%=libdto.getSvc_se()%></td>
			</tr>
			<tr>
				<th>망종류</th>
				<td><%=libdto.getCmcwr()%></td>
			</tr>
			<tr>
				<th>설치년도</th>
				<td><%=libdto.getCnstc_year()%></td>
			</tr>
			<tr>
				<th>실내외구분</th>
				<td><%=libdto.getInout_door()%></td>
			</tr>
			<tr>
				<th>WIFI접속환경</th>
				<td><%=libdto.getRemars3()%></td>
			</tr>
			<tr>
				<th>X좌표</th>
				<td><%=libdto.getLat()%></td>
			</tr>
			<tr>
				<th>Y좌표</th>
				<td><%=libdto.getLnt()%></td>
			</tr>
			<tr>
				<th>작업일자</th>
				<td><%=libdto.getWork_dttm()%></td>
			</tr>

		</tbody>
	</table>

	<script>
  function btnDetail() {
    var selectedGroup = document.getElementsByName("bookmarkbox")[0].value;
    var main_nm = encodeURIComponent("<%=main_nm%>");
    
			// XMLHttpRequest 객체 생성
			var xhr = new XMLHttpRequest();

			// 요청 완료 후 처리할 콜백 함수 설정
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4 && xhr.status === 200) {
					// 요청 완료 후 서버의 응답을 처리하는 로직 작성
					var response = xhr.responseText;
					alert("북마크 정보를 추가하였습니다.");
					location.href = "bookmark-list.jsp";
				}
			};

			// 요청 설정
			xhr.open("POST", "bookmark-list.jsp", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");

			// 요청 전송
			xhr.send("selectedGroup=" + selectedGroup + "&main_nm=" + main_nm);
		}
	</script>

</body>
</html>
