
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Students</title>
    <link rel="stylesheet" type="text/css" href="css/ViewStudentsServlet.css">
</head>
<body>
    <h1>View Students</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Username</th>
            <th>Action</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.gender}</td>
                <td>${student.username}</td>
                <td>
                    <a href="EditDeleteStudentServlet?action=edit&studentId=${student.id}">Edit</a>
                    <a href="EditDeleteStudentServlet?action=delete&studentId=${student.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
