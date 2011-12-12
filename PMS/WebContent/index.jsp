<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/include/css/style.css">
<title>성능감시시스템</title>

<script language="javascript">
<!--
function begin(){
	document.loginForm.id.focus();
}

function check(){
	if(!document.loginForm.id.value){
		alert("아이디를 입력하지 않으셨습니다.");
		document.loginForm.id.focus();
		return false;
	}
	
	if(!document.loginForm.pwd.value){
		alert("패스워드를 입력하지 않으셨습니다.");
		document.loginForm.pwd.focus();
		return false;
	}

	
	return true;
}
-->
</script>

</head>
<body onload="begin()" background="/images/bg_login.gif">
	<form name="loginForm" action="/login.do" method="post" onSubmit="return check()">
		<table width="100%" height="70%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="328" height="240" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td height="25"></td>
				</tr>
				<tr>
					<td align="right" valign="bottom" background="/images/img_login.png">
						<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input type="text" name="id" class="input_login" tabindex="1"></td>
							<td width="6" rowspan="3"></td>
							<td width="97" rowspan="3"><a href="#"><input type="image" src="/images/btn_login.gif" width="60" height="56" ></a></td>
						</tr>
						<tr>
							<td height="2"></td>
						</tr>
						<tr>
							<td><input type="password" name="pwd" class="input_login" tabindex="2"></td>
						</tr>
						<tr>
							<td height="38" colspan="3"></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>