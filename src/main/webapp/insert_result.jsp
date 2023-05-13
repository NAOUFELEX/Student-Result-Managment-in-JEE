<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert Result</title>
            <link rel="stylesheet" type="text/css" href="css/Result.css">
    
</head>
<body>
    <h1>Insert Result</h1>
    <form action="InsertResultServlet" method="post">
        <label for="studentId">Student ID:</label>
        <input type="text" name="studentId" id="studentId" required><br><br>
        
        <label for="courseId">Course Nmae:</label>
        <input type="text" name="courseId" id="courseId" required><br><br>
        
        <label for="result">Result:</label>
        <input type="text" name="result" id="result" required><br><br>
        
        <input type="submit" value="Submit">
    </form>
</body>
</html>
