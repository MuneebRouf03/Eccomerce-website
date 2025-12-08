<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Register</title></head>
<body>
    <h1>Register</h1>
    
    <script>
    function validateRegister() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        
        if(username.length < 3) {
            alert("Username must be at least 3 characters");
            return false;
        }
        
        if(password.length < 3) {
            alert("Password must be at least 3 characters");
            return false;
        }
        
        return true;
    }
    </script>
    
    <s:form action="register" onsubmit="return validateRegister()">
        <s:textfield id="username" name="username" label="Username"/>
        <s:password id="password" name="password" label="Password"/>
        <s:submit value="Register"/>
    </s:form>
    
    <p><a href="login.jsp">Login instead</a></p>
</body>
</html>