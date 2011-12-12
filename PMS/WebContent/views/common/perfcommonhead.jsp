<%@ page import="java.sql.*,  java.util.*" contentType="text/html; charset=utf-8"%>
<script language="javascript">
<!--
var tmr;

function refreshFunction(){
	
	if(document.checkFrm.refreshCB.checked == true){
		location.reload();
	}
}

function window.onload(){
	tmr = setInterval("refreshFunction()", 5 * 60 * 1000);
}

-->
</script>

<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td class="title"><img src="/images/blt_ttl.gif" width="12" height="15" style="vertical-align:middle"><%=commandTitle%></td>
	</tr>
	<tr>
		<td height="2" bgcolor="#b8b8b8"></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="subttl">
			<div><%=currentTime %> 성능 정보 (<input name="refreshCB" type="checkbox"  value="autoRefresh" checked="checked">자동갱신 - 5분 주기 )</div>
		</td>
	</tr>
</table>