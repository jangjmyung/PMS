<%@ page import="java.sql.*, java.util.*, sun.misc.*, java.io.*" contentType="text/html; charset=utf-8" %>
<%
Cookie[] cookies = request.getCookies();
if(cookies == null || cookies.length < 2){
	response.sendRedirect("/");
	return;
}

String id = "";
String domain = "";
String name="";

for(int i=0; i< cookies.length; i++){
	Cookie cookie = cookies[i];
	if(cookie.getName().compareTo("id") == 0){
		id = cookie.getValue();
	}else if(cookie.getName().compareTo("pms") == 0){
		domain = cookie.getValue();
	}else if(cookie.getName().compareTo("name") == 0){
		name = cookie.getValue();
		
		if(name != null && !name.isEmpty()){
			BASE64Decoder decoder = new BASE64Decoder();
			try{
				name = new String(decoder.decodeBuffer(name));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

if(id == null || id.isEmpty()){
	response.sendRedirect("/");
	return;
}

if(domain == null || domain.compareTo("www.pms.com") != 0){
	response.sendRedirect("/");
	return;
}

java.util.Date today = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String currentTime = sdf.format(today);

%>