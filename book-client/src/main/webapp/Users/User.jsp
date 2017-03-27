<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<h1>User</h1>
<%--@elvariable id="user" type="com.igels.book.entity.UserInfo"--%>
<p>
    Id: <c:out value="${user.id}"/>
</p>
<p>
    RoleId: <c:out value="${user.roleId}"/>
</p>
<p>
    Name: <c:out value="${user.name}"/>
</p>
<p>
    Surname: <c:out value="${user.surname}"/>
</p>
<p>
    Email: <c:out value="${user.email}"/>
</p>
<p>
    Password: <c:out value="${user.password}"/>
</p>
<p>
    Created: <c:out value="${user.created}"/>
</p>
<br/>
<button onclick="location.href='/book-client/Users/EditUser/${user.id}'" type="button">Edit</button>
<button onclick="location.href='/book-client/Users/DeleteUser/${user.id}'" type="button">Delete</button>

<br/>
<br/>
<button onclick="location.href='/book-client/Users/Users'" type="button">Back</button>
</body>
</html>