<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit user</title>
</head>
<body>
<h1>Edit user</h1>

<c:choose>
    <%--@elvariable id="user" type="com.igels.book.entity.UserInfo"--%>
    <c:when test="${not empty user.name}">
        <form method="POST" action="" accept-charset="UTF-8">
            <table>
                <thead>
                <tr bgcolor="gray">
                    <td width="30">Role</td>
                    <td width="50">Name</td>
                    <td width="50">Surname</td>
                    <td width="70">Email</td>
                    <td width="70">Password</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                    <a>
                        <select name='role_id'>
                            <c:forEach var="role" items="${roles}">
                                <c:if test="${role.id != user.roleId}">
                                    <option value="${role.id}">${role.name}</option>
                                </c:if>
                                <c:if test="${role.id == user.roleId}">
                                    <option value="${role.id}" selected>${role.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </a>
                    </td>
                    <td><a><input name="name" value="${user.name}" title=""/></a></td>
                    <td><a><input name="surname" value="${user.surname}" title=""/></a></td>
                    <td><a><input name="email" value="${user.email}" title=""/></a></td>
                    <td><a><input name="password" value="${user.password}" title=""/></a></td>
                    <td><a><input type="hidden" name="RequestType" value="POST" title=""></a></td>
                </tbody>
            </table>
            <br/>
            <input type="submit" value="Save"/>
        </form>
        <br/>
    </c:when>
    <c:otherwise>
        <p>
            User saved.
        </p>
    </c:otherwise>
</c:choose>

<button onclick="location.href='/book-client/Users/Users'" type="button">Back</button>
</body>
</html>
