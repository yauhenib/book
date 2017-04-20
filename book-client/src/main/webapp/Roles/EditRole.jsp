<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit role</title>
</head>
<body>
<h1>Edit role</h1>

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

<script>

$(document).ready
(
     function(){

       $("#editRoleForm").validate(
                 { // initialize the plugin
                          rules: {
                                name: {
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
                                }
                          }
                  });
     });
</script>

<c:choose>
    <%--@elvariable id="role" type="com.igels.book.entity.RoleInfo"--%>
    <c:when test="${not empty role.name}">
        <form id="editRoleForm" method="POST" action="" accept-charset="UTF-8">
            <table>
                <thead>
                <tr bgcolor="gray">
                    <td width="50">Name</td>
                 </tr>
                </thead>
                <tbody>
                <tr>
                    <td><a><input id="name" name="role_name" value="${role.name}" title=""/></a></td>
                    <input type="hidden" name="RequestType" value="POST" title="">
                </tbody>
            </table>
            <br/>
            <input type="submit" value="Save"/>
        </form>
        <br/>
    </c:when>
    <c:otherwise>
        <p>
            Role saved.
        </p>
    </c:otherwise>
</c:choose>

<button onclick="location.href='/book-client/Roles/Roles'" type="button">Back</button>
</body>
</html>
