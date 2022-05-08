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
        <security:authorize access="hasAnyRole('LECTURER')">    
            <h2>Add Lecture Note</h2>
            <form:form method="POST" enctype="multipart/form-data"
                       modelAttribute="viewlectureForm">
                <b>Attachments</b><br />
                <input type="file" name="attachments" multiple="multiple" /><br /><br />
                <input type="submit" value="Submit"/>
            </form:form>
        </security:authorize>


        ${courseDatabase.coursename}
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="viewlectureForm">
            <form:input type="hidden" path="courseName"  value="${courseDatabase.coursename}"/><br /><br /><br />
            <form:label path="comment">Comment</form:label><br />
            <form:input type="text" path="comment" /><br /><br />
            <input type="submit" value="Comment" />
        </form:form>



        //==================================Comment=====================================================<br />

        <c:forEach items="${courseCommentDatabase}" var="courseComment">
            <c:choose>
                <c:when test="${courseComment.course == courseDatabase.coursename}">
                    <br />${courseComment.id}: ${courseComment.username}: ${courseComment.comment}             
                    <security:authorize access="hasRole('LECTURER')">            
                        <a href="<c:url value="/lecture/delete/comment/${courseComment.id}" />">Delete</a><br />
                    </security:authorize>
                </c:when>
            </c:choose>
        </c:forEach>   


    </body>
</html>