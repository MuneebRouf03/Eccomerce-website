<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>All Users</title></head>
<body>
    <h1>All Users</h1>
    
    <p><a href="viewItems">Back to Items</a></p>
    
    <s:if test="users != null && !users.isEmpty()">
        <table border="1">
            <tr><th>Username</th></tr>
            
            <s:iterator value="users">
                <tr>
                    <td><s:property value="username"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:else>
        <p>No users found.</p>
    </s:else>
</body>
</html>