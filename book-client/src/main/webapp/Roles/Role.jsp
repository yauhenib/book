<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Role</title>
</head>
<body>
<h1>Role</h1>
<%--@elvariable id="role" type="com.igels.book.entity.RoleInfo"--%>
<p>
    Id: <c:out value="${role.id}"/>
</p>
<p>
    Name: <c:out value="${role.name}"/>
</p>
<br/>
<button onclick="location.href='/book-client/Roles/EditRole/${role.id}'" type="button">Edit</button>
<button onclick="location.href='/book-client/Roles/DeleteRole/${role.id}'" type="button">Delete</button>

<br/>
<br/>
<button onclick="location.href='/book-client/Roles/Roles'" type="button">Back</button>
</body>
</html>
</html>