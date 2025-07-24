<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.library.model.Book" %>
<%@ page import="uz.pdp.library.dto.BookDto" %>
<%@ page import="uz.pdp.library.dao.BookDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kutubxona - Kitoblar Ro'yxati</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #e0e7ff, #c3e8ff);
            min-height: 100vh;
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.3);
        }

        .card-img-top {
            height: 220px;
            object-fit: cover;
            border-bottom: 2px solid #e0e0e0;
        }

        .card-body {
            background: #ffffff;
            padding: 20px;
        }

        .card-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #2c3e50;
        }

        .card-text {
            color: #34495e;
        }

        .card-footer {
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            border-top: none;
            padding: 15px;
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .btn-custom {
            border-radius: 10px;
            padding: 8px 16px;
            font-size: 0.9rem;
            transition: all 0.3s ease;
        }

        .btn-detail {
            background-color: #007bff;
            border: none;
        }

        .btn-detail:hover {
            background-color: #0056b3;
        }

        .btn-update {
            background-color: #28a745;
            border: none;
        }

        .btn-update:hover {
            background-color: #218838;
        }

        .btn-delete {
            background-color: #dc3545;
            border: none;
        }

        .btn-delete:hover {
            background-color: #b02a37;
        }

        .container h2 {
            font-weight: 700;
            color: #2c3e50;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<%! private List<BookDto> books; %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <%if (session.getAttribute("fullName") != null) {%>
        <a class="navbar-brand" href="#"><%=session.getAttribute("fullName")%>
        </a>
        <%}%>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <%if (session.getAttribute("userId") == null) {%>
                    <form action="/login">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                    <%} else {%>
                    <form action="/logout" method="post">
                        <button type="submit" class="btn btn-warning">logout</button>
                    </form>
                    <%}%>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container mt-5 mb-5">
    <h2 class="text-center mb-5">Kutubxona Kitoblari</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <%
            books = (List<BookDto>) request.getAttribute("books");

            for (BookDto book : books) {%>


        <div class="col">
            <div class="card h-100">
                <img src="https://via.placeholder.com/300x220?text=Alkimyogar" class="card-img-top"
                     alt="Alkimyogar muqovasi">
                <div class="card-body">
                    <h5 class="card-title"><%= book.getName()%>
                    </h5>
                    <p class="card-text"><strong>Muallif:</strong><%=book.getAuthorName()%>
                    </p>
                    <p class="card-text"><strong>Nashr yili:</strong> <%=book.getPublishYear()%>
                    </p>
                </div>
                <div class="card-footer">

                    <a href="#" class="btn btn-custom btn-detail text-white">Info</a>
                    <%if (session.getAttribute("userId") != null) {%>
                    <form action="/book">
                        <input type="text" name="bookId" value="<%= book.getId() %>" hidden>
                        <input type="text" name="action" value="update" hidden>
                        <button type="submit"><a class="btn btn-custom btn-update text-white">Update</a></button>
                    </form>
                    <a href="#" class="btn btn-custom btn-delete text-white "><i class="fa fa-trash"></i> Delete </a>
                    <%}%>
                </div>
            </div>
        </div>

        <%}%>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>