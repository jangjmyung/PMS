package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.perf.RcsBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

public class RcsImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		RcsBean bean = new RcsBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfRcs", bean.getRcsBeansVec());
		
		return "/views/perf/rcs.jsp";
	}
}
