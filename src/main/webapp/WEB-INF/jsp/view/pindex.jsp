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
            <a href="<c:url value="/lecture/addlecture" />">Add Lecture</a><br /><br />
        </security:authorize>

        <c:choose>
            <c:when test="${fn:length(lectureDatabase) == 0}">
                <i>There are no lecture in the system.</i>
            </c:when>

            <c:otherwise>
                <c:forEach items="${lectureDatabase}" var="lecture">
                    ${lecture.coursecode}:
                    <a href="<c:url value="/lecture/coursematerial/${lecture.coursecode}" />">
                        <c:out value="${lecture.coursename}" /></a>

                    <security:authorize access="hasRole('ADMIN') or
                                        principal.username=='${ticket.customerName}'">
                        [<a href="<c:url value="/lecture" />">Edit</a>]
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">            
                        [<a href="<c:url value="/ticket/delete/${ticket.id}" />">Delete</a>]
                    </security:authorize>
                    <br /><br />
                </c:forEach>
            </c:otherwise>

        </c:choose>

        <security:authorize access="hasRole('ADMIN')">    
            <a href="<c:url value="/polling/addpolling" />">Add Polling</a><br /><br />
        </security:authorize>
        <c:choose>
            <c:when test="${fn:length(pollingDatabase) == 0}">
                <i>There are no lecture in the system.</i>
            </c:when>

            <c:otherwise>
                <c:forEach items="${pollingDatabase}" var="polling">
                    Question ${polling.id}:
                    <a href="<c:url value="/polling/${polling.id}" />">
                        <c:out value="${polling.question}" /></a>

                    <security:authorize access="hasRole('ADMIN') or
                                        principal.username=='${ticket.customerName}'">
                        [<a href="<c:url value="/lecture" />">Edit</a>]
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">            
                        [<a href="<c:url value="/ticket/delete/${ticket.id}" />">Delete</a>]
                    </security:authorize>
                    <br /><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </body>
</html>