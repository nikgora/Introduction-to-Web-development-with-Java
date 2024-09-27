<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
  <title>Index Page</title>
</head>
<body>
<h1>Welcome to the servlet example!</h1>
<form action="hello" method="get">
  <label for="name">Enter your name:</label>
  <input type="text" id="name" name="name">
  <button type="submit">Submit</button>
</form>
<h2>Conditional Tags Example</h2>
<%
  int number = 5;
  request.setAttribute("number", number);
%>

<c:if test="${number > 10}">
  <p>The number is greater than 10.</p>
</c:if>
<c:if test="${number <= 10}">
  <p>The number is less than or equal to 10.</p>
</c:if>

<h2>Iterating Over a List Example</h2>
<%
  List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Grapes");
  request.setAttribute("fruitsList", fruits);
%>

<ul>
  <c:forEach var="fruit" items="${fruitsList}">
    <li>${fruit}</li>
  </c:forEach>
</ul>

<h2>Date and Time Formatting</h2>
<%
  Date currentDate = new Date();
  request.setAttribute("currentDate", currentDate);
%>

<p>Current Date and Time (formatted):
  <fmt:formatDate value="${currentDate}" type="both" dateStyle="full" timeStyle="short"/>
</p>

<h2>Currency Formatting</h2>
<%
  double price = 12345.67;
  request.setAttribute("price", price);
%>

<p>Formatted Price:
  <fmt:formatNumber value="${price}" type="currency" currencySymbol="$"/>
</p>

<h2>Number Formatting</h2>
<%
  double largeNumber = 12345678.9123;
  request.setAttribute("largeNumber", largeNumber);
%>

<p>Formatted Number:
  <fmt:formatNumber value="${largeNumber}" type="number" maxFractionDigits="2"/>
</p>
<a href="<%= request.getContextPath() %>/logout">Logout</a>

</body>
</html>