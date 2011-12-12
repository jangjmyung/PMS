<%@ page import="java.sql.*, java.util.*, pms.utils.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="/include/css/style.css">
	<title>VCID 상세보기</title>
</head>
<body>


<table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="#c0c0c0">
<tr>
	<td width="10%"  class="gubunH" >CID 명</td>
	<td width="10%"  class="gubunH" >CID 섹션</td>
	<td width="10%"  class="gubunH" >서버 IP</td>
	<td width="10%"  class="gubunH" >서버 PORT</td>
	<td width="10%"  class="gubunH" >임계값(건/초)</td>
	<td width="10%"  class="gubunH" >상태</td>
	<td width="10%"  class="gubunH" >실시간 우선대기(ms)</td>
	<td width="10%"  class="gubunH" >실시간 일반대기(ms)</td>
	<td width="10%"  class="gubunH" >광고 우선대기(ms)</td>
	<td width="10%"  class="gubunH" >광고 일반대기(ms)</td>
</tr>
<c:forEach var="i" items="${setCidVec}">
<tr>
	<td width="10%"  class="center" >${i.cidName}</td>
	<td width="10%"  class="center" >${i.cidSection}</td>
	<td width="10%"  class="center" >${i.mrsServer}</td>
	<td width="10%"  class="center" >${i.mrsPort}</td>
	<td width="10%"  class="center" >${i.outTrafficMax}</td>
	<td width="10%"  class="center" >
	<c:choose>
		<c:when test="${i.priority1 != 4294967295}">가용</c:when>
		<c:otherwise>비가용</c:otherwise>
	</c:choose>
	</td>
	<td width="10%"  class="center" >${i.priority1}</td>
	<td width="10%"  class="center" >${i.priority2}</td>
	<td width="10%"  class="center" >${i.priority3}</td>
	<td width="10%"  class="center" >${i.priority4}</td>
</tr>
</c:forEach>
</table>

<br>
<div align="center" style="line-height:19px;">
<span class="submitbtn6" onMouseOver="this.className='submitbtn6_on'" onMouseOut="this.className='submitbtn6'" onclick="window.close();" >확인</span>
</div>
</body>
</html>

