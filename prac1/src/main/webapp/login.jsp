<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required /><br/>
    <label>Password:</label>
    <input type="password" name="password" required /><br/>
    <input type="submit" value="Login" />
</form>

<% if ("invalid".equals(request.getParameter("error"))) { %>
<p style="color:red;">Invalid username or password.</p>
<% } %>
</body>
</html>