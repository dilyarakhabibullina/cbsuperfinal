<%@ page import="ru.itpark.domain.Recipe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page  language="java" contentType="text/html;charset=UTF-8"%>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New recipe</title>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="container">
<h4> Ура! Новый рецепт!</h4>



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
<p></p>
<div class="input-group mb-3">
<div class="input-group-prepend">
    <span class="input-group-text">Фото</span>
  </div>
  <div class="custom-file">
    <input type="file" name ="file" class="custom-file-input" id="inputGroupFile04">
    <label class="custom-file-label" for="inputGroupFile04">Выбери файл</label>
  </div>
  <div class="input-group-append">

  <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon03">Сохранить</button>
  </div>

</div>
</form>
</div>
</div>

<%@ include file="bootstrap-scripts.jsp" %>
</body>
</html>