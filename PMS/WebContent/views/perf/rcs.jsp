<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td class="gubunH">IP</td>
	<td width="15%" class="gubunH">서비스 제공자 수</td>
	<td width="15%" class="gubunH">서비스 제공자 접속 수</td>
	<td width="15%" class="gubunH">서비스 요청자 수</td>
	<td width="15%" class="gubunH">서비스 요청자 접속 수</td>
	<td width="15%" class="gubunH">초당 요청 수</td>
</tr>

	<c:forEach var="i" items="${perfRcs}">
		<tr>
			<td class="centergray">${i.serverName}</td>
			<td class="centergray">${i.ip}</td>
			<td class="right">${i.provCont}</td>
			<td class="right">${i.provConCnt}</td>
			<td class="right">${i.consCnt}</td>
			<td class="right">${i.consConCnt}</td>
			<td class="right">${i.reqPerSec}</td>
		</tr>
	</c:forEach>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td style="font-size:14px; padding:20 0 7 0; font-weight:bold">서비스 감시 로그</td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="20%" class="gubunH">서버명</td>
	<td class="gubunH">내용</td>
	<td width="20%" class="gubunH">일시</td>
</tr>
<!-- 
<tr>
	<td class="center">서버명</td>
	<td class="default">22222</td>
	<td class="center">2011-08-18 10:12:55</td>
</tr>
<tr>
	<td class="center">서버명</td>
	<td class="default">22222</td>
	<td class="center">2011-08-18 10:12:55</td>
</tr>
<tr>
	<td class="center">서버명</td>
	<td class="default">22222</td>
	<td class="center">2011-08-18 10:12:55</td>
</tr>
<tr>
	<td class="center">서버명</td>
	<td class="default">22222</td>
	<td class="center">2011-08-18 10:12:55</td>
</tr>
<tr>
	<td class="center">서버명</td>
	<td class="default">22222</td>
	<td class="center">2011-08-18 10:12:55</td>
</tr>
-->
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>