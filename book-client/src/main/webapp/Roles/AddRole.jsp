<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add role</title>
</head>
<body>
<h1>Add role</h1>

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<script>

$(document).ready
(
     function(){

       $("#addRoleForm").validate(
                 { // initialize the plugin
                          rules: {
                                role_name: {
                                    required: true,
                                    minlength: 2,
                                    maxlength: 20,
                                }
                          },
                          messages: {
                                role_name: {
                                    required: "<li>Please enter a name.</li>",
                                    minlength: "<li>Your name is not long enough.</li>",
                                    maxlength: "<li>Your name is too long.</li>"
                                }
                          }
                  });
     });
</script>

<c:choose>
    <%--@elvariable id="role" type="com.igels.book.entity.RoleInfo"--%>
    <c:when test="${empty role}">
        <form id="addRoleForm" method="POST" accept-charset="UTF-8">
            <table>
                <tr>
                    <td><i>Name:</i></td>
                    <td><input id="role_name" name="role_name" title=""/></td>
                </tr>
                <input type="hidden" name="RequestType" value="PUT">
            </table>
            <br/>
            <input type="submit" value="Create"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>
            New role added.
        </p>
    </c:otherwise>
</c:choose>

<br/>
<button onclick="location.href='/book-client/Roles/Roles'" type="button">Back</button>
</body>
</html>