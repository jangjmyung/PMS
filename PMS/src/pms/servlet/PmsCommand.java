package pms.servlet;

import java.io.IOException;
import java.util.*;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.beans.*;
import pms.db.*;

/**
 * Servlet implementation class PmsCommand
 */
public class PmsCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	private Vector<CategoryBean> categoriesVec = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PmsCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String props = config.getInitParameter("propertyConfig");
		props = config.getServletContext().getRealPath(props);
		
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		
		try{
			fileInputStream = new FileInputStream(props);
			properties.load(fileInputStream);
			
			
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(fileInputStream != null){ try{	fileInputStream.close(); }catch(IOException e){}}
		}
		
		Iterator<?> it = properties.keySet().iterator();
		while(it.hasNext()){
			String command = (String)it.next();
			String className = properties.getProperty(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(InstantiationException e){
				e.printStackTrace();
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}
		}
		
		CategoryBean category = new CategoryBean();
		if(DBManager.excute(category)){
			categoriesVec = category.getCategoriesVec();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handing(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handing(request, response);
	}
	
	protected void handing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		PmsCommandInterface pmsIF = null;
		
		try{
			request.setCharacterEncoding("UTF-8");
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0){
				command.substring(request.getContextPath().length());
			}
			
			if(command != null && !command.isEmpty()){
				request.setAttribute("command", command);
			}
			
			if(categoriesVec != null){
				request.setAttribute("categories", categoriesVec);
			}
			
			pmsIF = (PmsCommandInterface)commandMap.get(command);
			
			if(pmsIF != null){
				view = pmsIF.handing(request, response);
			}
			
		}catch(Throwable e){
			throw new ServletException(e);
		}
		
		if(view == null || view.isEmpty()) return;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}














