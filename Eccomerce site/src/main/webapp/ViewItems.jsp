<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Items for Sale</title>
</head>
<body>
    <h1>Items for Sale</h1>
    
    <s:if test="%{#session.loggedInUser != null}">
        <p>Logged in as: <strong><s:property value="#session.loggedInUser" /></strong></p>
        <p><a href="addItem.jsp">Add New Item</a> | <a href="viewMyBids">My Bids</a> | <a href="viewAllUsers">All Users</a> | <a href="logout">Logout</a></p>
    </s:if>
    <s:else>
        <p><a href="login.jsp">Login to Bid</a> | <a href="register.jsp">Register</a></p>
    </s:else>
    
    <hr>
    
    <s:if test="items != null && !items.isEmpty()">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Item Name</th>
                <th>Description</th>
                <th>Price (€)</th>
                <th>Seller</th>
                <s:if test="%{#session.loggedInUser != null}">
                    <th>Action</th>
                </s:if>
            </tr>
            
            <s:iterator value="items">
                <tr>
                    <td><s:property value="itemName"/></td>
                    <td><s:property value="description"/></td>
                    <td>€<s:property value="price"/></td>
                    <td><s:property value="sellerName"/></td>
                    <s:if test="%{#session.loggedInUser != null}">
                        <td>
                            <s:form action="makeBid" method="post" style="display:inline;">
                                <s:hidden name="itemId" value="%{itemId}"/>
                                <s:textfield name="bidAmount" size="10" value="%{price + 1}" label=""/>
                                <s:submit value="Bid"/>
                            </s:form>
                            <a href="viewItemBids?itemId=<s:property value='itemId'/>">View Bids</a>
                        </td>
                    </s:if>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    <s:else>
        <p>No items available for sale.</p>
    </s:else>
    
    <s:if test="hasActionErrors()">
        <div style="color:red;">
            <s:actionerror/>
        </div>
    </s:if>
    
    <s:if test="hasActionMessages()">
        <div style="color:green;">
            <s:actionmessage/>
        </div>
    </s:if>
</body>
</html>