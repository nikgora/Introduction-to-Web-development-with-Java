<!-- login.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Авторизація</title>
</head>
<body>
<h2>Авторизація користувача</h2>
<form action="login" method="post">
  Логін: <input type="text" name="username" required/><br/>
  Пароль: <input type="password" name="password" required/><br/>
  <input type="submit" value="Увійти"/>
</form>
<c:if test="${'invalid'.equals(param.error)}">
  <p style="color:red;">Неправильний логін або пароль!</p>
</c:if>
</body>
</html>
