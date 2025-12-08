<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bids</title>
</head>
<body>
    <h1>My Bids</h1>
    
    <s:if test="%{#session.loggedInUser != null}">
        <p>Logged in as: <strong><s:property value="#session.loggedInUser" /></strong></p>
        <p><a href="viewItems">Home</a> | <a href="logout">Logout</a></p>
    </s:if>
    
    <hr>
    
    <s:if test="bids != null && !bids.isEmpty()">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Item Name</th>
                <th>Bid Amount (€)</th>
                <th>Bid Time</th>
            </tr>
            
            <s:iterator value="bids">
                <tr>
                    <td><s:property value="itemName"/></td>
                    <td>€<s:property value="bidAmount"/></td>
                    <td><s:property value="bidTime"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:else>
        <p>You haven't placed any bids yet.</p>
    </s:else>
    
    <s:if test="hasActionErrors()">
        <div style="color:red;">
            <s:actionerror/>
        </div>
    </s:if>
</body>
</html>