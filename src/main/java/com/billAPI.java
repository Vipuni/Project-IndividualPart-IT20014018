package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bill;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Servlet implementation class feedbackAPI
 */
@WebServlet("/billAPI")
public class billAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	bill billObj = new bill();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public billAPI() {
        super();
    	
        // TODO Auto-generated constructor stub
    }
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try
		 {
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 } 

		return map;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @param billObj 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 String output = billObj.insertBill(request.getParameter("ele_Acc_num"),
			 request.getParameter("cus_name"),
			request.getParameter("address"),
			request.getParameter("month"),
			request.getParameter("meter_units"),
			request.getParameter("unit_price"),
			request.getParameter("total_amount"));

			response.getWriter().write(output);
			}

		
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 Map paras = getParasMap(request);
			 String output = billObj.updateBill(paras.get("bill_id").toString(),
			 paras.get("ele_Acc_num").toString(),
			paras.get("cus_name").toString(),
			paras.get("address").toString(),
			paras.get("month").toString(),
			paras.get("meter_units").toString(),
			paras.get("unit_price").toString(),
			paras.get("total_amount").toString());
			 
			response.getWriter().write(output);
			} 

	

	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		// TODO Auto-generated method stub
		{
			 Map paras = getParasMap(request);
			 String output = billObj.deleteBill(paras.get("bill_id").toString());
			response.getWriter().write(output);
			}

}


