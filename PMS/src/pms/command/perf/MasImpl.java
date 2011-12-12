/**
 * 
 */
package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.servlet.PmsCommandInterface;
import pms.beans.perf.MasBean;
import pms.db.DBManager;

/**
 * @author jjm
 *
 */
public class MasImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MasBean bean = new MasBean();
		if(!DBManager.excute(bean))
			return "/logout.do";
		
		request.setAttribute("perfMas", bean.getMasBeans());
		return "/views/perf/mas.jsp";
	}
}
