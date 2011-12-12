/**
 * 
 */
package pms.command;

import java.io.IOException;

import javax.servlet.http.*;
import pms.beans.UserBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;
import sun.misc.*;

/**
 * @author jjm
 *
 */
public class LoginImpl implements PmsCommandInterface {

	/* (non-Javadoc)
	 * @see pms.servlet.PmsCommandInterface#handing(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	
		UserBean user = new UserBean(id, pwd);
		
		if(!DBManager.excute(user)){
			return "/";
		}
		
		if(user.getValidation()){
		
			Cookie cookie = new Cookie("id", user.getId());
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("pms", "www.pms.com");
			cookie.setMaxAge(60*60);
			response.addCookie(cookie2);
			
			String name = user.getName();
			
			if(name != null && !name.isEmpty()){
				BASE64Encoder encoder = new BASE64Encoder();
				name = encoder.encode(name.getBytes());
				
				Cookie cookie3 = new Cookie("name", name);
				cookie3.setMaxAge(60*60);
				response.addCookie(cookie3);
			}
			
//			return "/views/perf/mas.jsp";
			try {
				response.sendRedirect("/main.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		
		return "/";
	}
}
