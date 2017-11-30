
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD</title>
    </head>
    <body>
        <h1>Add new user!</h1>
        <br/>
        <form action="add" method="post">
            <label>First name:</label>
            <input name="fname" type="text"/>
            <br/>
            <label>Last name:</label>
            <input name="lname" type="text"/>
            <br/> 
            <label>Login:</label>
            <input name="login" type="text"/>
            <br/>
            <label>Password:</label>
            <input name="password" type="password"/>
            <br/>
            <label>Role:</label>
            <select name="idRole">
                <c:forEach var="r" items="${sessionScope.rolesList}">
                    <option value="${r.id}">${r.role}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
