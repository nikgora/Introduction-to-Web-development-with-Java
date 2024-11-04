<!-- register.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Реєстрація</title>
</head>
<body>
<h2>Реєстрація користувача</h2>
<form action="RegisterServlet" method="post">
    Логін: <input type="text" name="username" required/><br/>
    Пароль: <input type="password" name="password" required/><br/>
    Email: <input type="email" name="email" required/><br/>
    <input type="submit" value="Зареєструватися"/>
</form>
</body>
</html>
