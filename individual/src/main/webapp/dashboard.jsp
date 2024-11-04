<!-- dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="bank" uri="http://www.bank.com/tags" %>
<html>
<head>
  <title>Особистий кабінет</title>
</head>
<body>
<!-- Використання custom tag -->
<bank:accountInfo user="${user}" />

<!-- Статичний контент -->
<h2>Ласкаво просимо, ${user.username}</h2>

<!-- Динамічний контент -->
<h3>Ваші рахунки:</h3>
<ul>
  <c:forEach var="account" items="${user.accounts}">
    <li>${account.type}: ${account.balance} грн</li>
  </c:forEach>
</ul>
</body>
</html>