<%@page import="db.LibDto"%>
<%@page import="java.util.List"%>
<%@page import="db.wifiService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String lat = request.getParameter("lat");
String lnt = request.getParameter("lnt");
List<LibDto> wifiList = null;

if (lat != null && lnt != null) {
	wifiService wifiService = new wifiService();
	wifiList = wifiService.selectWifi(lat, lnt);
	wifiService.insertHistory(lat, lnt);
}
%>
<table>
	<thead>

	</thead>
	<tbody>
		<%
		if (wifiList != null) {
			for (LibDto libdto : wifiList) {
		%>
		<tr>
			<td><%=libdto.getDistance()%></td>
			<td><%=libdto.getMgr_no()%></td>
			<td><%=libdto.getWrdofc()%></td>
			<td><a
				href="detail.jsp?dis=<%=libdto.getDistance()%>&mgr_no=<%=libdto.getMgr_no()%>&main_nm=<%=libdto.getMain_nm()%>">
					<%=libdto.getMain_nm()%>
			</a></td>
			<td><%=libdto.getAdres1()%></td>
			<td><%=libdto.getAdres2()%></td>
			<td><%=libdto.getInstl_floor()%></td>
			<td><%=libdto.getInstl_ty()%></td>
			<td><%=libdto.getInstl_mby()%></td>
			<td><%=libdto.getSvc_se()%></td>
			<td><%=libdto.getCmcwr()%></td>
			<td><%=libdto.getCnstc_year()%></td>
			<td><%=libdto.getInout_door()%></td>
			<td><%=libdto.getRemars3()%></td>
			<td><%=libdto.getLnt()%></td>
			<td><%=libdto.getLat()%></td>
			<td><%=libdto.getWork_dttm()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>