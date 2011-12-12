<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td width="11%" class="gubunH">IP</td>
	<td width="11%" class="gubunH">음성<br>요청 건수</td>
	<td width="11%" class="gubunH">음성<br>처리중인 건수</td>
	<td width="11%" class="gubunH">음성<br>대기 건수</td>
	<td width="11%" class="gubunH">팩스<br>요청 건수</td>
	<td width="11%" class="gubunH">팩스<br>처리중인 건수</td>
	<td width="11%" class="gubunH">팩스<br>대기 건수</td>
	<td width="11%" class="gubunH">임계치 설정</td>
</tr>

<c:forEach var="i" items="${perfTms}" >
	<tr>
		<td class="centergray">${i.serverName}</td>
		<td width="11%" class="center">${i.ip}</td>
		<td width="11%" class="right">${i.vmsTotalCall}</td>
		<td width="11%" class="right">${i.vmsProcessingCall}</td>
		<td width="11%"  class="right">${i.vmsQueue}</td>
		<td width="11%" class="right">${i.fmsTotalCall}</td>
		<td width="11%" class="right">${i.fmsProcessingCall}</td>
		<td width="11%" class="right">${i.fmsQueue}</td>
		<td width="11%" class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
	</tr>
</c:forEach>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>