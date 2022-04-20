<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<c:url var="logoutUrl" value="/cslogout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<h2>Index Page</h2>
<security:authorize access="hasRole('ADMIN')">    
    <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
</security:authorize>
    
<security:authorize access="hasRole('ADMIN')">    
    <a href="<c:url value="/lecture/addlecture" />">Add</a><br /><br />
</security:authorize>
    
<c:choose>
    <c:when test="${fn:length(lectureDatabase) == 0}">
        <i>There are no lecture in the system.</i>
    </c:when>

</c:choose>
    
<security:authorize access="hasRole('LECTURER')">    
    <a href="<c:url value="/lecture/addlecture" />">Add</a><br /><br />
</security:authorize>


</body>