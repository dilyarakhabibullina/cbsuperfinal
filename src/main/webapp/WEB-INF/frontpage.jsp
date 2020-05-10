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

<div class="container">
<h4> <%=request.getAttribute("myrecipes")%></h4>
<form action="/search">
<input name="q" placeholder="Поиск">


<% for (Recipe item: (Collection<Recipe>) request.getAttribute("recipes")) { %>
<div class="list-group-item">
<h4><%= item.getName() %></h4>
<h5><%= item.getDescription() %></h5>
<p> <%= item.getIngredients() %></p>
<form action="<%= request.getContextPath() %>/edit" method="post">
<input type="hidden" name="id" value="<%= item.getId() %>">
<input type="hidden" name="action" value="edit">
<button>Редактировать</button>
</form>
<form action="<%= request.getContextPath() %>/" method ="post">
<input type="hidden" name="id" value="<%= item.getId() %>">
<input type="hidden" name="action" value="remove">
<button>Удалить</button>
</form>
</div>

<% } %>

</div>
</form>


<% Recipe rec = (Recipe) request.getAttribute("item"); %>
<form action="<%= request.getContextPath() %>/" method="post">
<input type="hidden" name="id" value="<%= rec == null ? "" : rec.getId() %>">
<input type="hidden" name="action" value="save">
<input name="name" placeholder="Название рецепта" value="<%= rec == null ? "" : rec.getName() %>">
<input name="ingredients" placeholder="Состав" value="<%= rec == null ? "" : rec.getIngredients() %>">
<textarea name="description" placeholder="Описание" value="<%= rec == null ? "" : rec.getDescription() %>"></textarea>
<button>Сохранить</button>
</form>





</div>
</body>
</html>