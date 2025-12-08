<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item for Sale</title>
</head>
<body>
    <h1>Add Item for Sale</h1>
    
    <s:if test="%{#session.loggedInUser != null}">
        <p>Logged in as: <strong><s:property value="#session.loggedInUser" /></strong></p>
        <p><a href="viewItems">Home</a> | <a href="logout">Logout</a></p>
    </s:if>
    
    <hr>
    
    <s:form action="addItem">
        <s:textfield name="itemName" label="Item Name" required="true"/>
        <s:textarea name="description" label="Description" rows="4" cols="50"/>
        <s:textfield name="price" label="Price (€)" required="true"/>
        <s:submit value="Add Item"/>
    </s:form>
    
    <s:if test="hasActionErrors()">
        <div style="color:red;">
            <s:actionerror/>
        </div>
    </s:if>
</body>
</html>