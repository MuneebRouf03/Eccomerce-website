<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bids for Item</title>
</head>
<body>
    <h1>All Bids for This Item</h1>
    
    <p><a href="viewItems">Back to Items</a></p>
    
    <hr>
    
    <s:if test="bids != null && !bids.isEmpty()">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Bidder</th>
                <th>Bid Amount (€)</th>
                <th>Bid Time</th>
            </tr>
            
            <s:iterator value="bids">
                <tr>
                    <td><s:property value="bidderName"/></td>
                    <td>€<s:property value="bidAmount"/></td>
                    <td><s:property value="bidTime"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:else>
        <p>No bids placed for this item yet.</p>
    </s:else>
</body>
</html>