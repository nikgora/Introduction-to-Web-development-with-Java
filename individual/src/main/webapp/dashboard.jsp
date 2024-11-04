<!-- dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="bank" uri="http://www.bank.com/tags" %>
<html>
<head>
  <title>Особистий кабінет</title>
</head>
<body>
<h2>Ласкаво просимо, ${user.username}</h2>

<!-- Використання custom tag -->
<bank:accountInfo user="${user}" />

<!-- Динамічний контент -->
<h3>Ваші рахунки:</h3>
<ul>
  <c:forEach var="account" items="${user.accounts}">
    <li>${account.type}: ${account.balance} грн</li>
  </c:forEach>
</ul>
</body>
</html>
