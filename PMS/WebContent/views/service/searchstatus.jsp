<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>
<%
	String spID = Util.getRequestParamet(request, "spID");
	String messageType = Util.getRequestParamet(request, "messageType");
	String searchDate = Util.getRequestParamet(request, "searchDate");
	String searchHour = Util.getRequestParamet(request, "searchHour");
	String startMin = Util.getRequestParamet(request, "startMin");
	String endMin = Util.getRequestParamet(request, "endMin");
	
	if(messageType.isEmpty()){
		messageType = "SMS";
	}
	
	if(searchDate.isEmpty()){
		SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd");
		searchDate = simpledf.format(today);
	}
	
	if(searchHour.isEmpty()){
		SimpleDateFormat simpledf = new SimpleDateFormat("HH");
		searchHour = simpledf.format(today);
	}
	
	if(startMin.isEmpty()){
		SimpleDateFormat simpledf = new SimpleDateFormat("mm");
		int min = Integer.parseInt(simpledf.format(today));
		min = (min / 5) == 0 ? 0 : (min/5) * 5;
		startMin = String.valueOf(min);
		endMin = String.valueOf(min+5);
	}
	
	endMin = String.valueOf(Integer.parseInt(startMin) + 5);
%>

<script type="text/javascript">
<!--
function onSelectChange(){
	var startSelect = document.getElementById("startSelect");
	var endSelect = document.getElementById("endSelect");
	var selectedIndex = startSelect.selectedIndex;
	endSelect.selectedIndex = selectedIndex+1; 
}

	
-->
</script>

<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<form name="searchFrom" method="post"  action="/service/searchstatus.do" >
				<tr>
					<td style="font-weight:bold; line-height:18px" >
						고객ID&nbsp;
						<input type="text" name="spID" style="margin-left:-3px" value=<%=spID %> >&nbsp;&nbsp;&nbsp;&nbsp;메시지별&nbsp;
						<input type="radio" name="messageType" style="margin-left:-3px"  value="SMS"  <%=messageType.equals("SMS") ? "checked" : "" %> >SMS&nbsp;
						<input type="radio" name="messageType" value="LMS"  style="margin-left:-3px" <%=messageType.equals("LMS") ? "checked" : "" %> >LMS&nbsp;
						<input type="radio" name="messageType" value="MMS"  style="margin-left:-3px" <%=messageType.equals("MMS") ? "checked" : "" %> >MMS&nbsp;
						<input type="radio" name="messageType" value="FMS"  style="margin-left:-3px" <%=messageType.equals("FMS") ? "checked" : "" %> >FMS&nbsp;
						<input type="radio" name="messageType" value="VMS"  style="margin-left:-3px" <%=messageType.equals("VMS") ? "checked" : "" %> >VMS&nbsp;&nbsp;&nbsp;&nbsp;검색기간&nbsp;
						<input type="text" size="10" name='searchDate' class="calendar"  value=<%= searchDate%>>&nbsp;
						<select name="searchHour">
						<%
							for(int i=0; i < 24; i++){
								String option = String.format("<option value=\'%d\' %s>%02d시</option>", i, Integer.parseInt(searchHour) == i ? "selected" : "", i);
								out.println(option);
							}
						%>
						</select>
						<select id="startSelect" name="startMin" onchange="onSelectChange();">
						<%
							for(int i=0; i < 60; i += 5){
								String option = String.format("<option value=\'%d\' %s>%02d분</option>", i, Integer.parseInt(startMin) == i ? "selected" : "", i);
								out.println(option);
							}
						%>
						</select>
						<select id="endSelect" name="endMin" disabled>
						<%
							for(int i=0; i <= 60; i += 5){
								String option = String.format("<option value=\'%d\' %s>%02d분</option>", i, Integer.parseInt(endMin) == i ? "selected" : "", i);
								out.println(option);
							}
						%>
						</select>
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
	<td class="gubunH" rowspan="2">ID</td>
	<td width="8%" class="gubunH" rowspan="2">메시지 유형</td>
	<td width="8%" class="gubunH" rowspan="2">전체<br>시도 건수</td>
	<td width="8%" class="gubunH" rowspan="2">발송처리<br>완료 건수</td>
	<td width="8%" class="gubunH" rowspan="2">대기건수</td>
	<td width="8%" class="gubunH" rowspan="2">발송<br>성공율(%)</td>
	<td width="10%" class="gubunH" colspan="5">Status</td>
</tr>
<tr>
	<td width="10%" class="gubunH" >0<br>이통사 전송 대기</td>
	<td width="10%" class="gubunH" >1<br>이통사 전송 완료</td>
	<td width="10%" class="gubunH" >2<br>리포트 수신</td>
	<td width="10%" class="gubunH" >3<br>고객 리포트 전송</td>
	<td width="10%" class="gubunH" >4<br>고객 리포트 응답</td>
</tr>

<c:if test="${serviceStatus != null}">
	<c:set var="i" value="${serviceStatus }" />
	<tr>
		<td class="centergray">${i.spID}</td>
		<td width="8%" class="center">${i.messageType}</td>
		<td width="8%" class="center">${i.totalMsg}</td>
		<td width="8%" class="center">${i.completeMsg}</td>
		<td width="8%" class="center">${i.standByMsg}</td>
		<td width="8%" class="center">${i.successRate}</td>
		<td width="10%" class="center">${i.statusZero}</td>
		<td width="10%" class="center">${i.statusOne}</td>
		<td width="10%" class="center">${i.statusTwo}</td>
		<td width="10%" class="center">${i.statusThree}</td>
		<td width="10%" class="center">${i.statusFour}</td>
	</tr>
</c:if>
</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>