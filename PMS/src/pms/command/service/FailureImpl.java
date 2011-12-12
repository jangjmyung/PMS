package pms.command.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.servlet.PmsCommandInterface;
import pms.utils.*;
import pms.beans.service.*;
import pms.db.DBManager;

public class FailureImpl implements PmsCommandInterface {

	@Override
	public String handing(HttpServletRequest request, HttpServletResponse response) {
		
		String searchFailure = Util.getRequestParamet(request, "searchFailure");
		String spID = Util.getRequestParamet(request, "spID");
		String startDate = Util.getRequestParamet(request, "startDate");
		String endDate = Util.getRequestParamet(request, "endDate");
		
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		
		if(searchFailure.equals("")){
			searchFailure = "all";			
		}
		
		try{
			if(!startDate.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(startDate);
				startCal.setTime(date);
			}
			
			startCal.set(Calendar.HOUR_OF_DAY, 0);
			startCal.set(Calendar.MINUTE, 0);
			startCal.set(Calendar.SECOND, 0);

			
			if(!endDate.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(endDate);
				endCal.setTime(date);
			}
			
			endCal.set(Calendar.HOUR_OF_DAY, 23);
			endCal.set(Calendar.MINUTE, 59);
			endCal.set(Calendar.SECOND, 0);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long difference = endCal.getTimeInMillis() - startCal.getTimeInMillis();
		if(difference > 0){
			FailureBean bean = new FailureBean();
			bean.setClientID(spID);
			bean.setFailureType(searchFailure);
			bean.setStartCal(startCal);
			bean.setEndCal(endCal);
			
			if(!DBManager.excute(bean)){
				return "/logout.do";
			}
			
			request.setAttribute("serviceFailureVec", bean.getFailureBeansVec());
		}
		
		return "/views/service/failure.jsp";
	}
}
