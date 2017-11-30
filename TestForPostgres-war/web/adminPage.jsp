<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.user.fname}!</h1>
        <br/>
        <h2>Users table</h2>
        <br/>
        <table border="1">
            <tr>
                <th>id</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>login</th>
                <th>password</th>
                <th>role</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            <c:forEach var="u" items="${sessionScope.usersList}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.fname}</td>
                    <td>${u.lname}</td>
                    <td>${u.login}</td>
                    <td>${u.password}</td>
                    <td>${u.idRole.role}</td>
                    <td><a href="edit?id=${u.id}">edit</a></td>
                    <td><a href="delete?id=${u.id}">delete</a></td>
                </tr>
            </c:forEach>
                <tr><td colspan="8" style="text-align: center"><a href="add.jsp">add new user</a></td></tr>
        </table>
    </body>
</html>
