<%@page import="com.techm.entity.Product"%>
<%@page import="java.util.List"%>
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
  <title>Customer Dashboard</title>
</head>
<body>
<c:if test="${sessionScope.customer == null}">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col text-center">
                    <div class="text text-center fs-4 fw-bold mt-5 mb-2">Your Session has Expired, Please Login Again</div>
                    <a class="btn btn-primary m-2 fw-bold ps-5 pe-5" href="/home" role="button">Home Page...</a>
                </div>
            </div>
        </div>
</c:if>
<c:if test="${sessionScope.customer != null}">
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top border-bottom border-5 border-white">
    <div class="container-fluid">
      <a class="navbar-brand" href="/customer/dashboard">
        <img src="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png" alt="" width="40" height="40" class="d-inline-block">
        <span class="text fw-bold fs-4 ms-1">Payment System</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
          <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
            <span class="text text-light fw-bold fs-5 me-4">Hi, ${customer.name}</span>
            <a class="btn btn-danger m-2 fw-bold" href="/home/logout" role="button">Logout</a>
          </div>

    </div>
  </nav>

  <div class="row container-fluid">
    <div class="col col-3">
      <h3 class="text-center text-warning pt-3">Customer Dashboard</h3>

      <ul class="nav flex-column">
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/customer/profile">View Profile</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/customer/my-orders">My Orders</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/customer/all-billers">All Billers</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/customer/all-products">All Products</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-primary p-3" href="/customer/registered-billers">Registered Billers</a>
        </li>
        <li class="nav-item p-3">
          <a class="nav-link text-light fw-bold btn btn-success p-3 disabled" href="/customer/biller-bills">Biller Bills</a>
        </li>

      </ul>
    </div>
    
    <div class="col col-9">
      <div class="message text-center text-warning fw-bold mt-3">
        ${message}
      </div>
      <div class="row m-2">
        <span class="text text-center fs-4 fw-bold">Customer Profile</span>

        <div class="col">

          <div class="d-flex justify-content-center m-3">
            <img src="https://i.postimg.cc/xjm5r4Bj/profile-icon.png" width="150px" height="150px" alt="">
          </div>

          <div class="d-flex justify-content-center m-3">
            <table>
              <tr class="m-2 fs-5">
                <th>Name:</th>
                <th> ${customer.name}</th>
              </tr>
              <tr class="m-2 fs-5">
                <th>Email:</th>
                <th> ${customer.email}</th>
              </tr>
            </table>
          </div>

          <div class="d-flex justify-content-center">

               <form class="row g-3 mt-4" action="/customer/change-password" method="post">
                <div class="col-auto">
                  <input type="password" class="form-control" id="inputPassword2" placeholder="Old Password" name="oldPassword" required>
                </div>
                <div class="col-auto">
                  <input type="password" class="form-control" id="inputPassword2" placeholder="New Password" name="newPassword" required>
                </div>
                <div class="col-auto">
                  <input type="hidden" class="form-control" id="customerId" name="customerId" value="${customer.customerId}" required>
                  <button type="submit" class="btn btn-primary mb-3">Change Password</button>
                </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</c:if>
</body>
</html>