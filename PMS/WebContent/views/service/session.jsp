<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>
<%

	String searchType = Util.getRequestParamet(request, "searchType");
	String spID = Util.getRequestParamet(request, "spID");
	String startDate = Util.getRequestParamet(request, "startDate");
	String endDate = Util.getRequestParamet(request, "endDate");
	
	java.util.Date nowDate = new java.util.Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	if(startDate.equals("")) startDate =  dateFormat.format(nowDate);
	if(endDate.equals("")) endDate =  dateFormat.format(nowDate);
	
%>
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<form name="searchFrom" method="post"  action="/service/session.do" >
				<tr>
					<td width="70" style="font-weight:bold">검색구분</td>
					<td style="font-weight:bold; line-height:18px" valign="center" >
						<input type="radio" name="searchType" style="margin-left:-3px"  value="real"  <%= searchType.equals("real") || searchType.isEmpty() ? "checked" : ""  %> >실시간&nbsp;
						<input type="radio" name="searchType" value="period"  <%= searchType.equals("period") ? "checked" : "" %> style="margin-left:-3px">기간 검색&nbsp;&nbsp;고객ID&nbsp;
						<input type="text" name="spID" style="margin-left:-3px" value=<%=spID %>>&nbsp;&nbsp;검색기간&nbsp;
						<input type="text" size="10" name='startDate' class=calendar value="<%=startDate%>">&nbsp;
						~
						<input type="text" size="10" name="endDate" class="calendar" value="<%=endDate%>">&nbsp;&nbsp;
						<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" onclick="searchFrom.submit();" >조회</span>
					</td>
				</tr>
			</form>
		</table>
	</td>
</tr>
</table>
<br>


<c:if test="${serviceSession != null}" >
	<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
	<tr>
		<td class="gubunH">SP ID</td>
		<td width="11%" class="gubunH">End User ID</td>
		<td width="11%" class="gubunH">Client IP</td>
		<td width="11%" class="gubunH">MAS ID</td>
		<td width="11%" class="gubunH">접속 시간</td>
		<td width="11%" class="gubunH">MAS IP</td>
		<td width="11%" class="gubunH">Client 버전</td>
		<td width="11%" class="gubunH">CSDK 버전</td>
	</tr>
	<c:forEach var="i" items="${serviceSession}">
		<tr>
			<td class="centergray">${i.spID}</td>
			<td width="12%" class="center">${i.euID}</td>
			<td width="12%" class="center">${i.clientIP}</td>
			<td width="12%" class="center">${i.masID}</td>
			<td width="12%" class="center">${i.conTime}</td>
			<td width="12%" class="center">${i.masIP}</td>
			<td width="12%" class="center">${i.clientVer}</td>
			<td width="12%" class="center">${i.csdkVer}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${serviceSessionHistory != null }" >
<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
	<tr>
		<td class="gubunH">SP ID</td>
		<td width="11%" class="gubunH">End User ID</td>
		<td width="11%" class="gubunH">Client IP</td>
		<td width="11%" class="gubunH">MAS ID</td>
		<td width="11%" class="gubunH">접속 시간</td>
		<td width="11%" class="gubunH">MAS IP</td>
		<td width="11%" class="gubunH">구분</td>
		<td width="11%" class="gubunH">Client 버전</td>
		<td width="11%" class="gubunH">CSDK 버전</td>
	</tr>
	<tr>
		<td class="centergray"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
		<td width="11%" class="right"></td>
	</tr>
	</table>
</c:if>


<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>