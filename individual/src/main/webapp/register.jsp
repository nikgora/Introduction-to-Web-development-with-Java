<!-- register.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Реєстрація</title>
</head>
<body>
<h2>Реєстрація користувача</h2>
<c:if test="${not empty errors}">
    <div style="color: red;">
        <ul>
            <c:forEach var="error" items="${errors}">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required/><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required/><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required/><br>

    <input type="submit" value="Register">
</form>
<form action="login.jsp" method="get">
    <button type="submit">Увійти</button>
</form>
</body>
</html>
