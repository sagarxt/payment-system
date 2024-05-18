<%@page import="com.techm.entity.Orders"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/x-icon" href="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <title>View Order</title>
</head>
<body>
<c:if test="${sessionScope.biller == null}">       
       <div class="container">
            <div class="row justify-content-center">
                <div class="col text-center">
                    <div class="text text-center fs-4 fw-bold mt-5 mb-2">Your Session has Expired, Please Login Again</div>
                    <a class="btn btn-primary m-2 fw-bold ps-5 pe-5" href="/home" role="button">Home Page...</a>
                </div>
            </div>
        </div>
</c:if>
<c:if test="${sessionScope.biller != null}">
  <nav class="navbar navbar-expand-lg navbar-dark bg-warning sticky-top border-bottom border-5 border-white">
    <div class="container-fluid">
      <a class="navbar-brand" href="/biller/dashboard">
        <img src="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png" alt="" width="40" height="40" class="d-inline-block">
        <span class="text fw-bold fs-4 ms-1">Payment System</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
          <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
            <span class="text text-light fw-bold fs-5 me-4">Hi, ${biller.billerName}</span>
            <a class="btn btn-danger m-2 fw-bold" href="/home/logout" role="button">Logout</a>
          </div>
    </div>
  </nav>

  <div class="row container-fluid">
    <div class="col col-3">
      <h3 class="text-center text-primary pt-3">Biller Dashboard</h3>

      <ul class="nav flex-column">
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/profile">View Profile</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/add-product">Add Product</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/view-products">View Products</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/pending-orders">Pending Orders</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/all-orders">All Orders</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/biller/registered-customers">Registered Customers</a>
        </li>
      </ul>
    </div>
    
    
    <div class="col col-9">
      <div class="row m-2">
        <span class="text text-center fs-4 fw-bold">Order Details (Receipt)</span>

        <div class="col">
        
		<% Orders orders = (Orders) request.getAttribute("orders");%>
	         <div class="card m-3 border-0">
				  <img class="card-img-top rounded" src="https://i.postimg.cc/CxQVyg4V/Picsart-24-04-27-17-03-58-141.jpg" alt="Card image cap" height="150px">
				  <div class="card-body row mt-3">
				  	<div class="col m-1 ms-5">
				  		<span class="text fw-bold row">Product Name : <%= orders.getProduct().getName() %></span>
				  		<span class="text row mt-1">Description : <%= orders.getProduct().getDescription() %></span>
				  		<span class="text row mt-1">Product Category : <%= orders.getProduct().getCategory() %></span>
				  		<span class="text fw-bold row mt-3">Biller Name : <%= orders.getProduct().getBiller().getBillerName() %></span>
				  		<span class="text row mt-1">Biller Email : <%= orders.getProduct().getBiller().getEmail() %></span>
				  	</div>
				  	<div class="col m-1 mt-1">
				  		<span class="text fw-bold row">Buyer Name : <%= orders.getCustomer().getName() %></span>
				  		<span class="text row mt-1">Buyer Email : <%= orders.getCustomer().getEmail() %></span>
				  		<span class="text fw-bold text-warning row mt-5">Order Bill ID : <%= orders.getBillId() %></span>
				  	</div>
				  </div>
				  <div class="card-body row border-top border-bottom">
				  	<div class="col m-1 ms-5">
				  		<span class="text fw-bold row  text-primary">Order Status : <%= orders.getOrderStatus() %></span>
				  		<span class="text row mt-1"  id="timestamp"></span>
				  	</div>
				  	<div class="col">
				  		<span class="text fw-bold row">Product Price : <%= orders.getProduct().getPrice() %></span>
				  		<span class="text fw-bold row text-info mt-1">Payment Status : <%= orders.getPaymentStatus() %></span>
				  	</div>
				  </div>
			</div>

          <script>
		        // Function to convert ISO datetime to 12-hour format with AM and PM and DD-MM-YYYY format
		        function convertTo12HourDateTime(isoDateTime) {
		            var dateTime = new Date(isoDateTime);
		            var day = dateTime.getDate().toString().padStart(2, '0');
		            var month = (dateTime.getMonth() + 1).toString().padStart(2, '0'); // Add 1 because month index starts from 0
		            var year = dateTime.getFullYear();
		            var hours = dateTime.getHours();
		            var minutes = dateTime.getMinutes().toString().padStart(2, '0');
		            var amPM = hours >= 12 ? 'PM' : 'AM';
		            hours = hours % 12 || 12;
		            return "Order Date : " + day + "-" + month + "-" + year + " " + hours + ":" + minutes + " " + amPM;
		        }
		        // Example usage
		        window.onload = function() {
		            var isoDateTime = "<%=orders.getOrderDate()%>";
		            var twelveHourDateTime = convertTo12HourDateTime(isoDateTime);
		            document.getElementById("timestamp").textContent = twelveHourDateTime;
		        };
		</script>

         
        </div>
      </div>
    </div>
  </div>
   

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</c:if>
</body>
</html>