<%@page import="model.bill" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%    
    //update part
 if (request.getParameter("bill_id") != null)
 {
 bill billObj = new bill();
 String stsMsg = billObj.updateBill(request.getParameter("bill_id"),

 request.getParameter("ele_Acc_num"),
 request.getParameter("cus_name"),
 request.getParameter("address"),
 request.getParameter("month"),
 request.getParameter("meter_units"), 
 request.getParameter("unit_price"),
 request.getParameter("total_amount"));
 session.setAttribute("statusMsg", stsMsg);
 }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<h1>Bill Management</h1>
<h3><u>Update Bill </u></h3>

<form method="post" action="updateBill.jsp">

 <div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Ele Acc Num: </span>
	</div>
	<input name="ele_Acc_num" type="text" id="ele_Acc_num"/><br><br> 
	
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Customer Name: </span>
	</div>
	<input name="cus_name" type="text" id="cus_name"/><br><br> 
	 
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Address: </span>
	</div>
	<input name="address" type="text" id="address"/><br><br> 
	 
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Month: </span>
	</div>
	<select id="month" name="month">
	<option value="0">--Select year--</option>
	<option value="1">January</option>
	<option value="2">Febrary</option>
	<option value="3">March</option>
	<option value="4">April</option>
	<option value="5">May</option>
	<option value="6">June</option>
	<option value="7">July</option>
	<option value="8">August</option>
	<option value="9">September</option>
	<option value="10">October</option>
	<option value="11">November</option>
	<option value="12">December</option>
	</select>
	</div>
	<br><br> 
	
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Meter Units: </span>
	</div>
	<input name="meter_units" type="text" id="meter_units"/><br><br> 
	 
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Unit Price: </span>
	</div>
	<input name="unit_price" type="text" id="unit_price"/><br/><br/> 
	
	<div class="input-group input-group-sm mb-3">
	<div class="input-group-prepend">
	<span class="input-group-text" id="lblName">Total Amount: </span>
	</div>
	<input name="total_amount" type="text" id="total_amount"/>
	<br/>
	<br/>
	 
	  
	 <div class="input-group-prepend">
	 <input name="btnSubmit" type="submit" value="Submit" class="btn btn-primary"/>
	</div>
</form>
<br>
<br>




<br>
<%
 bill billObj = new bill();
 out.print(billObj.readBill());
%>
</body>
</html>