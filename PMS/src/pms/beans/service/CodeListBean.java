package pms.beans.service;
import java.util.*;
import java.util.Map.Entry;

public class CodeListBean {
	private int totalSms = 0;
	private int totalMms = 0;
	private int totalVms = 0;
	private int totalFms = 0;
	
	private int errorCode = 0;
	private int sms = 0;
	private int vms = 0;
	private int fms = 0;
	private int mms = 0;
	
	private Map<Integer, CodeListBean> codeListMap = new HashMap<Integer, CodeListBean>();
	
	private void sum(CodeListBean codeListBean, CodeBean bean){
		switch(bean.getMessageType()){
		case 1 :
			codeListBean.setSms(codeListBean.getSms() + bean.getCount());
			totalSms += bean.getCount();
			break;
		case 2:
			codeListBean.setVms(codeListBean.getVms() + bean.getCount());
			totalVms += bean.getCount();
			break;
		case 3:
			codeListBean.setFms(codeListBean.getFms() + bean.getCount());
			totalFms += bean.getCount();
			break;
		case 4:
			codeListBean.setMms(codeListBean.getMms() + bean.getCount());
			totalMms += bean.getCount();
			break;
		default:
			break;
		}
	}
	
	public void add(Vector<CodeBean> codeBeanVec){
		Iterator<CodeBean> it = codeBeanVec.iterator();
		while(it.hasNext()){
			CodeBean bean = it.next();
			if(!codeListMap.containsKey(bean.getErrorCode())){
				CodeListBean codeListBean = new CodeListBean();
				codeListMap.put(codeListBean.getErrorCode(), codeListBean);
				
				codeListBean.setErrorCode(bean.getErrorCode());
				sum(codeListBean, bean);
			}else{
				CodeListBean codeListBean = codeListMap.get(bean.getErrorCode());
				sum(codeListBean, bean);
			}
		}
	}
	
	public Vector<CodeListBean> get(){
		Vector<CodeListBean> vec = new Vector<CodeListBean>();
		
		Set<Entry<Integer, CodeListBean>> codeSet =  codeListMap.entrySet();
		Iterator<Entry<Integer, CodeListBean>> it = codeSet.iterator();
		while(it.hasNext()){
			Entry<Integer, CodeListBean> entry = it.next();
			vec.add(entry.getValue());
		}
		
		Collections.sort(vec, new Comp());
		
		return vec;
	}

	public int getTotalSms() {
		return totalSms;
	}

	public void setTotalSms(int totalSms) {
		this.totalSms = totalSms;
	}

	public int getTotalMms() {
		return totalMms;
	}

	public void setTotalMms(int totalMms) {
		this.totalMms = totalMms;
	}

	public int getTotalVms() {
		return totalVms;
	}

	public void setTotalVms(int totalVms) {
		this.totalVms = totalVms;
	}

	public int getTotalFms() {
		return totalFms;
	}

	public void setTotalFms(int totalFms) {
		this.totalFms = totalFms;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getVms() {
		return vms;
	}

	public void setVms(int vms) {
		this.vms = vms;
	}

	public int getFms() {
		return fms;
	}

	public void setFms(int fms) {
		this.fms = fms;
	}

	public int getMms() {
		return mms;
	}

	public void setMms(int mms) {
		this.mms = mms;
	}
}

class Comp implements Comparator<CodeListBean>{
	public int compare(CodeListBean bean1, CodeListBean bean2){
		return bean1.getErrorCode() > bean2.getErrorCode() ? -1 : (bean1.getErrorCode() == bean2.getErrorCode() ? 0 : 1);
	}
}





