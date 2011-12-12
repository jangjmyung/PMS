package pms.command.set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.set.ServerSearchBean;
import pms.beans.set.ServerUpdateBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;
import pms.utils.Util;

public class ServerPopImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		String seq = Util.getRequestParamet(request, "seq");
		String name = Util.getRequestParamet(request, "name");
		String type = Util.getRequestParamet(request, "type");
		String ip = Util.getRequestParamet(request, "ip");
		String os = Util.getRequestParamet(request, "os");
		String content = Util.getRequestParamet(request, "content");
		String modify = Util.getRequestParamet(request, "modify");
		
		if(modify.isEmpty()){
			if(!seq.isEmpty()){
				ServerSearchBean bean =  new ServerSearchBean();
				bean.setSeqNo(Integer.parseInt(seq));
				
				if(!DBManager.excute(bean)){
					return "";
				}
				
				if(!bean.getServerVec().isEmpty())
					request.setAttribute("setServer", bean.getServerVec().get(0));
			}
		}else{
			
			ServerUpdateBean bean = new ServerUpdateBean();
			bean.setName(name);
			bean.setType(type);
			bean.setIp(ip);
			bean.setOs(os);
			bean.setContent(content);
			
			if(!seq.isEmpty()){
				bean.setSeqNo(Integer.parseInt(seq));
			}
			
			if(!DBManager.excute(bean)){
				modify = "false";
			}
		}

		return "/views/set/serverpop.jsp";
	}
}
