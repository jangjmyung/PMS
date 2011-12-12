<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>
<%@ include file = "/views/common/servicecommonhead.jsp" %>
<%
	String messageType = Util.getRequestParamet(request, "messageType");
	String telcoInfo = Util.getRequestParamet(request, "telcoInfo");
	String vcidName = Util.getRequestParamet(request, "vcidName");
	
	if(messageType.equals("")) messageType = "0";
	if(telcoInfo.equals("")) telcoInfo = "0";
%>

<script language="javascript">
<!--
function popCid(vcid, vcidName)
{
	window.open("/set/vcidpop.do?vcid=" + vcid + "&vcidName=" + vcidName, '', 'width=1200,height=500,scrollbars=yes');
	return true;
}

-->
</script>


<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td class="gubunH" style="padding:5 10 8 10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
			<form name="searchFrom" method="post"  action="/set/vcid.do" >
				<tr>
					<td width="70" style="font-weight:bold">구분</td>
					<td style="font-weight:bold; line-height:18px" valign="center" >
						<select name="messageType">
							<option value="0" 	<%=messageType.equals("0") ? "selected" : "" %> >메시지타입</option>
							<option value="1" 	<%=messageType.equals("1") ? "selected" : "" %> >SMS</option>
							<option value="2"  <%=messageType.equals("2") ? "selected" : "" %> >VMS</option>
							<option value="3"  <%=messageType.equals("3") ? "selected" : "" %> >FMS</option>
							<option value="4"  <%=messageType.equals("4") ? "selected" : "" %> >MMS</option>
						</select>&nbsp;
						<select name="telcoInfo">
							<option value="0" <%=telcoInfo.equals("0") ? "selected" : "" %> >통신사</option>
							<option value="1" <%=telcoInfo.equals("1") ? "selected" : "" %> >SKT</option>
							<option value="2"  <%=telcoInfo.equals("2") ? "selected" : "" %> >KT</option>
							<option value="3"  <%=telcoInfo.equals("3") ? "selected" : "" %> >LGT</option>
							<option value="4"  <%=telcoInfo.equals("4") ? "selected" : "" %>  >ISMC</option>
							<option value="5"  <%=telcoInfo.equals("5") ? "selected" : "" %> >DACOM</option>
						</select>&nbsp;&nbsp;VCID명&nbsp;
						<input type="text" name="vcidName" style="margin-left:-3px" value=<%=vcidName %> >&nbsp;&nbsp;
						<span class="submitbtn3" onMouseOver="this.className='submitbtn3_on'" onMouseOut="this.className='submitbtn3'" onclick="searchFrom.submit();" >조회</span>
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
	<td width="6%"  class="gubunH" rowspan="2">NO</td>
	<td width="10%"  class="gubunH" rowspan="2">VCID명</td>
	<td width="10%"  class="gubunH" rowspan="2">메시지 타입</td>
	<td width="10%"  class="gubunH" rowspan="2">메시지 서브 타입</td>
	
	<td class="gubunH" colspan="2">전송 용량</td>
	<td class="gubunH" colspan="3">멤버 CID</td>
	<td width="10%"  class="gubunH" rowspan="2">수정 / 삭제</td>
</tr>
<tr>
	<td width="10%" class="gubunH" >임계값(건/초)</td>
	<td width="10%"  class="gubunH" >대기시간(ms)</td>
	<td width="10%"  class="gubunH" >가용 CID</td>
	<td width="10%"  class="gubunH" >비가용 CID</td>
	<td width="10%"  class="gubunH" >상세 보기</td>
</tr>
<c:forEach var="i" items="${setVcidVec}">
	<tr>
			<td class="centergray">${i.vcid}</td>
			<td width="10%" class="center">${i.vcidName}</td>
			<td width="10%" class="center">${i.messageTypeName}</td>
			<td width="10%" class="center">${i.messageSubType}</td>
			<td width="10%" class="center">${i.totalPerSec}</td>
			<td width="10%" class="center">${i.standBy}</td>
			<td width="10%" class="center">${i.availableCid}</td>
			<td width="10%" class="center">${i.noneCid}</td>
			<td width="10%" class="center"><span class="submitbtn6" onMouseOver="this.className='submitbtn6_on'" onMouseOut="this.className='submitbtn6'" onclick="popCid('${i.vcid}', '${i.vcidName}');" >상세 보기</span></td>
			<td width="10%" class="center">
				<span class="submitbtn" onMouseOver="this.className='submitbtn_on'" onMouseOut="this.className='submitbtn'" >수정</span>
			</td>
		</tr>
</c:forEach>
</table>



<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>