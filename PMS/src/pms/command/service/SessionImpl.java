package pms.command.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.servlet.PmsCommandInterface;
import pms.utils.*;
import pms.beans.service.*;
import pms.db.DBManager;

public class SessionImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		
		String searchType = Util.getRequestParamet(request, "searchType");
		String spID = Util.getRequestParamet(request, "spID");
		String startDate = Util.getRequestParamet(request, "startDate");
		String endDate = Util.getRequestParamet(request, "endDate");
		
		if(searchType.compareTo("real") == 0 || searchType.isEmpty() ){
			
			if(spID.isEmpty()){
				SessionListBean sessionList = new SessionListBean();
				if(!DBManager.excute(sessionList,"jdbc/pms_dev_mysql")){
					return "/logout.do";
				}
				request.setAttribute("serviceSession", sessionList.getSessionVec());
			}else{
				SessionListWithSpidBean sessionList = new SessionListWithSpidBean();
				sessionList.setSpID(spID);
				if(!DBManager.excute(sessionList,"jdbc/pms_dev_mysql")){
					return "/logout.do";
				}
				request.setAttribute("serviceSession", sessionList.getSessionVec());
			}
		}else{
			
		}
	
		return "/views/service/session.jsp";
	}
}
