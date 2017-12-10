<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submitted contents</title>
</head>
<body>
<h2>Submitted contents:</h2>
<ul>
    <c:forEach var="response" items="${ansData.responses}" varStatus="status">
        <li>${status.index+1}th question: ${response}</li>
    </c:forEach>
</ul>
<p>Respondent location: ${ansData.res.location}</p>
<p>Respondent age: ${ansData.res.age}</p>
</body>
</html>
