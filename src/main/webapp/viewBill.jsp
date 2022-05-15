<%@page import="model.bill" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<h1>BILL DETAILS</h1>
<%
//Delete Bill----------------------------------
	if (request.getParameter("bill_id") != null)
	{
		bill billObj = new bill();
	String stsMsg = billObj.deleteBill(request.getParameter("bill_id"));
	session.setAttribute("statusMsg", stsMsg);
	}   
	
 bill billObj = new bill();
 out.print(billObj.readBill());
%>

</body>
</html>