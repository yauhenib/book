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


<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<script>

$(document).ready
(
     function(){

       $("#editUserForm").validate(
                 { // initialize the plugin
                          rules: {
                                name: {
                                    required: true,
                                    minlength: 2,
                                    maxlength: 20,
                                },
                                surname: {
                                    required: true,
                                    minlength: 2,
                                    maxlength: 20,
                                },
                                email: {
                                    required: true,
                                    email: true,
                                    minlength: 2,
                                    maxlength: 20,
                                },
                                password: {
                                    required: true,
                                    minlength: 2,
                                    maxlength: 20,
                                }
                          },
                          messages: {
                                name: {
                                    required: "<li>Please enter a name.</li>",
                                    minlength: "<li>Your name is not long enough.</li>",
                                    maxlength: "<li>Your name is too long.</li>"
                                },
                                surname: {
                                    required: "<li>Please enter a surname.</li>",
                                    minlength: "<li>Your surname is not long enough.</li>",
                                    maxlength: "<li>Your surname is too long.</li>"
                                },
                                email: {
                                    required: "<li>Please enter a email.</li>",
                                    minlength: "<li>Your email is not long enough.</li>",
                                    maxlength: "<li>Your email is too long.</li>"
                                },
                                password: {
                                    required: "<li>Please enter a password.</li>",
                                    minlength: "<li>Your email is not long enough.</li>",
                                    maxlength: "<li>Your email is too long.</li>"
                                }
                          }
                  });
     });
</script>

<c:choose>
    <%--@elvariable id="user" type="com.igels.book.entity.UserInfo"--%>
    <c:when test="${not empty user.name}">
        <form id="editUserForm" method="POST" action="" accept-charset="UTF-8">
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
                    <td><a><input id="name" name="name" value="${user.name}" title=""/></a></td>
                    <td><a><input id="surname" name="surname" value="${user.surname}" title=""/></a></td>
                    <td><a><input id="email" name="email" value="${user.email}" title=""/></a></td>
                    <td><a><input id="password" name="password" value="${user.password}" title=""/></a></td>
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
