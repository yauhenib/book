<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit role</title>
</head>
<body>
<h1>Edit role</h1>

<c:choose>
    <%--@elvariable id="role" type="com.igels.book.entity.RoleInfo"--%>
    <c:when test="${not empty role.name}">
        <form method="POST" action="" accept-charset="UTF-8">
            <table>
                <thead>
                <tr bgcolor="gray">
                    <td width="50">Name</td>
                 </tr>
                </thead>
                <tbody>
                <tr>
                    <td><a><input name="role_name" value="${role.name}" title=""/></a></td>
                    <input type="hidden" name="RequestType" value="POST" title="">
                </tbody>
            </table>
            <br/>
            <input type="submit" value="Save"/>
        </form>
        <br/>
    </c:when>
    <c:otherwise>
        <p>
            Role saved.
        </p>
    </c:otherwise>
</c:choose>

<button onclick="location.href='/book-client/Roles/Roles'" type="button">Back</button>
</body>
</html>
