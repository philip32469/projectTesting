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

        <h2>Edit Page</h2>

        <c:choose>
            <c:when test="${fn:length(userDatabase) == 0}">
                <i>There are no user in the system.</i>
            </c:when>

            <c:otherwise>
                <c:forEach items="${userDatabase}" var="user">
                    ${user.fullname}  &nbsp  ${user.phonenumber}  &nbsp   ${user.address}
                    <security:authorize access="hasRole('LECTURER')">            
                        [<a href="<c:url value="/createStudent" />">Edit</a>]
                    </security:authorize>
                    <security:authorize access="hasRole('LECTURER')">            
                        [<a href="<c:url value="/createLecturer" />">Delete</a>]<br />
                    </security:authorize>
                    <br /><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <br />Add User
        <security:authorize access="hasRole('LECTURER')">            
            <a href="<c:url value="/createStudent" />">Add Student</a>
        </security:authorize>

        <security:authorize access="hasRole('LECTURER')">            
            <a href="<c:url value="/createLecturer" />">Add Lecturer</a>
        </security:authorize>

    </body>
</html>