package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.perf.ScsBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

public class ScsImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ScsBean bean = new ScsBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfScs", bean.getScsBeansVec());
		
		return "/views/perf/scs.jsp";
	}
}
