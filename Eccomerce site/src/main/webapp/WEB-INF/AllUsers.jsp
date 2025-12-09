<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <h1>All Registered Users</h1>
    
    <p><a href="viewItems">Back to Items</a></p>
    
    <hr>
    
    <s:if test="users != null && !users.isEmpty()">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Username</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Joined</th>
            </tr>
            
            <s:iterator value="users">
                <tr>
                    <td><s:property value="username"/></td>
                    <td><s:property value="fullName"/></td>
                    <td><s:property value="email"/></td>
                    <td><s:property value="createdAt"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:else>
        <p>No users found.</p>
    </s:else>
</body>
</html>