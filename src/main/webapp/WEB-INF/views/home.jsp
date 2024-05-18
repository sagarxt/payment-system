<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/x-icon" href="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <title>Payment System</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/home">
        <img src="https://i.postimg.cc/sgHqJ95p/payment-system-logo.png" alt="" width="35" height="35" class="d-inline-block align-text-top">
        <span class="text fw-bold">Payment System</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
        <a class="btn btn-warning m-2 fw-bold" href="/customer/register" role="button">Register</a>
        <a class="btn btn-success m-2 fw-bold" href="/home/login" role="button">Login</a>
      </div>
    </div>
  </nav>

  <div class="container d-flex justify-content-center mt-5 p-5">
    <div class="container">
      <h1 class="text fw-bold pt-5">Welcome to Payment System</h1>
      <p class="text">Pay All Bills at One Place, with most trusted billers and products.</p>

      <div>
        <a class="btn btn-primary" href="/customer/register" role="button">Getting Started</a>
      </div>
    </div>
    <div >
      <img src="https://i.postimg.cc/j2bvQvvd/ps-img1.jpg" width="400px" height="400px" class="rounded-circle" alt="...">
    </div>
  </div>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>