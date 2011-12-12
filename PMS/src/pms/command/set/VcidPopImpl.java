package pms.command.set;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.servlet.PmsCommandInterface;
import pms.utils.*;
import pms.beans.set.*;
import pms.db.DBManager;

public class VcidPopImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		String vcid = Util.getRequestParamet(request, "vcid");
		String vcidName = Util.getRequestParamet(request, "vcidName");
		if(!vcid.equals("")){
			CidBean rootCidBean = new CidBean();
			rootCidBean.setVcid(Integer.parseInt(vcid));
			rootCidBean.setVcidName(vcidName);
			
			if(!DBManager.excute(rootCidBean, "jdbc/pms_dev_mysql")){
				return "/logout.do";
			}
			
			Catalog catalog = Catalog.load("http://119.205.196.124/catalogs/"+ rootCidBean.getVcidName());
			if(catalog == null) return "/logout.do";
			
			Iterator<CidBean> cidIt = rootCidBean.getCidBeansVec().iterator();
			while(cidIt.hasNext()){
				CidBean cidBean = cidIt.next();
				QueueInformation qi = catalog.getQueueInformation(cidBean.getCidName(), "10");
				if(qi != null) cidBean.setPriority1(Long.parseLong(qi.queueDelayTime));
				
				qi = catalog.getQueueInformation(cidBean.getCidName(), "100");
				if(qi != null) cidBean.setPriority2(Long.parseLong(qi.queueDelayTime));
				
				qi = catalog.getQueueInformation(cidBean.getCidName(), "1000");
				if(qi != null) cidBean.setPriority3(Long.parseLong(qi.queueDelayTime));
				
				qi = catalog.getQueueInformation(cidBean.getCidName(), "2000");
				if(qi != null) cidBean.setPriority4(Long.parseLong(qi.queueDelayTime));
			}
			
			request.setAttribute("setCidVec", rootCidBean.getCidBeansVec());
		}

		return "/views/set/vcidpop.jsp";
	}

}
