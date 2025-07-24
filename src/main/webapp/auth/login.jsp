<%--
  Created by IntelliJ IDEA.
  User: macbookpro
  Date: 24/07/25
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Sahifasi</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <style>
    body {
      background-color: #f0f2f5;
    }
    .login-container {
      max-width: 400px;
      margin: auto;
      padding: 20px;
    }
    .card {
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .btn-primary:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
<div class="login-container">
  <div class="card p-4">
    <h2 class="text-center mb-4">Login</h2>
    <form action="/login" method="post">
      <div class="mb-3">
        <label for="username" class="form-label">Login</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="username" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Parol" required>
      </div>
      <div class="alert alert-danger d-none" role="alert" id="errorMessage">
        Foydalanuvchi nomi yoki parol xato!
      </div>
      <button type="submit" class="btn btn-primary w-100">login</button>
    </form>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
