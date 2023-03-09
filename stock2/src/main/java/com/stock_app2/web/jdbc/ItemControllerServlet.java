package com.stock_app2.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ItemControllerServlet 
 */
@WebServlet("/ItemControllerServlet")
public class ItemControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ItemDbUtil itemDbUtil;
	
	@Resource(name="jdbc/stock_orgs")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create our student db util... and pass in the conn pool/datasource
		try {
			itemDbUtil = new ItemDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}


	int flag1=0;
	int flag2=0;
	static String table_name1;
	static String wh_locn;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try {
			
			//read the command parameter
			String theCommand=request.getParameter("command");
			//String table_name="";
			
			if (theCommand == null) {
				if(flag1 == 0) {
					flag1++;
					theCommand="LOGIN";
				}
				else theCommand="LIST";
				
			}
			
			//route to the appropriate method
			switch(theCommand) {
			
			case "LOGIN":
				login(request,response);
				//login(request,response);
				break;
				
			case "SIGNUP":
				signup(request,response);
				break;
				
			case "SIGNUP2":
				signup2(request,response);
				break;
			
			case "LIST":
				listItems(request,response);
				break;
			
			
			case "ADD":
				addItem(request,response);
				break;
				
			case "LOAD":
				loadItem(request,response);
				break;
				
			case "UPDATE":
				updateItem(request,response);
				break;
				
			case "DELETE":
				deleteItem(request,response);
				break;
				
			case "INVOICE":
				recordInvoice(request,response);
				break;
				
			case "VIEW_ORDERS":
				viewOrders(request,response);
				break;
				
			case "VIEW_LOSS_DETAILS":
				viewLossDetails(request,response);
				break;
				
			case "RECORD_LOSS":
				recordLoss(request,response);
				break;
			case "LOGOUT":
				logout(request,response);
				break;
			default:
			//list the items in mvc fashion
				listItems(request,response);

			}
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		login(request,response);
		flag1=0;
		flag2=0;
		wh_locn=null;
		table_name1=null;
	}

	private void recordLoss(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		String date_of_loss=request.getParameter("date");
		String wh_name=request.getParameter("wh_name");
		String item_name=request.getParameter("itemName");
		String quantity_temp=request.getParameter("quantity");
		String reason=request.getParameter("reason");
		int quantity=Integer.parseInt(quantity_temp);
		
		LossDetails theLossDetail=new LossDetails(date_of_loss,wh_name,item_name,quantity,reason);
		itemDbUtil.recordLoss(table_name1,wh_locn,theLossDetail);
		
		viewLossDetails(request,response);
		//listItems(request,response);
		
	}

	private void viewLossDetails(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		
		List<LossDetails> lossDetails=new ArrayList<>();
		lossDetails=itemDbUtil.viewLossDetails();
		
		request.setAttribute("LOSS_RECORDS", lossDetails);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/loss-details.jsp");
		dispatcher.forward(request, response);
		
	}

	private void signup(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		String comp_name=request.getParameter("companyName");
		String username=request.getParameter("userName");
		String password=request.getParameter("pwd");
		String no_of_wh=request.getParameter("noOfWarehouses");
		int no_of_warehouses=Integer.parseInt(no_of_wh);
		
		Company theCompany=new Company(comp_name,username,password);
		
		itemDbUtil.signup(theCompany,no_of_warehouses);
		request.setAttribute("noOfWarehouses",no_of_warehouses);
		request.setAttribute("username",username);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/signup-pt2-form.jsp");
		dispatcher.forward(request,response);
		
		//signup_part2(no_of_warehouses,request,response);
		
	}

	private void signup2(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		List<String> locations=new ArrayList<>();
		String no_of_wh=request.getParameter("no_of_warehouses");
		String username=request.getParameter("username");
		int no_of_warehouses=Integer.parseInt(no_of_wh);
		for(int i=1;i<=no_of_warehouses;i++) {
			String locn=request.getParameter("location"+i);
			locations.add(locn);
		}
		itemDbUtil.signup2(locations,username,no_of_warehouses);
		request.setAttribute("username", username);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/signup-pt3.jsp");
		dispatcher.forward(request,response);
		
		
	}

	private void viewOrders(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		String date=request.getParameter("date");
		List<Invoice> invoices=itemDbUtil.viewOrders(date);
		 
		request.setAttribute("ORDERS_LIST",invoices);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/display-invoice-records.jsp");
		dispatcher.forward(request, response);
	} 



	private void recordInvoice(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		String date=request.getParameter("date");
	String itemName=request.getParameter("itemName");
		String quantity_temp=request.getParameter("quantity");
		int quantity=Integer.parseInt(quantity_temp);
		
		Invoice theInvoice=new Invoice(date,itemName,quantity);
		itemDbUtil.recordInvoice(table_name1,wh_locn,theInvoice);
		
		listItems(request,response);
	}



	public String login(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		String username= request.getParameter("username");
		String pwd= request.getParameter("pwd");
		table_name1=username;
		int flag=itemDbUtil.login_check(username,pwd);
		if(flag != 0) {
			choose_listItems(request,response);
			//listItems(request,response);
		}
		String sample="Hello!";
		request.setAttribute("SAMPLE",sample);
		//CompanyLogin theCompLogin=new CompanyLogin(username,pwd);
		//int flag=itemDbUtil.login_check(theCompLogin);
		//String table_name1="india_mart_items";
		/*if(flag != 0) {
			listItems(request,response);
		}*/
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher=request.getRequestDispatcher("/login-form1.jsp");
		dispatcher.forward(request, response);
		return table_name1;
		
	}



	private void choose_listItems(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		List<String> locations=new ArrayList<>();
		locations=itemDbUtil.choose_listItems(table_name1);
		
		request.setAttribute("LOCATIONS",locations);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/choose-locnOfWh.jsp");
		dispatcher.forward(request,response);
		
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		//read item id from form data
		String theItemId = request.getParameter("itemId");
		
		//delete item from the database
		itemDbUtil.deleteitem(table_name1,wh_locn,theItemId);
		
		//send them back to list items page
		listItems(request,response);
		
	}



	private void updateItem(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		//read item info from the form data
		int id=Integer.parseInt(request.getParameter("itemId"));
		String itemName=request.getParameter("itemName");
		String brand=request.getParameter("brand");
		int count= Integer.parseInt(request.getParameter("count"));
		
		//create new item object
		Item theItem=new Item(id,itemName,brand,count);
		
		//perform update on the database
		itemDbUtil.updateItem(table_name1,wh_locn,theItem);
		
		//send then back to the list students page
		listItems(request,response);
		
	}



	private void loadItem(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		// TODO Auto-generated method stub
		//read item id from form data
		String theItemId = request.getParameter("itemId");
		
		//get student from database (db util)
		Item theItem= itemDbUtil.getItem(table_name1,wh_locn,theItemId);
		
		//place student in the request attribute
		request.setAttribute("THE_ITEM", theItem);
		
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher=request.getRequestDispatcher("/update-item-form1.jsp");
		dispatcher.forward(request, response);
		
	}



	private void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		//read item info from form data
		//String itemId_temp=request.getParameter("itemId");
		String itemName=request.getParameter("itemName");
		String brand=request.getParameter("brand");
		String count_temp=request.getParameter("count");
		//int itemId=Integer.parseInt(itemId_temp);
		int count=Integer.parseInt(count_temp);
		
		
		//create a new item object
		Item theItem = new Item(itemName,brand,count);
		
		//add the item to the database
		ItemDbUtil.addItem(table_name1,wh_locn,theItem);
		
		//send back to the main page
		listItems(request,response);
	}



	private void listItems(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		// TODO Auto-generated method stub
		//get items from db util
		if(flag2 == 0) {
			flag2++;
			wh_locn=request.getParameter("location");
		}
		List<Item> items=itemDbUtil.getItems(table_name1,wh_locn);
		
		//add students to the request
		request.setAttribute("ITEM_LIST",items);
		
		//send to JSP page
		RequestDispatcher dispatcher =request.getRequestDispatcher("/list-items1.jsp");
		dispatcher.forward(request, response);
		
	}

}
