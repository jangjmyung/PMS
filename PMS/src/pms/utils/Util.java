package pms.utils;
import javax.servlet.http.HttpServletRequest;

public class Util {
	
	static public String getRequestParamet(HttpServletRequest request, String parameter){
		String param = request.getParameter(parameter);
		if(param == null) return "";
		return param;
	}
}
