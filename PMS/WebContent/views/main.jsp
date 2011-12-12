<%@ page import="java.sql.*, java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file = "/views/common/common.jsp" %>
<%@ include file = "/views/common/commonhead.jsp"%>
<%@ include file = "/views/common/commonmenu.jsp"%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td class="title"><img src="images/blt_ttl.gif" width="12" height="15" style="vertical-align:middle">모니터링</td>
</tr>
<tr>
	<td height="2" bgcolor="#b8b8b8"></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td height="35" align="right"><div id="btnR"><a href="#"><img src="images/btn6.gif"><img src="images/btn6_on.gif" class="over"></a></div></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#dddddd">
<tr>
	<td bgcolor="#ffffff">
		<table width="100%" border="0" align="center" cellpadding="7" cellspacing="0">
		<tr>
			<td width="6" rowspan="5"></td>
			<td height="6"></td>
			<td width="6" rowspan="5"></td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="4" cellpadding="0">
				<tr>
					<td width="25%">
						<table cellpadding="0" cellspacing="0" border="3" width="100%" class="table_grd05">
						<tr>
							<td class="title_cell">고객 발송상태</td>
						</tr>			
						<tr>
							<td height="80" valign="top">airmedia1 07-21 12:20:05<br>성공률 20%</td>
						</tr>
						</table>
					</td>
					<td width="25%">
						<table cellpadding="0" cellspacing="0" border="3" width="100%" class="table_grd05">
						<tr>
							<td class="title_cell">고객 세션상태</td>
						</tr>
						<tr>
							<td height="80" valign="top">ds1243 세션종료 07-21 12:20:05<br>(agent 1.3.1)</td>
						</tr>
						</table>
					</td>
					<td width="25%">
						<table cellpadding="0" cellspacing="0" border="3" width="100%" class="table_grd05">
						<tr>
							<td class="title_cell">CID별 발송상태</td>
						</tr>
						<tr>
							<td height="80" valign="top">SKT SMS1 07-21 12:20:05<br>대기 102,525건</td>
						</tr>
						</table>
					</td>
					<td width="25%">
						<table cellpadding="0" cellspacing="0" border="3" width="100%" class="table_grd05">
						<tr>
							<td class="title_cell">월간발송량 제한</td>
						</tr>
						<tr>
							<td height="80" valign="top">시간 Arimedia1 3,200건</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="2"></td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="4" cellpadding="0">
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">RCS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td width="8%"  class="mntgray1">RCS 1</td>
					<td width="8%"  class="mntgray1">RCS 2</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
					<td width="8%"  class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">MAS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">MAS 1</td>
					<td class="mntgray1">MAS 2</td>
					<td class="mntgray1">MAS 3</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">MRS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">MRS 1</td>
					<td class="mntgray1">MRS 2</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">SCS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">SCS 1</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">TMS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">TMS 1</td>
					<td class="mntgray1">TMS 2</td>
					<td class="mntgray1">TMS 3</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntred1">TGS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">TGS 1</td>
					<td class="mntred2">TGS 2</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">G/W</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">G/W 1</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">FUS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">FUS 1</td>
					<td class="mntgray1">FUS 2</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">FCS-TTS</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">TTS 1</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">FCS-TTF</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">TTF 1</td>
					<td class="mntgray1">TTF 2</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="mntblue">API</td>
							<td class="mntgray3">대기:10건<br>처리:10,251건 (0.2초)</td>
						</tr>
						</table>
					</td>
					<td class="mntgray1">API 1</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
					<td class="mntgray2">&nbsp;</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="6"></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<table cellpadding="0" cellspacing="0" border="3" width="100%" class="table_grd05">
<tr>
	<td class="title_cell">Critical 로그</td>
</tr>
<tr>
	<td height="80" valign="top">장비명 : ERROR 표시</td>
</tr>
</table>

<!-- Content End -->
<%@ include file = "/views/common/commonbottom.jsp"%>