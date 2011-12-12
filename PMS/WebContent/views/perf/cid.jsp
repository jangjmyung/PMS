<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>

<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="330" style="font-weight:bold">메시지별&nbsp;&nbsp;<input type="radio" style="margin-left:-3px">SMS <input type="radio" style="margin-left:-3px">LMS <input type="radio" style="margin-left:-3px">MMS <input type="radio" style="margin-left:-3px">FMS <input type="radio" style="margin-left:-3px">VMS</td>
			<td style="font-weight:bold">이통사별&nbsp;&nbsp;<input type="radio" style="margin-left:-3px">KT <input type="radio" style="margin-left:-3px">SKT <input type="radio" style="margin-left:-3px">LGU+ <input type="radio" style="margin-left:-3px">기타</td>
			<td style="text-align:right;line-height:18px "><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >조회</span></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH">CID명</td>
	<td width="7%" class="gubunH">SMPP<br>Type</td>
	<td width="7%" class="gubunH">MSG<br>Type</td>
	<td width="7%" class="gubunH">전체<br>시도건수</td>
	<td width="7%" class="gubunH">MCS 발송<br>대기건수</td>
	<td width="9%" class="gubunH">이통사 응답<br>대기건수</td>
	<td width="7%" class="gubunH">리포트<br>대기건수</td>
	<td width="7%" class="gubunH">리포트<br>완료건수</td>
	<td width="7%" class="gubunH">발송처리<br>완료건수</td>
	<td width="7%" class="gubunH">발송<br>성공률(%)</td>
	<td width="7%" class="gubunH">최소<br>성공률(%)</td>
	<td width="7%" class="gubunH">세션상태</td>
	<td width="7%" class="gubunH">임계치<br>설정</td>
</tr>
<tr>
	<td class="center"></td>
	<td class="center"></td>
	<td class="center"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="right"></td>
	<td class="center"></td>
	<td class="center"><span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >조회</span></td>
</tr>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>