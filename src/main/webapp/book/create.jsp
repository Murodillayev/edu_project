<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.library.model.Author" %>
<%@ page import="uz.pdp.library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%! private List<Author> authors;%>
<% authors = (List<Author>) request.getAttribute("authors");%>

<h2>Kitobini tahrirlash</h2>

<form action="/book" method="post" enctype="multipart/form-data">
    <input type="text" name="action" value="create" hidden>
    <div class="form-group">
        <label for="bookName">Kitob Nomi:</label>
        <input type="text" id="bookName" name="name" required>
    </div>
    <div class="form-group">
        <label for="authorId">Muallif:</label>
        <select id="authorId" name="authorId" required>
            <%for (Author author : authors) {%>
               <option value="<%=author.getId()%>"><%=author.getFirstName() + " " + author.getLastName()%>
            <%}%>
        </select>
    </div>
    <div class="form-group">
        <label for="publishYear">Nashr Yili:</label>
        <input type="number" id="publishYear" name="publishYear" min="1900" max="2025" required>
    </div>
    <div class="form-group">
        <label for="imgFile">Kiton rasmi:</label>
        <input type="file" id="imgFile" name="imgFile" required>
    </div>
    <button type="submit">Save</button>
</form>
</body>
</html>

