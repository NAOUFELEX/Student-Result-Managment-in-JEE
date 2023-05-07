
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
            <link rel="stylesheet" type="text/css" href="css/login.css">
    
</head>
<body>
    <h1>Signup</h1>
    <%-- Display error message if registration fails --%>
    <% String error = request.getParameter("error");
       if (error != null && error.equals("registration")) { %>
        <p>Error: Registration failed. Please try again.</p>
    <% } else if (error != null && error.equals("database")) { %>
        <p>Error: Database connection failed. Please try again later.</p>
    <% } %>
    
    <%-- Signup form --%>
    <form action="SignupServlet" method="post">
        <input type="radio" name="userType" value="teacher" checked>Teacher
        <input type="radio" name="userType" value="student">Student
        <br><br>
        First Name: <input type="text" name="firstname" required>
        <br><br>
        Last Name: <input type="text" name="lastname" required>
        <br><br>
        Gender: <input type="text" name="gender" required>
        <br><br>
        Username: <input type="text" name="username" required>
        <br><br>
        Password: <input type="password" name="password" required>
        <br><br>
        <input type="submit" value="Signup">
    </form>
</body>
</html>
