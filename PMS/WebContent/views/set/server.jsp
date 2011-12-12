<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>
<%
	String serverType = Util.getRequestParamet(request, "serverType");
	String serverName = Util.getRequestParamet(request, "serverName");

%>

<script language="javascript">
<!--
function onModifyServer(server)
{
	window.open("/set/serverpop.do?seq=" + server, '', 'width=420,height=500,scrollbars=no');
	return true;
}

function onDeleteServer(server)
{
	
	return true;
}

-->
</script>


<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<form name="searchFrom" method="post"  action="/set/server.do" >
				<tr>
					<td width="70" style="font-weight:bold">장비분류</td>
					<td style="font-weight:bold; line-height:18px" valign="center" >
						<select name="serverType">
							<option value=""  <%=serverType.equals("") ? "selected" : "" %> >전체</option>
							<option value="RCS"  <%=serverType.equals("RCS") ? "selected" : "" %> >RCS</option>
							<option value="MAS" 	<%=serverType.equals("MAS") ? "selected" : "" %> >MAS</option>
							<option value="MRS" 	<%=serverType.equals("MRS") ? "selected" : "" %> >MRS</option>
							<option value="SCS" 	<%=serverType.equals("SCS") ? "selected" : "" %> >SCS</option>
							<option value="TMS" 	<%=serverType.equals("TMS") ? "selected" : "" %> >TMS</option>
							<option value="TGS" 	<%=serverType.equals("TGS") ? "selected" : "" %> >TGS</option>
							<option value="G/W" 	<%=serverType.equals("G/W") ? "selected" : "" %> >G/W</option>
							<option value="FUS" 	<%=serverType.equals("FUS") ? "selected" : "" %> >FUS</option>
							<option value="FCS" 	<%=serverType.equals("FCS") ? "selected" : "" %> >FCS</option>
							<option value="API" 	<%=serverType.equals("API") ? "selected" : "" %> >API</option>
						</select>&nbsp;&nbsp;장비명&nbsp;
						<input type="text" name="serverName" style="margin-left:-3px" value=<%=serverName %> >&nbsp;&nbsp;
						<span class="submitbtn3" onMouseOver="this.className='submitbtn3_on'" onMouseOut="this.className='submitbtn3'" onclick="searchFrom.submit();" >조회</span>
					</td>
				</tr>
			</form>
		</table>
	</td>
</tr>
</table>
<br>
<div style="line-height:19px">
<span class="submitbtn6" onMouseOver="this.className='submitbtn6_on'" onMouseOut="this.className='submitbtn6'" onclick="onModifyServer('');"  >추가 등록</span>
</div>
<br>


<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="6%"  class="gubunH" >장비명</td>
	<td width="6%"  class="gubunH" >분류</td>
	<td width="10%"  class="gubunH" >IP Address</td>
	<td width="10%"  class="gubunH" >O/S</td>
	<td width="10%"  class="gubunH" >등록일</td>
	<td width="20%"  class="gubunH" >비고</td>
	<td width="10%"  class="gubunH" >수정 / 삭제</td>
</tr>
<c:forEach var="i" items="${setServerVec}">
<tr>
	<td width="6%"  class="center" >${i.name}</td>
	<td width="6%"  class="center" >${i.type}</td>
	<td width="10%"  class="center" >${i.ip}</td>
	<td width="10%"  class="center" >${i.os}</td>
	<td width="10%"  class="center" >${i.dateText}</td>
	<td width="20%"  class="center" >${i.content}</td>
	<td width="10%"  class="center" >
		<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" onclick="onModifyServer(${i.seqNo})">수정</span>
		<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" onclick="onDeleteServer(${i.seqNo})">삭제</span>
	</td>
</tr>
</c:forEach>


</table>



<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>