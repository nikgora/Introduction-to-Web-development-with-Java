<!-- dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
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
    <li>${account.type}:
      <c:choose>
        <c:when test="${not empty account.currency and fn:length(account.currency) == 3}">
          <fmt:formatNumber value="${account.balance}" type="currency" currencyCode="${account.currency}" />
        </c:when>
        <c:otherwise>
          ${account.balance} <!-- or provide a default currency format here -->
        </c:otherwise>
      </c:choose>
    </li>
  </c:forEach>
</ul>
</body>
</html>