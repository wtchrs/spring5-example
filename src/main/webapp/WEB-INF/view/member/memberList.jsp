<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="member.lookup.title"/></title>
</head>
<body>

<form:form modelAttribute="listCommand">
    <p>Enter lookup period(yyyyMMddHH):</p>
    <p><label>from: <form:input path="from"/></label> <form:errors path="from"/></p>
    <p><label>to: <form:input path="to"/></label> <form:errors path="to"/></p>
    <input type="submit" value="<spring:message code="member.lookup.submit"/>"/>
</form:form>

<c:if test="${! empty members}">
    <table>
        <tr>
            <th><spring:message code="id"/></th>
            <th><spring:message code="email"/></th>
            <th><spring:message code="name"/></th>
            <th><spring:message code="regdate"/></th>
        </tr>
        <c:forEach var="member" items="${members}">
            <tr>
                <td>${member.id}</td>
                <td><a href="<c:url value="members/${member.id}"/>">${member.email}</a></td>
                <td>${member.name}</td>
                <td><tf:formatDateTime value="${member.registerDateTime}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
