<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.library.model.Author" %>
<%@ page import="uz.pdp.library.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uz">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manga Kitobini Yangilash</title>
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
<%! private Book book;%>
<% authors = (List<Author>) request.getAttribute("authors");%>
<% book = (Book) request.getAttribute("book");%>

<h2>Manga Kitobini Yangilash</h2>
<h2><%=book.toString()%>
</h2>
<h2><%=authors.toString()%>
</h2>
<form action="/book" method="post">
    <input type="text" name="id" value="<%=book.getId()%>" hidden>
    <input type="text" name="action" value="update" hidden>
    <div class="form-group">
        <label for="bookName">Kitob Nomi:</label>
        <input type="text" id="bookName" name="name" value="<%=book.getName()%>" required>
    </div>
    <div class="form-group">
        <label for="authorId">Muallif:</label>
        <select id="authorId" name="authorId" required>
            <%
                for (Author author : authors) {
                    if (book.getAuthorId().equals(author.getId())) {
            %>
            <option value="<%=author.getId()%>" selected><%=author.getFirstName()%>
            </option>
            <%} else {%>
            <option value="<%=author.getId()%>"><%=author.getFirstName()%>
                    <%}}%>
        </select>
    </div>
    <div class="form-group">
        <label for="publishYear">Nashr Yili:</label>
        <input type="number" id="publishYear" name="publishYear" min="1900" max="2025"
               value="<%=book.getPublishYear()%>" required>
    </div>
    <button type="submit">Update</button>
</form>
</body>
</html>
