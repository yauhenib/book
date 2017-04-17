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
    <title>Roles</title>
</head>
<body>
<h1>Roles</h1>
<table>
    <thead>
    <tr bgcolor="gray">
        <td width="30">Id</td>
        <td width="50">Name</td>
        <td width="40"></td>
        <td width="40"></td>
    </tr>
    </thead>
    <tbody>

    <%--@elvariable id="roles" type="java.util.List"--%>
    <c:forEach var="role" items="${roles}">
    <tr>
        <td><a href="/book-client/Roles/Roles/${role.id}">${role.id}</a></td>
        <td><a>${role.name}</a></td>
        <td>
            <button onclick="location.href='/book-client/Roles/EditRole/${role.id}'" type="button">Edit</button>
        </td>
        <td>
            <button onclick="location.href='/book-client/Roles/DeleteRole/${role.id}'" type="button">Delete</button>
        </td>
        </c:forEach>
    </tbody>
</table>
<br/>
<button onclick="location.href='/book-client/Roles/AddRole'" type="button">New role</button>
<br/>
<br/>
<button onclick="location.href='/book-client'" type="button">Back</button>
</body>
</html>
