
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
        <link rel="stylesheet" type="text/css" href="css/Dashboard.css">
    
</head>
<body>
    <h2>Welcome, Student!</h2>
    <p>This is your dashboard.</p>

    <h3>Your Result:</h3>
    <table>
        <thead>
            <tr>
                <th>Subject</th>
                <th>Score</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="viewResult" items="${viewResult}">
                <tr>
                    <td>${viewResult.subject}</td>
                    <td>${viewResult.score}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
