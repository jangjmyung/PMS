<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<!-- Content Start -->
	<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
		<tr>
			<td class="gubunH">서버명</td>
			<td class="gubunH">IP</td>
			<td width="7%" class="gubunH">초당 메시지<br>처리 건수</td>
			<td width="7%" class="gubunH">발송<br>시도건수</td>
			<td width="7%" class="gubunH">발송건수</td>
			<td width="7%" class="gubunH">발송<br>대기건수</td>
			<td width="7%" class="gubunH">평균 발송<br>처리시간</td>
			<td width="7%" class="gubunH">응답<br>대기건수</td>
			<td width="7%" class="gubunH">평균 요청<br>응답시간</td>
			<td width="7%" class="gubunH">리포트<br>대기건수</td>
			<td width="7%" class="gubunH">JobID 할당<br>대기 건수</td>
			<td width="7%" class="gubunH">NPDB<br>조회수</td>
			<td width="7%" class="gubunH">세션수</td>
			<td width="7%" class="gubunH">임계치<br>설정</td>
		</tr>
		<c:forEach var="i" items="${ perfMas}">
			<tr>
				<td bgcolor="#f3f3f3" class="center">${i.serverName}</td>
				<td bgcolor="#f3f3f3" class="center">${i.ip}</td>
				<td class="right">${i.AVR_RMPS}</td>
				<td class="right">${i.CUR_PM + i.CUR_MQ}</td>
				<td class="right">${i.CUR_PM}</td>
				<td bgcolor="#ffedbf" class="right">${i.CUR_MQ}</td>
				<td bgcolor="#ffedbf" class="right">${i.AVR_TMP}</td>
				<td class="right">${i.CUR_WRM}</td>
				<td class="right">${i.AVR_TMR}</td>
				<td bgcolor="#ffedbf" class="right">${i.CUR_RQ}</td>
				<td class="right">${i.CUR_AJ}</td>
				<td class="right">${i.CUR_NQ}</td>
				<td class="right">${i.SESSION_CNT}</td>
				<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
			</tr>
		</c:forEach>
	</table>

<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>