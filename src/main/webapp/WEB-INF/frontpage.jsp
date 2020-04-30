<%@ page import="ru.itpark.domain.Recipe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page  language="java" contentType="text/html;charset=cp1251"%>
<%@ page pageEncoding="cp1251"%>
<%request.setCharacterEncoding("cp1251");%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="cp1251">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h4> <%=request.getAttribute("myrecipes")%></h4>
<form action="/search">
<input name="q" placeholder="Поиск">

</form>


<ul>
<% for (Recipe item : (Collection<Recipe>)request.getAttribute("recipes")) { %>
<li>
<%= item.getName() %>
<%= item.getIngredients() %>
<%= item.getDescription() %>
</li>
<% } %>
</ul>


</body>
</html>