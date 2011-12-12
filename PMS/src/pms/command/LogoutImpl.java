package pms.command;

import java.io.IOException;

import javax.servlet.http.*;

import pms.servlet.PmsCommandInterface;

public class LogoutImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++){
			cookies[i].setMaxAge(0);
		}
		request.getSession().invalidate();
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
