<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td class="gubunH">IP</td>
	<td width="15%" class="gubunH">현재 다운로드<br>요청 건수</td>
	<td width="15%" class="gubunH">현재 업로드<br>요청 건수</td>
	<td width="15%" class="gubunH">초당<br>다운로드 건수</td>
	<td width="15%" class="gubunH">초당<br>업로드 건수</td>
	<td width="15%" class="gubunH">임계치<br>설정</td>
</tr>
<c:forEach var="i" items="${perfFus}">
	<tr>
		<td class="centergray">${i.serverName}</td>
		<td class="centergray">${i.ip}</td>
		<td class="right">${i.curDownRequest}</td>
		<td class="right">${i.curUploadRequest}</td>
		<td class="right">${i.downloadPerSec}</td>
		<td class="right">${i.uploadPerSec}</td>
		<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
	</tr>
</c:forEach>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>