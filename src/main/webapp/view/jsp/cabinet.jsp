<%@ page import="model.entity.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>cabinet</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Hello !!</h1>
    <% HttpSession sett = request.getSession();
       Person person = (Person) sett.getAttribute("person");
    %>

    <center><h1><%= "Привет " + person.getName() %></h1></center>
    <center>Твой емайл: <%= person.getEmail() %></center>
</body>
</html>
