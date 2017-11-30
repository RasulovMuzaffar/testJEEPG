
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <h1>Change user data!</h1>
        <br/>
        <form action="edit" method="post">
            <label>First name:</label>
            <input name="fname" type="text" value="${u.fname}"/>
            <br/>
            <label>Last name:</label>
            <input name="lname" type="text" value="${u.lname}"/>
            <br/> 
            <label>Login:</label>
            <input name="login" type="text" value="${u.login}"/>
            <br/>
            <label>Password:</label>
            <input name="password" type="password" value="${u.password}"/>
            <br/>
            <label>Role:</label>
            <select name="idRole">
                <c:forEach var="r" items="${sessionScope.rolesList}">
                    <c:if test="${r.id == u.idRole.id}">
                        <option value="${r.id}" selected="selected">${r.role}</option>
                    </c:if>
                    <c:if test="${r.id != u.idRole.id}">
                        <option value="${r.id}">${r.role}</option>
                    </c:if>
                </c:forEach>
            </select>
            <br/>
            <input type="hidden" name="id" value="${u.id}"/>
            <input type="submit" value="submit"/>
        </form>
    </body>
</html>
