<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>User Registration</h1>
    
    <s:form action="register">
        <s:textfield name="username" label="Username" required="true"/>
        <s:password name="password" label="Password" required="true"/>
        <s:textfield name="email" label="Email"/>
        <s:textfield name="fullName" label="Full Name"/>
        <s:submit value="Register"/>
    </s:form>
    
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
    
    <s:if test="hasActionErrors()">
        <div style="color:red;">
            <s:actionerror/>
        </div>
    </s:if>
</body>
</html>