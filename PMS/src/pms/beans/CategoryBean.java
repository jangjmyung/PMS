package pms.beans;
import java.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import pms.db.DBStatement;

public class CategoryBean implements DBStatement {
	private Vector<CategoryBean> categoriesVec = new Vector<CategoryBean>();
	private int seq = 0;
	private int level = 0;
	private String name = "";
	private String url = "";
	private int parentSeq = 0;
	private int type = 1;
	private int show = 1;

	@Override
	public void excute(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		if(!categoriesVec.isEmpty()){
			categoriesVec.clear();
		}
		
		while(rs.next()){
			CategoryBean bean = new CategoryBean();
			
			bean.setSeq(rs.getInt("SEQNO"));
			bean.setLevel(rs.getInt("LVL"));
			bean.setName(rs.getString("NAME"));
			bean.setUrl(rs.getString("URL"));
			bean.setParentSeq(rs.getInt("PARENT_SEQNO"));
			bean.setType(rs.getInt("TYPE"));
			bean.setShow(rs.getInt("SHOW"));
			
			categoriesVec.add(bean);
		}	
	}

	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "select * from p_category order by TYPE, LVL, SEQNO";
	}
	
	public Vector<CategoryBean> getCategoryBeans(){
		return categoriesVec;
	}

	public Vector<CategoryBean> getCategoriesVec() {
		return categoriesVec;
	}

	public void setCategoriesVec(Vector<CategoryBean> categoriesVec) {
		this.categoriesVec = categoriesVec;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}
}
