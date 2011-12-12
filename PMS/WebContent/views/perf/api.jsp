<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td class="gubunH">IP Address</td>
	<td width="11%" class="gubunH">변환 요청 건수</td>
	<td width="11%" class="gubunH">삭제 메시지 요청 건수</td>
	<td width="11%" class="gubunH">업로드 완료 건수</td>
	<td width="11%" class="gubunH">메시지 요청 건수</td>
	<td width="11%" class="gubunH">세션 수</td>
	<td width="11%" class="gubunH">임계치<br>설정</td>
</tr>
<c:forEach var="i" items="${perfApi}">
	<tr>
		<td class="centergray">${i.serverName}</td>
		<td class="centergray">${i.ip}</td>
		<td class="right">${i.curConvertDateReq}</td>
		<td class="right">${i.curDeleteMessageReq}</td>
		<td class="right">${i.curFinishUploadReq}</td>
		<td class="right">${i.curSendmsgReq}</td>
		<td class="right">${i.curSessions}</td>
		<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
	</tr>
</c:forEach>	
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>