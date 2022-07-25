<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="member.info"/></title>
</head>
<body>
<p><spring:message code="id"/>: ${member.id}</p>
<p><spring:message code="email"/>: ${member.email}</p>
<p><spring:message code="name"/>: ${member.name}</p>
<p>
    <spring:message code="regdate"/>: <tf:formatDateTime value="${member.registerDateTime}" pattern="yyyy-MM-dd HH:mm"/>
</p>
</body>
</html>
