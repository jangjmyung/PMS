<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>

<%
	String searchFailure = Util.getRequestParamet(request, "searchFailure");
	String spID = Util.getRequestParamet(request, "spID");
	String startDate = Util.getRequestParamet(request, "startDate");
	String endDate = Util.getRequestParamet(request, "endDate");
	
	java.util.Date nowDate = new java.util.Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	if(startDate.equals("")) startDate =  dateFormat.format(nowDate);
	if(endDate.equals("")) endDate =  dateFormat.format(nowDate);
	if(searchFailure.equals("")) searchFailure = "all";
%>

<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<form name="searchFrom" method="post"  action="/service/failure.do" >
				<tr>
					<td width="70" style="font-weight:bold">검색 기간</td>
					<td style="font-weight:bold; line-height:18px" valign="center" >
						<input type="text" size="10" name='startDate' class=calendar value="<%=startDate%>">&nbsp;
						~
						<input type="text" size="10" name="endDate" class="calendar" value="<%=endDate%>">&nbsp;&nbsp;고객ID&nbsp;
						<input type="text" name="spID" style="margin-left:-3px" value=<%=spID %>>&nbsp;&nbsp;장애 유형&nbsp;
						<select name="searchFailure">
							<option value="all"  <%=searchFailure.equals("all") ? "selected" : "" %> >전체</option>
							<option value="cid"  <%=searchFailure.equals("cid") ? "selected" : "" %> >CID 에러</option>
							<option value="session"  <%=searchFailure.equals("session") ? "selected" : "" %> >세션 단절</option>
							<option value="rate"  <%=searchFailure.equals("rate") ? "selected" : "" %> >성공율 미만</option>
						</select>&nbsp;&nbsp;
						<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" onclick="searchFrom.submit();" >조회</span>
					</td>
				</tr>
			</form>
		</table>
	</td>
</tr>
</table>
<br>

<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="10%" class="gubunH">장애 일시</td>
	<td width="10%" class="gubunH">복구 일시</td>
	<td width="10%" class="gubunH">장애 유형</td>
	<td width="10%" class="gubunH">구분 값</td>
	<td class="gubunH">장애내용</td>
	<td width="20%" class="gubunH" colspan="2">장애처리내역</td>
</tr>

<c:forEach var="i" items="${serviceFailureVec}" >
	<tr>
		<td width="10%"  class="centergray">${i.failureTime}</td>
		<td width="10%"  class="center">${i.recoveryTime}</td>
		<td width="10%"  class="center">${i.failureType == "session" ? "세션단절" : "최소성공율 미만" }
		</td>
		<td width="10%"  class="center">${i.id}</td>
		<td class="center">${i.description}</td>
		<td width="15%"  class="center">${i.resolution}</td>
		<td width="5%"  class="center">
		<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >
			<c:choose>
				<c:when test="${i.checked == 0}">등록</c:when>
				<c:otherwise>수정</c:otherwise>
			</c:choose>
		</span>
		</td>
	</tr>
</c:forEach>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>