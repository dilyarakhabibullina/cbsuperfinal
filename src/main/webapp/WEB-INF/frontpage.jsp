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

<img src="/Upload/king.jpg %>" style="height:75px;"/>
<form action="/search">
<input name="q" placeholder="�����">
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
   <li class="list-group-item">������: <%= item.getIngredients() %></li>
    <li class="list-group-item">��������: <%= item.getDescription() %></li>

  </ul>
  <div class="card-body">

<form action="<%= request.getContextPath() %>/edit" method="post">
<input type="hidden" name="id" value="<%= item.getId() %>">
<input type="hidden" name="action" value="edit">
<button>�������������</button>
</form>
<form action="<%= request.getContextPath() %>/" method ="post">
<input type="hidden" name="id" value="<%= item.getId() %>">
<input type="hidden" name="action" value="remove">
<button>�������</button>
</form>

  </div>
  </div>
</div>

<% } %>
</div>







<% Recipe rec = (Recipe) request.getAttribute("item"); %>
<form action="<%= request.getContextPath() %>/" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<%= rec == null ? "" : rec.getId() %>">
<input type="hidden" name="action" value="save">
<input name="name" placeholder="�������� �������" value="<%= rec == null ? "" : rec.getName() %>">
<input name="ingredients" placeholder="������" value="<%= rec == null ? "" : rec.getIngredients() %>">
<textarea name="description" placeholder="��������" value="<%= rec == null ? "" : rec.getDescription() %>"></textarea>
<input type ="file" name= "file" accept="image/* ">
<button>���������</button>
</form>





</div>
</body>
</html>