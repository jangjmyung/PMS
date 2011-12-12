package pms.command.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.service.*;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;
import pms.utils.*;

public class SearchCodeImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		
		String spID = Util.getRequestParamet(request, "spID");
		String searchDate = Util.getRequestParamet(request, "searchDate");
		String searchHour = Util.getRequestParamet(request, "searchHour");
		String startMin = Util.getRequestParamet(request, "startMin");
		String endMin = Util.getRequestParamet(request, "endMin");
		if(spID.isEmpty() || searchDate.isEmpty() || searchHour.isEmpty() || startMin.isEmpty()){
			return "/views/service/searchcode.jsp";
		}
		
//		String timeformat = searchDate + searchHour + startMin;
		String timeformat = String.format("%s%02d%02d", searchDate, Integer.parseInt(searchHour), Integer.parseInt(startMin));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmm");
		try {
			java.util.Date date = sdf.parse(timeformat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Calendar currentCal = Calendar.getInstance();
			long difference = currentCal.getTimeInMillis() - cal.getTimeInMillis();
			if(difference > 0){
				CodeBean smsCodeBean = new CodeBean();
				smsCodeBean.setMessageType(1);
				smsCodeBean.setSpID(spID);
				smsCodeBean.setCalendar(cal);

				if(!DBManager.excute(smsCodeBean,"jdbc/pms_dev_oracle")){
					return "/logout.do";
				}
				
				CodeBean mmsCodeBean = new CodeBean();
				mmsCodeBean.setMessageType(4);
				mmsCodeBean.setSpID(spID);
				mmsCodeBean.setCalendar(cal);

				if(!DBManager.excute(mmsCodeBean,"jdbc/pms_dev_oracle")){
					return "/logout.do";
				}
				
				CodeBean vmsCodeBean = new CodeBean();
				vmsCodeBean.setMessageType(2);
				vmsCodeBean.setSpID(spID);
				vmsCodeBean.setCalendar(cal);

				if(!DBManager.excute(vmsCodeBean,"jdbc/pms_dev_oracle")){
					return "/logout.do";
				}
				
				CodeBean fmsCodeBean = new CodeBean();
				fmsCodeBean.setMessageType(3);
				fmsCodeBean.setSpID(spID);
				fmsCodeBean.setCalendar(cal);

				if(!DBManager.excute(fmsCodeBean,"jdbc/pms_dev_oracle")){
					return "/logout.do";
				}
				
				CodeListBean codeListBean = new CodeListBean();
				codeListBean.add(smsCodeBean.getCodeBeanVec());
				codeListBean.add(vmsCodeBean.getCodeBeanVec());
				codeListBean.add(fmsCodeBean.getCodeBeanVec());
				codeListBean.add(mmsCodeBean.getCodeBeanVec());
				
				request.setAttribute("serviceCode", codeListBean);
				request.setAttribute("serviceCodeVec", codeListBean.get());
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/views/service/searchcode.jsp";
	}
}
