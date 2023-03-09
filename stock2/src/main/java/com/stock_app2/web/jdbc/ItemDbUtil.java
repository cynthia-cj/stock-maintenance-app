package com.stock_app2.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemDbUtil {
	private static DataSource dataSource;
	
	//contructor
	public ItemDbUtil(DataSource theDataSource) {
		dataSource= theDataSource;
	}
	//String temp="quick_store_items";
	
	public List<Item> getItems(String table_name,String location) throws Exception {
		List<Item> items= new ArrayList<>();
		//ArrayList<ArrayList<Item>> allItems=new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		//PreparedStatement myStmt2=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		//ResultSet myRs2=null;
		ResultSet myRs=null;
		
		try {
			//get a connection
			myConn=dataSource.getConnection();
		
			//create sql statement
			//String temp="india_mart_items";
			//int id=2;
			/*String sql2="SELECT * FROM warehouses_count WHERE org_username= ?";
			myStmt2=myConn.prepareStatement(sql2);
			myStmt2.setString(1, table_name);
			myRs2=myStmt2.executeQuery();
			int no_of_warehouses=myRs2.getInt("no_of_warehouses");
			*/
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, location);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");
			//for(int i=1;i<=no_of_warehouses;i++) {
			String sql="SELECT * FROM "+table_name+"_"+wh_id+"_items";
			myStmt=myConn.createStatement();
			//myStmt.setString(1,"temp");
			
			//execute query
			myRs=myStmt.executeQuery(sql);
			
			//process result set
			while(myRs.next()) {
				//retrieve data from the result set
				int itemId=myRs.getInt("item_id");
				String itemName=myRs.getString("item_name");
				String brand=myRs.getString("brand");
				int count=myRs.getInt("count1");
				
				//create new item object
				Item tempItem=new Item(itemId,itemName,brand,count);
				
				//add it to the items list
				items.add(tempItem);
			}
			//allItems.add(items);
			//}
			
			return items;
		
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public static void addItem(String table_name,String wh_locn,Item theItem) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection myConn=null;
		PreparedStatement myStmt1=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		
		try {
			
			//get db connection
			try {
				myConn= dataSource.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, wh_locn);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");
			
			//create sql for insert
			String sql = "insert INTO "+table_name+"_"+wh_id+"_items"
					+ "(item_name, brand, count1) "
					+ "values(?, ?, ?)";
			
			myStmt1=myConn.prepareStatement(sql);
			
			//myStmt1.setString(1,table_name);
			myStmt1.setString(1, theItem.getItem_name());
			myStmt1.setString(2, theItem.getBrand());
			myStmt1.setInt(3, theItem.getCount());
			
			myStmt1.execute();
		}
		finally {
			close(myConn,myStmt1,null);
		}
		
		
		
	}

	public Item getItem(String table_name,String wh_locn,String theItemId) throws Exception {
		// TODO Auto-generated method stub
		Item theItem = null;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs = null;
		ResultSet myRs3=null;
		int itemIdint;
		
		try {
			//convert student id to int
			itemIdint=Integer.parseInt(theItemId);
			
			//get connection to database
			myConn = dataSource.getConnection();
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, wh_locn);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");
			
			//create sql to get selected student
			String sql="SELECT * FROM "+ table_name+"_"+wh_id+"_items"+" WHERE item_id= ?";
			
			//create a prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			//myStmt.setString(1, table_name);
			//myStmt.setString(1, temp);
			myStmt.setInt(1, itemIdint);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from resultset row
			if(myRs.next()) {
				String name_temp= myRs.getString("item_name");
				String brand_temp= myRs.getString("brand");
				String count_temp=myRs.getString("count1");
				int count = Integer.parseInt(count_temp);
				
				//use the itemId during construction
				theItem=new Item(itemIdint,name_temp,brand_temp,count);
			}
			else {
				throw new Exception("Could not find student id: "+ itemIdint);
			}
			
			return theItem;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}

	public void updateItem(String table_name,String wh_locn,Item theItem) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		
		try {
		//get db connection
		myConn=dataSource.getConnection();
		
		String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
		myStmt3=myConn.prepareStatement(sql3);
		myStmt3.setString(1, wh_locn);
		myRs3=myStmt3.executeQuery();
		//String wh_id;
		myRs3.next();
		String wh_id=myRs3.getString("wh_identifier");
		
		//create sql update statement
		String sql="UPDATE "+table_name+"_"+wh_id+"_items"+" SET item_name=?, brand=?, count1=?"
				+ " WHERE item_id=?";
		
		//prepare statement
		myStmt=myConn.prepareStatement(sql);
		
		//set params
		//myStmt.setString(1, table_name);
		myStmt.setString(1, theItem.getItem_name());
		myStmt.setString(2, theItem.getBrand());
		myStmt.setInt(3, theItem.getCount());
		myStmt.setInt(4, theItem.getItem_id());
		
		//execute sql statement
		myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
		
	}

	public void deleteitem(String table_name,String wh_locn,String theItemId) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement myStmt=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		
		try {
			//convert itemId to integer
			int id=Integer.parseInt(theItemId);
			
			//get a connection to the database
			myConn=dataSource.getConnection();
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, wh_locn);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");
			
			//create sql to delete the item
			String sql="DELETE from "+table_name+"_"+wh_id+"_items"+" WHERE item_id= ?";
			
			//prepare statement
			myStmt=myConn.prepareStatement(sql);
			
			//set params
			//myStmt.setString(1, table_name);
			myStmt.setInt(1, id);
			
			//execute the sql statement
			myStmt.execute();
			
		}
		finally {
			close(myConn,myStmt,myRs3);
		}
		
	}

	public int login_check(String username,String pwd) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="SELECT * FROM org_login_details "
					+ "WHERE org_username = ? AND pswrd= ?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, username);
			myStmt.setString(2, pwd);
			
			myRs=myStmt.executeQuery();
			
			if(myRs.next()) {
				return 1;
			}
			
			else return 0;
			
		}
		finally {
			close(myConn,myStmt,myRs);
		}
	}

	public void recordInvoice(String table_name,String wh_locn,Invoice theInvoice) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt1=null;
		PreparedStatement myStmt2=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		
		try {
			
			myConn=dataSource.getConnection();
			String sql1="INSERT INTO invoice "
					+ "(invoice_date,item_name,quantity) "
					+ "VALUES(?,?,?)";
			myStmt1=myConn.prepareStatement(sql1);
			myStmt1.setString(1,theInvoice.getInvoice_date());
			myStmt1.setString(2, theInvoice.getItemName()); 
			myStmt1.setInt(3, theInvoice.getQuantity());
			
			myStmt1.execute();
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, wh_locn);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");

			String sql2="UPDATE "+table_name+"_"+wh_id+"_items"
					+ " SET count1=count1-? WHERE item_name=?";
			myStmt2=myConn.prepareStatement(sql2);
			myStmt2.setInt(1,theInvoice.getQuantity());
			myStmt2.setString(2, theInvoice.getItemName());
			
			myStmt2.execute();
		}
		finally {
			close(myConn,myStmt1,null);
		}
		
	}

	public List<Invoice> viewOrders(String date) throws Exception{
		// TODO Auto-generated method stub
		List<Invoice> invoices=new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="SELECT * FROM invoice WHERE invoice_date=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, date);
			
			myRs=myStmt.executeQuery();
			while(myRs.next()) {
				String itemName=myRs.getString("item_name");
				int quantity=myRs.getInt("quantity");
				Invoice theInvoice=new Invoice(date,itemName,quantity);
				invoices.add(theInvoice);
			}
			return invoices;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		
	}

	public void signup(Company theCompany, int no_of_warehouses) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		PreparedStatement myStmt=null;
		Statement myStmt2=null;
		PreparedStatement myStmt3=null;
		try {
			myConn=dataSource.getConnection();
			String sql="INSERT INTO org_login_details "
					+ "(org_name, org_username,pswrd)"
					+ " VALUES(?, ?, ?)";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, theCompany.getComp_name());
			myStmt.setString(2, theCompany.getComp_username());
			myStmt.setString(3, theCompany.getPassword());
			myStmt.execute();
			
			for(int i=1;i<=no_of_warehouses;i++) {
			String sql2="CREATE TABLE "+theCompany.getComp_username()+"_wh"+i+"_items "
					+ "("
					+ "item_id INT(11) NOT NULL AUTO_INCREMENT,\n"
					+ "item_name VARCHAR(45) DEFAULT NULL,\n"
					+ "brand VARCHAR(45) DEFAULT NULL,\n"
					+ "count1 INT(11) DEFAULT NULL,\n"
					+ "PRIMARY KEY(item_id)\n"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1";
			myStmt2=myConn.createStatement();
			//myStmt2.setString(1, theCompany.getComp_username());
			myStmt2.execute(sql2);
			}
			
			String sql3="INSERT INTO warehouses_count "
					+ "(org_username, no_of_warehouses) "
					+ "VALUES(?, ?)";
			myStmt3= myConn.prepareStatement(sql3);
			myStmt3.setString(1, theCompany.getComp_username());
			myStmt3.setInt(2, no_of_warehouses);
			myStmt3.execute();
			
		}
		finally {
			close(myConn,myStmt,null);
		}
		
	}

	public void signup2(List<String> locations, String username, int no_of_warehouses) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		Statement myStmt=null;
		PreparedStatement myStmt2=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="CREATE TABLE "+username+"_whLocations "
					+ "(wh_identifier VARCHAR(10) DEFAULT NULL, "
					+ "wh_locn VARCHAR(40) DEFAULT NULL)";
			myStmt=myConn.createStatement();
			myStmt.execute(sql);
			for(int i=1;i<=no_of_warehouses;i++) {
				String wh_id_temp="wh"+i;
				String sql2="INSERT INTO "+username+"_whLocations "
						+ "(wh_identifier, wh_locn) "
						+ "VALUES(?, ?)";
				myStmt2=myConn.prepareStatement(sql2);
				myStmt2.setString(1, wh_id_temp);
				myStmt2.setString(2, locations.get(i-1));
				myStmt2.execute();
			}
		}
		finally {
			close(myConn,myStmt,null);
		}
		
	}

	public List<String> choose_listItems(String table_name1) throws Exception{
		// TODO Auto-generated method stub
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		List<String> locations=new ArrayList<>();
		
		try {
			
			myConn=dataSource.getConnection();
			String sql="SELECT * FROM "+table_name1+"_whLocations";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				String temp_locn=myRs.getString("wh_locn");
				locations.add(temp_locn);
			}
			return locations;
		}
		finally {
			close(myConn,myStmt,null);
		}
		

	}

	public List<LossDetails> viewLossDetails() throws Exception{
		// TODO Auto-generated method stub
		List<LossDetails> lossDetails=new ArrayList<>();
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myConn=dataSource.getConnection();
			myStmt=myConn.createStatement();
			String sql= "SELECT * FROM loss_records";
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				int loss_id=myRs.getInt("loss_recordId");
				String date_of_loss=myRs.getString("date_of_loss");
				String wh_name=myRs.getString("wh_name");
				String item_name=myRs.getString("item_name");
				int quantity=myRs.getInt("quantity");
				String reason=myRs.getString("reason");
				
				LossDetails theLossDetail=new LossDetails(loss_id,date_of_loss,wh_name,item_name,quantity,reason);
				lossDetails.add(theLossDetail);
				
			}
			return lossDetails;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		
	}

	public void recordLoss(String table_name,String wh_locn,LossDetails theLossDetail) throws Exception{
		// TODO Auto-generated method stub
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		PreparedStatement myStmt2=null;
		PreparedStatement myStmt3=null;
		ResultSet myRs3=null;
		
		try {
			myConn=dataSource.getConnection();
			
			String sql3="SELECT * FROM "+table_name+"_whLocations WHERE wh_locn= ?";
			myStmt3=myConn.prepareStatement(sql3);
			myStmt3.setString(1, wh_locn);
			myRs3=myStmt3.executeQuery();
			//String wh_id;
			myRs3.next();
			String wh_id=myRs3.getString("wh_identifier");

			
			String sql="INSERT INTO loss_records "
					+ "(date_of_loss,wh_name,item_name,quantity,reason)"
					+ " VALUES (?, ?, ?, ?, ?)";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, theLossDetail.getDate());
			myStmt.setString(2, theLossDetail.getWhName());
			myStmt.setString(3, theLossDetail.getItemName());
			myStmt.setInt(4, theLossDetail.getQuantity());
			myStmt.setString(5, theLossDetail.getReason());
			
			myStmt.execute();
			
			String sql2="UPDATE "+table_name+"_"+wh_id+"_items"
					+ " SET count1=count1-? WHERE item_name=?";
			
			myStmt2=myConn.prepareStatement(sql2);
			myStmt2.setInt(1,theLossDetail.getQuantity());
			myStmt2.setString(2, theLossDetail.getItemName());
			myStmt2.execute();
			
		}
		finally {
			close(myConn,myStmt3,null);
		}
		
	}
}
