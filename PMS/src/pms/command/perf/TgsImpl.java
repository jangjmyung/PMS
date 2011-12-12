package pms.command.perf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.servlet.PmsCommandInterface;

public class TgsImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/views/perf/tgs.jsp";
	}
}
