package com;

import model.bill;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/bill")

public class billService {
	
	bill billObj = new bill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readbill()
	 {
		return billObj.readBill(); 
	 } 
	
	


	// insert user details
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertBill(
		
			@FormParam("ele_Acc_num") String ele_Acc_num,
			@FormParam("cus_name") String cus_name,
			@FormParam("address") String address,
			@FormParam("month") String month,
			@FormParam("meter_units") String meter_units,
			@FormParam("unit_price") String unit_price,
			@FormParam("total_amount") String total_amount)
			
		

		
				{
					 String output = billObj.insertBill(ele_Acc_num, cus_name, address, month, meter_units, unit_price, total_amount);
					 return output;
				}
						
						
						
	//update user details
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateBill(String billData)
		{
		   //Convert the input string to a JSON object
			JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
							 
		    //Read the values from the JSON object
				String bill_id = billObject.get("bill_id").getAsString();			
				String ele_Acc_num = billObject.get("ele_Acc_num").getAsString();
				String cus_name = billObject.get("cus_name").getAsString();
				String address = billObject.get("address").getAsString();
			    String month = billObject.get("month").getAsString();
			    String meter_units = billObject.get("meter_units").getAsString();
			    String unit_price = billObject.get("unit_price").getAsString();
			    String total_amount = billObject.get("total_amount").getAsString();
			    
				
				String output = billObj.updateBill(bill_id,ele_Acc_num,cus_name,address,month,meter_units,unit_price,total_amount);
				return output;
		}
						
		
		
	//delete user details
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData)
		{
		 //Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
					
		//Read the value from the element <idUnit>
		 String bill_id = doc.select("bill_id").text();
		 String output = billObj.deleteBill(bill_id);
		 return output;
		 
		}

}
