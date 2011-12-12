<%@ page import="java.sql.*, java.util.*, pms.utils.*, pms.beans.set.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	String modify = Util.getRequestParamet(request, "modify");
	if(!modify.equals("")){
		if(modify.equals("true")){
			out.println("<script>alert('수정되었습니다.');window.opener.location.reload();window.close();</script>");
			return;
		}else{
			out.println("<script>alert('수정하지 못했습니다. 필수값을 확인하세요');window.opener.location.reload();window.close();</script>");
			return;
		}
	}
	

	String seq = "", name="", type="", os="", content="", ip="";

	ServerBean bean = (ServerBean)request.getAttribute("setServer");
	if(bean != null){
		seq = String.valueOf(bean.getSeqNo());
		name = bean.getName();
		type = bean.getType();
		os = bean.getOs();
		content = bean.getContent();
		ip = bean.getIp();
	}
%>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="/include/css/style.css">
<title>장비관리</title>
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
	<td height="35" background="/images/bg_popup_top.gif" class="popuptitle"><img src="/images/blt_popup_ttl.gif" width="6" height="11" hspace="5" style="vertical-align:middle">장비관리</td>
</tr>
<tr>
	<td style="padding:10px">
		<form name="updateForm"  method="post">
			<input type="hidden" name="modify" value="true" >
			<input type="hidden" name="seq" value=<%= seq %> >
			<table width="400px" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>장비정보를 추가 등록하거나, 정보를 수정합니다.</td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#c0c0c0">
							<tr>
								<td width="100" class="gubunH">장비분류</td>
								<td class="default">
									<select name="type"  style="width:180px;margin-left:-3px;">
										<option value=""  <%=type.equals("") ? "selected" : "" %> >분류선택</option>
										<option value="RCS"  <%=type.equals("RCS") ? "selected" : "" %> >RCS</option>
										<option value="MAS" 	<%=type.equals("MAS") ? "selected" : "" %> >MAS</option>
										<option value="MRS" 	<%=type.equals("MRS") ? "selected" : "" %> >MRS</option>
										<option value="SCS" 	<%=type.equals("SCS") ? "selected" : "" %> >SCS</option>
										<option value="TMS"  <%=type.equals("TMS") ? "selected" : "" %> >TMS</option>
										<option value="TGS" 	<%=type.equals("TGS") ? "selected" : "" %> >TGS</option>
										<option value="G/W" 	<%=type.equals("G/W") ? "selected" : "" %> >G/W</option>
										<option value="FUS" 	<%=type.equals("FUS") ? "selected" : "" %> >FUS</option>
										<option value="FCS" 	<%=type.equals("FCS") ? "selected" : "" %> >FCS</option>
										<option value="API" 	<%=type.equals("API") ? "selected" : "" %> >API</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="100" class="gubunH">장비명</td>
								<td class="default"><input type="text" name="name" style="margin-left:-3px;width:180px" value=<%=name %>></td>
							</tr>
							<tr>
								<td width="100" class="gubunH">IP Address</td>
								<td class="default"><input type="text" name="ip" style="margin-left:-3px;width:180px" value=<%= ip %>>&nbsp;ex)119.205.196.124</td>
							</tr>
							<tr>
								<td width="100" class="gubunH">O/S</td>
								<td class="default"><input type="text" name="os" style="margin-left:-3px;width:180px" value=<%=os %> ></td>
							</tr>
							<tr height="290">
								<td width="100" class="gubunH">비고</td>
								<td class="default"><textarea style="width:290px;height:280px;margin-left:-3px;margin-right:-3px;" name="content"  ><%=content %></textarea></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr >
					<td height="33" align="center" valign="bottom" >
						<div style="line-height:23px">
							<span class="submitbtn7" onMouseOver="this.className='submitbtn7_on'" onMouseOut="this.className='submitbtn7'" onclick="updateForm.submit();" >
								<%=seq.isEmpty() ? "저장" : "수정" %></span>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</td>
</tr>
</table>
</body>
</html>