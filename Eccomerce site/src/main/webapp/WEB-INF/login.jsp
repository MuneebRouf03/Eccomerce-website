<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>User Login</h1>
    
    <s:form action="login">
        <s:textfield name="username" label="Username" required="true"/>
        <s:password name="password" label="Password" required="true"/>
        <s:submit value="Login"/>
    </s:form>
    
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    
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