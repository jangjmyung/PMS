package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.perf.FusBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

public class FusImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		FusBean bean = new FusBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfFus", bean.getFusBeansVec());
		
		return "/views/perf/fus.jsp";
	}
}
