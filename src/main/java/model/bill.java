package model;

import java.sql.*; 

public class bill {
	
	//A common method to connect to the DB
	
		private Connection connect()
		 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
			
				 //Provide the correct details: DBServer/DBName, username, password
				 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powereg", "root", "");
			 }
				 catch (Exception e)
				 {e.printStackTrace();}
				 return con;
		 }

	//insert method

		public String insertBill(String ele_Acc_num, String cus_name, String address, String month, String meter_units, String unit_price, String total_amount)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 
				 String query = " insert into bill (`bill_id`,`ele_Acc_num`,`cus_name`,`address`,`month`,`meter_units`,`unit_price`,`total_amount`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, ele_Acc_num);
				 preparedStmt.setString(3, cus_name);
				 preparedStmt.setString(4, address);
				 preparedStmt.setString(5, month);
				 preparedStmt.setString(6, meter_units);
				 preparedStmt.setString(7, unit_price);
				 preparedStmt.setString(8, total_amount);
				
				 
				 
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 String newbill = readBill();
					output = "{\"status\":\"success\", \"data\": \"" +newbill + "\"}";
			 }
			 
			 catch (Exception e)
			 {
				 output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}

	//view method
		public String readBill()
		{
				String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table border='1'> <tr><th>Bill ID</th>"
								+ "<th>Electricity Acc No</th>"
								+ "<th>Customer Name</th>"
								+ "<th>Address</th>"
								+ "<th>Month</th>"
								+ "<th>Meter Units</th>"
								+ "<th>Unit_Price</th>"
								+ "<th>Total Amount</th>" 
					            + "<th>Update</th>"
					            + "<th>Remove</th></tr>";
					 
					 String query = "select * from bill";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
					 {
						 String bill_id = Integer.toString(rs.getInt("bill_id"));
						 String ele_Acc_num = rs.getString("ele_Acc_num");
						 String cus_name = rs.getString("cus_name");
						 String address = rs.getString("address");
						 String month = rs.getString("month");
						 String meter_units = rs.getString("meter_units");
						 String unit_price = rs.getString("unit_price");
						 String total_amount = rs.getString("total_amount");
						 
			
						 
						 // Add into the html table
						 output += "<td>" + bill_id + "</td>";
						 output += "<td>" + ele_Acc_num + "</td>";
						 output += "<td>" + cus_name + "</td>";
						 output += "<td>" + address + "</td>";
						 output += "<td>" + month + "</td>";
						 output += "<td>" + meter_units + "</td>";
						 output += "<td>" + unit_price + "</td>";
						 output += "<td>" + total_amount + "</td>";
						 
						 
						 
						 // buttons
						 
						 output += "<td><form method='post' action='updateBill.jsp'>"
							 		+ "<input name='btnUpdate' type='submit' value='Update'class='btn btn-secondary'>"
							 		+ "<input name='uid' type='hidden' value='" + bill_id + "'>" + "</form></td>"
							 + "<td><form method='post' action='viewBill.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='bill_id' type='hidden' value='" + bill_id + "'>" + "</form></td></tr>";
					 }
					 con.close();
					 
					 // Complete the html table
					 
					 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading the bill.";
					 System.err.println(e.getMessage());
				 }
				 return output;
		}
		
		//Update Query
		public String updateBill(String bill_id,String ele_Acc_num, String cus_name, String address, String month, String meter_units, String unit_price, String total_amount)
		{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for updating."; }
					 
					 // create a prepared statement
					 
					 String query = "UPDATE bill SET ele_Acc_num=?,cus_name=?,address=?,month=?,meter_units=?,unit_price=?,total_amount=? WHERE bill_id=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 
					 preparedStmt.setString(1, ele_Acc_num);
					 preparedStmt.setString(2, cus_name);
					 preparedStmt.setString(3, address);
					 preparedStmt.setString(4, month);
					 preparedStmt.setString(5, meter_units);
					 preparedStmt.setString(6, unit_price);
					 preparedStmt.setString(7, total_amount);
					 preparedStmt.setInt(8, Integer.parseInt(bill_id));
					 
					 
					 // execute the statement
					 
					 preparedStmt.execute();
					 con.close();
					 String newbill = readBill();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newbill + "\"}";
				 }
				 
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
					 System.err.println(e.getMessage());
				 }
				 return output;
		}

	//delete method

		public String deleteBill(String bill_id)
		{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for deleting."; 
					 }
					 
					 // create a prepared statement
					 String query = "delete from bill where bill_id=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(bill_id));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newbill = readBill();
					 output = "{\"status\":\"success\", \"data\": \"" +
					 newbill + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\": \"Error while deleting the bill.\"}";
					 System.err.println(e.getMessage());
				 }
				 return output;
		}

}
