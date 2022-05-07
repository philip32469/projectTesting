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
        <security:authorize access="hasAnyRole('ADMIN', 'LECTURER')">   
            <a href="<c:url value="/lecture/addlecture" />">Add Lecture</a><br /><br />
        </security:authorize>




        <security:authorize access="hasAnyRole('STUDENT', 'LECTURER')">   
            <c:choose>
                <c:when test="${fn:length(lectureDatabase) == 0}">
                    <i>There are no lecture in the system.</i>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${lectureDatabase}" var="lecture">
                        ${lecture.coursecode}:
                        <a href="<c:url value="/lecture/coursematerial/${lecture.coursecode}" />">
                            <c:out value="${lecture.coursename}" /></a>

                        <security:authorize access="hasRole('LECTURER')">
                            [<a href="<c:url value="/lecture" />">Edit</a>]
                        </security:authorize>
                        <security:authorize access="hasRole('LECTURER')">            
                            [<a href="<c:url value="/lecture/delete/${lecture.coursecode}" />">Delete</a>]
                        </security:authorize>
                        <br /><br />
                    </c:forEach>
                </c:otherwise>

            </c:choose>

            <security:authorize access="hasAnyRole('ADMIN', 'LECTURER')">   
                <a href="<c:url value="/polling/addpolling" />">Add Polling</a><br /><br />
            </security:authorize>
            <c:choose>
                <c:when test="${fn:length(pollingDatabase) == 0}">
                    <i>There are no polling in the system.</i>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${pollingDatabase}" var="polling">
                        Question ${polling.id}:
                        <a href="<c:url value="/polling/${polling.id}" />">
                            <c:out value="${polling.question}" /></a>


                        <security:authorize access="hasRole('LECTURER')">            
                            [<a href="<c:url value="/polling/delete/${polling.id}" />">Delete</a>]
                        </security:authorize>
                        <br /><br />
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </security:authorize>


        <security:authorize access="hasRole('LECTURER')">   
            <a href="<c:url value="/editUser" />">Edit User Information</a><br /><br />
        </security:authorize>


        <security:authorize access="hasRole('LECTURER')">   
            <a href="<c:url value="/polling/votinghistory" />">Voting history</a><br /><br />
        </security:authorize>

        <security:authorize access="hasRole('LECTURER')">   
            <a href="<c:url value="/polling/commenthistory" />">Comment history(polling)</a><br /><br />
        </security:authorize>

        <security:authorize access="hasRole('LECTURER')">   
            <a href="<c:url value="/lecture/commenthistory" />">Comment history(course)</a><br /><br />
        </security:authorize>


        <security:authorize access="hasRole('GUEST')">   
            <c:choose>
                <c:when test="${fn:length(lectureDatabase) == 0}">
                    <i>There are no lecture in the system.</i>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${lectureDatabase}" var="lecture">
                        ${lecture.coursecode}:
                            <c:out value="${lecture.coursename}" /></a>
                        <br /><br />
                    </c:forEach>
                </c:otherwise>

            </c:choose>

            <c:choose>
                <c:when test="${fn:length(pollingDatabase) == 0}">
                    <i>There are no poll in the system.</i>
                </c:when>

                <c:otherwise>
                    <c:forEach items="${pollingDatabase}" var="polling">
                        Question ${polling.id}:
                            <c:out value="${polling.question}" /></a>
                        <br /><br />
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </security:authorize>


    </body>
</html>