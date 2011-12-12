package pms.command.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.service.StatusBean;
import pms.db.DBManager;
import pms.servlet.PmsCommandInterface;
import pms.utils.*;

public class SearchStatusImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		String spID = Util.getRequestParamet(request, "spID");
		String messageType = Util.getRequestParamet(request, "messageType");
		String searchDate = Util.getRequestParamet(request, "searchDate");
		String searchHour = Util.getRequestParamet(request, "searchHour");
		String startMin = Util.getRequestParamet(request, "startMin");
		
		if(spID.isEmpty() || messageType.isEmpty() || searchDate.isEmpty() || searchHour.isEmpty() || startMin.isEmpty()){
			return "/views/service/searchstatus.jsp";
		}
		
		String timeformat = String.format("%s%02d%02d", searchDate, Integer.parseInt(searchHour), Integer.parseInt(startMin));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmm");
		try {
			java.util.Date date = sdf.parse(timeformat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			Calendar currentCal = Calendar.getInstance();
			long difference = currentCal.getTimeInMillis() - cal.getTimeInMillis();
			if(difference > 0){
				StatusBean bean = new StatusBean();
				bean.setSpID(spID);
				bean.setMessageType(messageType);
				bean.setCalendar(cal);
				
				if(!DBManager.excute(bean,"jdbc/pms_dev_oracle")){
					return "/logout.do";
				}
				
				request.setAttribute("serviceStatus", bean);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "/views/service/searchstatus.jsp";
	}
}
