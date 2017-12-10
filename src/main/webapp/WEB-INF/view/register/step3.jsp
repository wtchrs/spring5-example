<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2><strong>Hello, ${formData.name}!</strong></h2>
<p>Register success</p>
<p><a href="<c:url value='/main'/>">[go to main page]</a></p>
</body>
</html>
