package pms.command.set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.set.ServerSearchBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

import pms.utils.*;

public class ServerImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		String serverType = Util.getRequestParamet(request, "serverType");
		String serverName = Util.getRequestParamet(request, "serverName");
		
		ServerSearchBean bean = new ServerSearchBean();
		
		if(!serverType.isEmpty()){
			bean.setType(serverType);
		}
		
		if(!serverName.isEmpty()){
			bean.setName(serverName);
		}
		
		if(!DBManager.excute(bean)){
			return "/logout.do";
		}

		request.setAttribute("setServerVec", bean.getServerVec());
		return "/views/set/server.jsp";
	}
}
