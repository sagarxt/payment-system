<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/x-icon" href="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <title>C Registration</title>
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/home">
        <img src="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png" alt="" width="35" height="35" class="d-inline-block align-text-top">
        <span class="text fw-bold">Payment System</span>
      </a>
      <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <a class="btn btn-warning m-2 fw-bold" href="/biller/register" role="button">Register as Biller</a>
        <a class="btn btn-warning m-2 fw-bold" href="/customer/register" role="button">Register as Customer</a>
      </div>
    </div>
  </nav>

  <div class="container d-flex justify-content-center mt-5 p-5 border rounded shadow w-50">

    <form action="/home/login" method="post">
       <div class="message text-center mb-3 fw-bold text-info">
           ${message}
       </div>

      <span class="text text-center mb-5 fs-2 fw-bold text-primary">Login (Customer & Biller)</span>
      <div class="mb-3 mt-4">
        <label for="email" class="form-label">Enter Your Email address</label>
        <input type="email" class="form-control" id="email" name="email" required>
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>
      <button type="submit" class="btn btn-primary w-100 mt-3">Login</button>
    </form>
  </div>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>