<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>My Bids</title></head>
<body>
   <h1>My Bids</h1>
<p><a href="viewItems">Back to Items</a></p>
<s:if test="bids != null && !bids.isEmpty()">
        <table border="1">
            <tr>
                <th>Item</th>
                <th>Bid Amount</th>
            </tr>
            <s:iterator value="bids">
                <tr>
                    <td><s:property value="itemName"/></td>
                    <td>€<s:property value="amount"/></td>
                </tr>
            </s:iterator>
        </table>
</s:if>
<s:else>
    <p>No bids placed yet.</p>
</s:else>
</body>
</html>/html>