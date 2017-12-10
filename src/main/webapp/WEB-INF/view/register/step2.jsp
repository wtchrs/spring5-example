<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Enter member information</h2>
<form:form action="step3" modelAttribute="formData">
    <p>
        <label>Email:<br>
            <form:input path="email" />
        </label>
    </p>
    <p>
        <label>Name:<br>
            <form:input path="name" />
        </label>
    </p>
    <p>
        <label>Password:<br>
            <form:password path="password" />
        </label>
    </p>
    <p>
        <label>Password confirm:<br>
            <form:password path="confirmPassword" />
        </label>
    </p>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
