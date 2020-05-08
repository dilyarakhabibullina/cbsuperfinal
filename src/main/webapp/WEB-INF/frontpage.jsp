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


<img src="<%= request.getContextPath() %>/king.jpg">
<img src="/Upload/king.jpg">

<form action="/search">
<input name="q" placeholder="Поиск">
</form>


<div class="row" padding="10px">



<% for (Recipe item: (Collection<Recipe>) request.getAttribute("recipes")) { %>
<div class="col-sm-4 m-3">


<div class="card">
  <img class="card-img-top" src="<%= request.getContextPath() %>/images/<%= item.getId() %>" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title"><%= item.getName() %></h5>

  </div>
  <ul class="list-group list-group-flush">
   <li class="list-group-item">Состав: <%= item.getIngredients() %></li>
    <li class="list-group-item">Описание: <%= item.getDescription() %></li>

  </ul>
  <div class="card-body">

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
  </div>
</div>

<% } %>
</div>
<label for="basic-url">У тебя есть новый рецепт? Пиши...</label>
<% Recipe rec = (Recipe) request.getAttribute("item"); %>
<form action="<%= request.getContextPath() %>/" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<%= rec == null ? "" : rec.getId() %>">
<input type="hidden" name="action" value="save">


<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon3">Название рецепта</span>
  </div>
  <input type="text" name="name" value="<%= rec == null ? "" : rec.getName() %>" class="form-control" id="basic-url" aria-describedby="basic-addon3">
</div>

<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text">Состав</span>
  </div>
  <input type="text" class="form-control" name="ingredients" aria-label="Ингредиенты" value="<%= rec == null ? "" : rec.getIngredients() %>">

</div>

<div class="input-group">
  <div class="input-group-prepend">
    <span class="input-group-text">Описание</span>
  </div>
  <textarea name="description" value="<%= rec == null ? "" : rec.getDescription() %>" class="form-control" aria-label="With textarea"></textarea>
</div>

<input type ="file" name= "file" accept="image/* ">
<button class="btn-primary">Сохранить</button>
</form>

</div>
</body>
</html>