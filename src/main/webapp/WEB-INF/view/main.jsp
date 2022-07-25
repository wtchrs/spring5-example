<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="main.title"/></title>
</head>
<body>

<c:if test="${empty authInfo}">
    <h2>Welcome!</h2>
    <p>
        <a href="<c:url value="/register/step1"/>">[<spring:message code="register.title"/>]</a>
        <a href="<c:url value="/login"/>">[<spring:message code="login.title"/>]</a>
    </p>
</c:if>

<c:if test="${! empty authInfo}">
    <h2>Welcome, ${authInfo.name}!</h2>
    <p>
        <a href="<c:url value="/edit/changePassword"/>">[<spring:message code="change.pwd.title"/>]</a>
        <a href="<c:url value="/logout"/>">[<spring:message code="logout"/>]</a>
    </p>
</c:if>

<p><a href="<c:url value="/survey"/>">[<spring:message code="survey.title"/>]</a></p>
<p><a href="<c:url value="/members"/>">[<spring:message code="member.lookup.title"/>]</a></p>

</body>
</html>
