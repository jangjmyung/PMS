<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>

<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="14%" class="gubunH">서버명</td>
	<td class="gubunH">IP</td>
	<td width="14%" class="gubunH">현재 요청건수</td>
	<td width="14%" class="gubunH">초당 요청건수</td>
	<td width="14%" class="gubunH">초당 리포트 처리건수</td>
	<td width="14%" class="gubunH">리포트 대기건수</td>
	<td width="14%" class="gubunH">임계치 설정</td>
</tr>

	<c:forEach var="i" items="${perfScs}">
		<tr>
			<td class="centergray">${i.serverName}</td>
			<td class="centergray">${i.ip}</td>
			<td class="right">${i.curRequests}</td>
			<td class="right">${i.reqPerSec}</td>
			<td class="right">${i.reportPerSec}</td>
			<td class="right">${i.queuedReports}</td>
			<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
		</tr>
	</c:forEach>	
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td style="font-size:14px; padding:20 0 7 0; font-weight:bold">아이디별 리포트 대기건수 (상위 00개)</td>
	<td align="right" style="padding:20 0 7 0;text_align:center;line-height:18px"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >검색</span></td>
</tr>
</table>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="25%" class="gubunH">SP ID</td>
	<td width="25%" class="gubunH">Enduser ID</td>
	<td class="gubunH">서버명</td>
	<td width="25%" class="gubunH">리포트 대기건수</td>
</tr>
<tr>
	<td class="center"></td>
	<td class="center"></td>
	<td class="center"></td>
	<td class="right"></td>
</tr>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>