<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/perfcommonhead.jsp" %>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="10%"  class="gubunH" rowspan="2">ID</td>
	<td width="10%"  class="gubunH" rowspan="2">고객명</td>
	<td width="10%"  class="gubunH" rowspan="2">발송 건수</td>
	<td width="30%"  class="gubunH" colspan="3">성공 건수 감시</td>
	<td width="30%"  class="gubunH" colspan="3">성공율 감시</td>
	<td width="10%"  class="gubunH" rowspan="2">임계치 설정</td>
</tr>
<tr>
	<td width="10%" class="gubunH" >성공 건수</td>
	<td width="10%"  class="gubunH" >최소 성공 건수</td>
	<td width="10%"  class="gubunH" >상태</td>
	<td width="10%"  class="gubunH" >성공율</td>
	<td width="10%"  class="gubunH" >최소 성공율</td>
	<td width="10%"  class="gubunH" >상태</td>
</tr>
<tr>
	<td width="10%"  class="centergray"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="right"></td>
	<td width="10%"  class="center"><div id="btnR"><a href="#"><img src="/images/btn2.gif" width="35" height="19"><img src="/images/btn2_on.gif" width="35" height="19" class="over"></a></div></td>
</tr>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>