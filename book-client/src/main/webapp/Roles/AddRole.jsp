<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add role</title>
</head>
<body>
<h1>Add role</h1>

<c:choose>
    <%--@elvariable id="role" type="com.igels.book.entity.RoleInfo"--%>
    <c:when test="${empty role}">
        <form method="POST" accept-charset="UTF-8">
            <table>
                <tr>
                    <td><i>Name:</i></td>
                    <td><input name="role_name" title=""/></td>
                </tr>
                <input type="hidden" name="RequestType" value="PUT">
            </table>
            <br/>
            <input type="submit" value="Create"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>
            New role added.
        </p>
    </c:otherwise>
</c:choose>

<br/>
<button onclick="location.href='/book-client/Roles/Roles'" type="button">Back</button>
</body>
</html>