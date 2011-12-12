<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">서버명</td>
	<td width="17%" class="gubunH">변환 대기건수</td>
	<td width="17%" class="gubunH">처리건수</td>
	<td width="17%" class="gubunH">평균처리시간</td>
	<td width="17%" class="gubunH">성공률</td>
	<td width="17%" class="gubunH">임계치<br>설정</td>
</tr>
<tr>
	<td class="centergray"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span></td>
</tr>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>