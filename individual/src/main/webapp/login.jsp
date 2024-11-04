<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <title>Dashboard</title>
</head>
<body>
<h2>Welcome, ${user.username}</h2>
<h3>Your Accounts:</h3>
<ul>
  <c:forEach var="account" items="${user.accounts}">
    <li>${account.type}: ${account.balance} USD</li>
  </c:forEach>
</ul>
</body>
</html>