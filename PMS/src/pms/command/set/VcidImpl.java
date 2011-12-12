package pms.command.set;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pms.servlet.PmsCommandInterface;
import pms.utils.*;
import pms.beans.set.*;
import pms.db.DBManager;

public class VcidImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		String messageType = Util.getRequestParamet(request, "messageType");
		String telcoInfo = Util.getRequestParamet(request, "telcoInfo");
		String vcidName = Util.getRequestParamet(request, "vcidName");
		
		VcidBean vcidBean = new VcidBean();
		if(!messageType.equals("")){
			vcidBean.setMessageType(Integer.parseInt(messageType));
		}
		
		if(!telcoInfo.equals("")){
			vcidBean.setTelcoinfo(Integer.parseInt(telcoInfo));
		}
		
		vcidBean.setVcidName(vcidName);
		
		if(!DBManager.excute(vcidBean, "jdbc/pms_dev_mysql")){
			return "/logout.do";
		}
		
		Iterator<VcidBean> it = vcidBean.getVcidBeansVec().iterator();
		while(it.hasNext()){
			VcidBean bean = it.next();
			
			CidBean rootCidBean = new CidBean();
			rootCidBean.setVcid(bean.getVcid());
			rootCidBean.setVcidName(bean.getVcidName());
			
			if(!DBManager.excute(rootCidBean, "jdbc/pms_dev_mysql")){
				return "/logout.do";
			}
			
			bean.setTotalPerSec(rootCidBean.getSumOutTrafficMax());
			int totalCid = rootCidBean.getCidBeansVec().size();
			int availCid = 0;
			long standBy = 0;
			
			Catalog catalog = Catalog.load("http://119.205.196.124/catalogs/"+ bean.getVcidName());
			if(catalog == null) continue;
			
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
				
				if(cidBean.getPriority1() != Long.parseLong("4294967295") 
					&& cidBean.getPriority2() != Long.parseLong("4294967295")
					&& cidBean.getPriority3() != Long.parseLong("4294967295")
					&& cidBean.getPriority4() != Long.parseLong("4294967295")){
										
					availCid++;
					standBy += cidBean.getPriority1();
					standBy += cidBean.getPriority2();
					standBy += cidBean.getPriority3();
					standBy += cidBean.getPriority4();
				}
			}
			
			bean.setAvailableCid(availCid);
			bean.setNoneCid(totalCid - availCid);
			bean.setStandBy(standBy);
		}
		
		request.setAttribute("setVcidVec", vcidBean.getVcidBeansVec());
		
		return "/views/set/vcid.jsp";
	}
}
