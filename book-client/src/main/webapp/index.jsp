<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
</head>
<body>
<h1>Books Web Application</h1>
<table>
    <tr>
        <th>
            <p><a href="Users/Users">Users</a></p>
        </th>
    </tr>
    <tr>
        <td>
            <p><a href="Roles">Roles</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="RolesPermissions">RolesPermission</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="Books">Books</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="Vendors">Vendors</a></p>
        </td>
    </tr>
    <tr>
        <td>
            <p><a href="VendorsTransactions">VendorsTransactions</a></p>
        </td>
    </tr>
</table>
</body>
</html>