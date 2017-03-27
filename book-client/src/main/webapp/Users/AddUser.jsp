<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
</head>
<body>
<h1>Add user</h1>

<c:choose>
    <%--@elvariable id="user" type="com.igels.book.entity.UserInfo"--%>
    <c:when test="${empty user}">
        <form method="POST" accept-charset="UTF-8">
            <table>
                <tr>
                    <td><i>RoleId:</i></td>
                    <td><input name="role_id" title=""/></td>
                </tr>
                <tr>
                    <td><i>Name:</i></td>
                    <td><input name="name" title=""/></td>
                </tr>
                <tr>
                    <td><i>Surname:</i></td>
                    <td><input name="surname" title=""/></td>
                </tr>
                <tr>
                    <td><i>Email:</i></td>
                    <td><input name="email" title=""/></td>
                </tr>
                <tr>
                    <td><i>Password:</i></td>
                    <td><input name="password" title=""/></td>
                </tr>

                <input type="hidden" name="RequestType" value="PUT">
            </table>
            <br/>
            <input type="submit" value="Create"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>
            New user added.
        </p>
    </c:otherwise>
</c:choose>

<br/>
<button onclick="location.href='/book-client/Users/Users'" type="button">Back</button>
</body>
</html>