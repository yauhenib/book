<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
</head>
<body>
<h1>Add user</h1>

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<script>

$(document).ready
(
     function(){

       $("#addUserForm").validate(
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
    <c:when test="${empty user}">
        <form id="addUserForm" method="POST" accept-charset="UTF-8"">
            <table>
                <tr>
                    <td><i>RoleId:</i></td>
                    <td><input name="role_id" title=""/></td>
                </tr>
                <tr>
                    <td><i>Name:</i></td>
                    <td><input id="name" name="name" title=""/></td>
                </tr>
                <tr>
                    <td><i>Surname:</i></td>
                    <td><input id="surname" name="surname" title=""/></td>
                </tr>
                <tr>
                    <td><i>Email:</i></td>
                    <td><input id="email" name="email" title=""/></td>
                </tr>
                <tr>
                    <td><i>Password:</i></td>
                    <td><input id="password" name="password" title="" type="password"/></td>
                </tr>

                <input type="hidden" name="RequestType" value="PUT">
            </table>
            <br/>
            <input type="submit" value="Create"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>
            New user added.
        </p>
    </c:otherwise>
</c:choose>

<br/>
<button onclick="location.href='/book-client/Users/Users'" type="button">Back</button>
</body>
</html>