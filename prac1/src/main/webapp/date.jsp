<%@ page import="java.util.*, java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Date and Age Calculator</title>
</head>
<body>
<h1>Welcome to the Age Calculator</h1>

<%
    Date currentDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String formattedDate = sdf.format(currentDate);
%>
<p>Current Date and Time: <strong><%= formattedDate %></strong></p>

<form action="date.jsp" method="post">
    <label for="dob">Enter your date of birth (yyyy-mm-dd):</label>
    <input type="date" id="dob" name="dob" required>
    <button type="submit">Calculate Days Lived</button>
</form>


<%
    String days = "";
    // Перевірка, чи була введена дата народження
    String dob = request.getParameter("dob");
    if (dob != null && !dob.isEmpty()) {
//        PrintWriter date = response.getWriter();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = dateFormat.parse(dob);

            long diffInMillies = currentDate.getTime() - birthDate.getTime();
            long daysLived = diffInMillies / (1000 * 60 * 60 * 24);

            days = ("<p>You have lived for <strong>" + daysLived + "</strong> days.</p>");
        } catch (Exception e) {
            days = ("<p>Error parsing the date. Please enter a valid date of birth.</p>");
        }
    }
%>
<%= days %>
</body>
</html>
