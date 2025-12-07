<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Home Page</title></head>
<body>
    <h1>eCommerce Website</h1>
    
    <%-- Jenny's style: Simple scriptlet for logic --%>
    <%
        if (session.getAttribute("loggedInUser") != null) {
    %>
        <p>Logged in as: <%= session.getAttribute("loggedInUser") %></p>
        <p><a href="logout">Logout</a></p>
    <%
        } else {
    %>
        <p><a href="login.jsp">Login</a> | <a href="register.jsp">Register</a></p>
    <%
        }
    %>
    
    <%-- Jenny's style: Basic links --%>
    <h3>Navigation:</h3>
    <ul>
        <li><a href="viewItems">View Items</a></li>
        <%
            if (session.getAttribute("loggedInUser") != null) {
        %>
            <li><a href="addItem.jsp">Add Item</a></li>
            <li><a href="viewMyBids">My Bids</a></li>
            <li><a href="viewAllUsers">All Users</a></li>
        <%
            }
        %>
    </ul>
</body>
</html>