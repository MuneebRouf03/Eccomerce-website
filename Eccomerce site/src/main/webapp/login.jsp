<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Login</title></head>
<body>
    <h1>Login</h1>
    
    <script>
    function showWelcome() {
        alert("Welcome! Please enter your credentials.");
    }
    
    // Call when page loads
    window.onload = showWelcome;
    </script>
    
    <s:form action="login">
        <s:textfield name="username" label="Username"/>
        <s:password name="password" label="Password"/>
        <s:submit value="Login"/>
    </s:form>
    
    <p><a href="register.jsp">Register instead</a></p>
</body>
</html>