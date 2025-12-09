<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>eCommerce Website</title>
</head>
<body>
    <h1>Welcome to eCommerce Site</h1>
    
    <s:if test="%{#session.loggedInUser != null}">
        <p>Logged in as: <strong><s:property value="#session.loggedInUser" /></strong></p>
        <p><a href="viewItems">Home</a> | <a href="logout">Logout</a></p>
    </s:if>
    <s:else>
        <p><a href="login.jsp">Login</a> | <a href="register.jsp">Register</a></p>
    </s:else>
    
    <hr>
    
    <h2>Site Navigation:</h2>
    <ul>
        <s:if test="%{#session.loggedInUser != null}">
            <li><a href="addItem.jsp">Add Item for Sale</a></li>
            <li><a href="viewItems">View All Items for Sale</a></li>
            <li><a href="viewMyBids">View My Bids</a></li>
            <li><a href="viewAllUsers">View All Users</a></li>
        </s:if>
        <s:else>
            <li><a href="viewItems">View Items for Sale</a></li>
            <li><a href="login.jsp">Login to access more features</a></li>
        </s:else>
    </ul>
    
    <s:if test="hasActionMessages()">
        <div style="color:green;">
            <s:actionmessage/>
        </div>
    </s:if>
    
    <s:if test="hasActionErrors()">
        <div style="color:red;">
            <s:actionerror/>
        </div>
    </s:if>
</body>
</html>