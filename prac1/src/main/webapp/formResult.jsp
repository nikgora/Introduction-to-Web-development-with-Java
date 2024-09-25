<%@ include file="header.jsp" %>

<h2>Form Submission Results</h2>
<%
    String name = request.getParameter("name");

    String[] hobbies = request.getParameterValues("hobbies");

    String[] foods = request.getParameterValues("foods");


    out.println("<p><strong>Name:</strong> " + name + "</p>");

    if (hobbies != null && hobbies.length > 0) {
        out.println("<p><strong>Your hobbies:</strong></p>");
        out.println("<ul>");
        for (String hobby : hobbies) {
            out.println("<li>" + hobby + "</li>");
        }
        out.println("</ul>");
    } else {
        out.println("<p>You have not selected any hobbies.</p>");
    }

    if (foods != null && foods.length > 0) {
        out.println("<p><strong>Your favorite foods:</strong></p>");
        out.println("<ul>");
        for (String food : foods) {
            out.println("<li>" + food + "</li>");
        }
        out.println("</ul>");
    } else {
        out.println("<p>You have not selected any favorite foods.</p>");
    }
%>

<%@ include file="footer.jsp" %>
