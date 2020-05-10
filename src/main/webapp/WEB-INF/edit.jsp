<%@ page import="ru.itpark.domain.Recipe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page  language="java" contentType="text/html;charset=cp1251"%>

<!doctype html>
<html lang="en">

<head>
    <meta charset="cp1251">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h4>Редактировать можно здесь: </h4>

 <% Recipe item = (Recipe) request.getAttribute("item"); %>
<form action="<%= request.getContextPath() %>/" method="post">
<input type="hidden" name="id" value="<%= item == null ? "" : item.getId() %>">
<input type="hidden" name="action" value="save">
<input name="name" placeholder="Название рецепта" value="<%= item == null ? "" : item.getName() %>">
<input name="ingredients" placeholder="Состав" value="<%= item == null ? "" : item.getIngredients() %>">
<textarea name="description" placeholder="Описание" value="<%= item == null ? "" : item.getDescription() %>"></textarea>
<button>Сохранить</button>
</form>


</body>
</html>