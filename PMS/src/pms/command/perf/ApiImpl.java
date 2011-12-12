package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.perf.ApiBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;

public class ApiImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ApiBean bean = new ApiBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfApi", bean.getApiBeansVec());
		
		return "/views/perf/api.jsp";
	}
}
