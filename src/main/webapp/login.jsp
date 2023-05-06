<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    
</head>
<body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Login">
    </form>

    <p><strong>${param.signupSuccess}</strong></p>
    
    <p>Don't have an account? <a href="signup.jsp">Signup</a></p>
</body>
</html>
