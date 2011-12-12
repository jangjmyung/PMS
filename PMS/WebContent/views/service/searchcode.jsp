<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>

<%
	String spID = Util.getRequestParamet(request, "spID");
	String searchDate = Util.getRequestParamet(request, "searchDate");
	String searchHour = Util.getRequestParamet(request, "searchHour");
	String startMin = Util.getRequestParamet(request, "startMin");
	String endMin = Util.getRequestParamet(request, "endMin");

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
		
			<form name="searchFrom" method="post"  action="/service/searchcode.do" >
				<tr>
					<td style="font-weight:bold; line-height:18px" >
						고객ID&nbsp;
						<input type="text" name="spID" style="margin-left:-3px" value=<%=spID %> >&nbsp;&nbsp;&nbsp;&nbsp;검색 기간&nbsp;
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
	<td width="16%" class="gubunH" rowspan="3">에러코드</td>
	<td class="gubunH" colspan="5">Msg Type(건수)</td> 
</tr>
<tr>
	<td width="16%" class="gubunH" >SMS</td> 
	<td width="16%" class="gubunH" >MMS</td>
	<td width="16%" class="gubunH" >FMS</td>
	<td width="16%" class="gubunH" >VMS</td>
</tr>
<tr>
<c:choose>
	<c:when test="${serviceCode != null}" >
		<c:set var="i" value="${serviceCode}" />
		<td width="16%" class="gubunH" >${i.totalSms}건</td> 
		<td width="16%" class="gubunH" >${i.totalMms}건</td>
		<td width="16%" class="gubunH" >${i.totalFms}건</td>
		<td width="16%" class="gubunH" >${i.totalVms}건</td>
	</c:when>
	<c:otherwise>
		<td width="16%" class="gubunH" >&nbsp;</td> 
		<td width="16%" class="gubunH" >&nbsp;</td>
		<td width="16%" class="gubunH" >&nbsp;</td>
		<td width="16%" class="gubunH" >&nbsp;</td>
	</c:otherwise>
</c:choose>
</tr>

<c:forEach var="j" items="${serviceCodeVec}">
	<tr>
		<td width="16%" class="centergray" >${j.errorCode}</td>
		<td width="16%" class="center" >${j.sms}</td>
		<td width="16%" class="center" >${j.mms}</td>
		<td width="16%" class="center" >${j.fms}</td>
		<td width="16%" class="center" >${j.vms}</td>
	</tr>
</c:forEach>


</table>
<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>