<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td width="8%" class="gubunH">구분</td>
	<td width="8%" class="gubunHy">세션상태</td>
	<td width="8%" class="gubunHy">평균<br>처리시간</td>
	<td width="8%" class="gubunH">팩스발송<br>시도건수</td>
	<td width="8%" class="gubunH">음성발송<br>시도건수</td>
	<td width="8%" class="gubunH">전송중인<br>팩스건수</td>
	<td width="8%" class="gubunH">통화중인<br>음성건수</td>
	<td width="8%" class="gubunH">처리중<br>메시지 건수</td>
	<td width="8%" class="gubunH">대기중<br>메시지 건수</td>
	<td width="8%" class="gubunHy">성공률</td>
	<td width="8%" class="gubunH">임계치 설정</td>
</tr>
<tr>
	<td class="centergray"></td>
	<td class="centergray"></td>
	<td class="centery"></td>
	<td class="centery"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="centery"></td>
	<td class="center"></td>
</tr>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>