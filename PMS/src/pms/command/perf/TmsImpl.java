package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.perf.TmsBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

public class TmsImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		TmsBean bean = new TmsBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfTms", bean.getTmsBeansVec());
		
		return "/views/perf/tms.jsp";
	}
}
