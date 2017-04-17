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
        <td width="30">Role</td>
        <td width="50">Name</td>
        <td width="50">Surname</td>
        <td width="70">Email</td>
        <td width="70">Created</td>
        <td width="40"></td>
        <td width="40"></td>
    </tr>
    </thead>
    <tbody>
 
    <%--@elvariable id="users" type="java.util.List"--%>
    <c:forEach var="pair" items="${users}">
    <tr>
        <td><a href="/book-client/Users/User/${pair.key.id}">${pair.key.id}</a></td>
        <td><a>${pair.value.name}</a></td>
        <td><a>${pair.key.name}</a></td>
        <td><a>${pair.key.surname}</a></td>
        <td><a>${pair.key.email}</a></td>
        <td><a>${pair.key.created}</a></td>
        <td>
            <button onclick="location.href='/book-client/Users/EditUser/${pair.key.id}'" type="button">Edit</button>
        </td>
        <td>
            <button onclick="location.href='/book-client/Users/DeleteUser/${pair.key.id}'" type="button">Delete</button>
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
