<!-- dashboard.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ taglib prefix="bank" uri="http://www.bank.com/tags" %>
<html>
<head>
  <title>Особистий кабінет</title>
  <script>
    document.addEventListener("DOMContentLoaded", function() {
      document.getElementById("showFormButton").addEventListener("click", function() {
        let form = document.getElementById("addAccountForm");
        let button = document.getElementById("showFormButton");
        if (form.style.display === "none") {
          form.style.display = "block";
          button.innerText = "Сховати форму";
        } else {
          form.style.display = "none";
          button.innerText = "Додати рахунок";
        }
      });

      document.querySelectorAll(".toggleFormButton").forEach(function(button) {
        button.addEventListener("click", function() {
          let formId = button.getAttribute("data-form-id");
          let form = document.getElementById(formId);
          if (form.style.display === "none") {
            form.style.display = "block";
            button.innerText = "Сховати форму";
          } else {
            form.style.display = "none";
            button.innerText = button.getAttribute("data-show-text");
          }
        });
      });

      document.getElementById("deleteUserForm").addEventListener("submit", function(event) {
        if (!confirm("Видалення користувача призведе до видалення всіх ваших рахунків. Ви впевнені?")) {
          event.preventDefault();
        }
      });
    });
  </script>
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
          ${account.balance} ${account.currency}
        </c:otherwise>
      </c:choose>
      <button class="toggleFormButton" data-form-id="depositForm-${account.type}" data-show-text="Додати гроші">Додати гроші</button>
      <div id="depositForm-${account.type}" style="display: none;">
        <form action="deposit" method="post" style="display:inline;">
          <input type="hidden" name="accountType" value="${account.type}">
          <input type="hidden" name="currency" value="${account.currency}">
          <input type="number" name="amount" placeholder="Сума" required>
          <button type="submit">Додати гроші</button>
        </form>
      </div>
      <button class="toggleFormButton" data-form-id="withdrawForm-${account.type}" data-show-text="Зняти гроші">Зняти гроші</button>
      <div id="withdrawForm-${account.type}" style="display: none;">
        <form action="withdraw" method="post" style="display:inline;">
          <input type="hidden" name="accountType" value="${account.type}">
          <input type="hidden" name="currency" value="${account.currency}">
          <input type="number" name="amount" placeholder="Сума" required>
          <button type="submit">Зняти гроші</button>
        </form>
      </div>
    </li>
  </c:forEach>
</ul>

<button id="showFormButton">Додати рахунок</button>
<div id="addAccountForm" style="display: none;">
  <form action="addAccount" method="post">
    <label for="type">Ім'я рахунку:</label>
    <input type="text" id="type" name="type" required>
    <label for="balance">Баланс:</label>
    <input type="number" id="balance" name="balance" required>
    <label for="currency">Валюта:</label>
    <input type="text" id="currency" name="currency" pattern="[A-Z]{3}" title="Валюта повинна складатися з 3 великих літер" required>
    <button type="submit">Додати рахунок</button>
  </form>
</div>

<form method="get" action="logout">
  <button type="submit">Вийти</button>
</form>

<form id="deleteUserForm" method="post" action="deleteUser">
  <button type="submit">Видалити користувача</button>
</form>
</body>
</html>