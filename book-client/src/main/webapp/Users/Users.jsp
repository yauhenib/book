<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>Users</h1>
<table>
    <thead>
    <tr bgcolor="gray">
        <td width="30">Id</td>
        <td width="30">RoleId</td>
        <td width="50">Name</td>
        <td width="50">Surname</td>
        <td width="70">Email</td>
        <td width="70">Password</td>
        <td width="70">Created</td>
        <td width="40"></td>
        <td width="40"></td>
    </tr>
    </thead>
    <tbody>
 
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><a href="/book-client/Users/User/${user.id}">${user.id}</a></td>
        <td><a>${user.roleId}</a></td>
        <td><a>${user.name}</a></td>
        <td><a>${user.surname}</a></td>
        <td><a>${user.email}</a></td>
        <td><a>${user.password}</a></td>
        <td><a>${user.created}</a></td>
        <td>
            <button onclick="location.href='/book-client/Users/EditUser/${user.id}'" type="button">Edit</button>
        </td>
        <td>
            <button onclick="location.href='/book-client/Users/DeleteUser/${user.id}'" type="button">Delete</button>
        </td>
        </c:forEach>
    </tbody>
</table>
<br/>
<button onclick="location.href='/book-client/Users/AddUser'" type="button">New user</button>
<br/>
<br/>
<button onclick="location.href='/book-client'" type="button">Back</button>
</body>
</html>
