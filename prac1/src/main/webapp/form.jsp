<%@ include file="header.jsp" %>

<h2>Form Submission</h2>
<form action="formResult.jsp" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="hobbies">Select your hobbies:</label><br>
    <input type="checkbox" id="hobby1" name="hobbies" value="Reading">
    <label for="hobby1">Reading</label><br>

    <input type="checkbox" id="hobby2" name="hobbies" value="Traveling">
    <label for="hobby2">Traveling</label><br>

    <input type="checkbox" id="hobby3" name="hobbies" value="Cooking">
    <label for="hobby3">Cooking</label><br>

    <input type="checkbox" id="hobby4" name="hobbies" value="Sports">
    <label for="hobby4">Sports</label><br><br>

    <label for="food">Select your favorite foods:</label><br>
    <select id="food" name="foods" multiple>
        <option value="Pizza">Pizza</option>
        <option value="Burger">Burger</option>
        <option value="Sushi">Sushi</option>
        <option value="Pasta">Pasta</option>
    </select><br><br>

    <button type="submit">Submit</button>
</form>

<%@ include file="footer.jsp" %>
