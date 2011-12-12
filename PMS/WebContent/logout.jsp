<%@ page contentType="text/html; charset=utf-8" %>
<%
	Cookie[] cookies = request.getCookies();
	for(int i=0; i<cookies.length; i++){
		cookies[i].setMaxAge(0);
	}
	session.invalidate();
	response.sendRedirect("/login.jsp");
%>
