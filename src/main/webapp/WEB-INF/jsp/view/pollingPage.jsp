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

        <h2>Question Page</h2>
        ${pollingDatabase.question}<br />
        <c:choose>
            <c:when test="${realTimeDatabase.username != null}">
                You have voted!
                <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollingForm">
                    <security:authorize access="hasRole('STUDENT')">            
                        <a href="<c:url value="/polling/edit/${pollingDatabase.id}/${currentName}" />">Edit Polling</a><br /><br />
                    </security:authorize>

                    Voting Statistics:<br />
                    A:${pollingResultDatabase.totalchoiceA}  B:${pollingResultDatabase.totalchoiceB} C:${pollingResultDatabase.totalchoiceC} D:${pollingResultDatabase.totalchoiceD}<br />
                </form:form>
            </c:when>
            <c:otherwise>

                    ${pollingDatabase.question}<br /><br />
                    <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollingForm">
                        <security:authorize access="hasRole('STUDENT')"> 
                            <form:input type="submit" path="choice"  value="A" />
                            <form:label path="choice">: ${pollingDatabase.option1}</form:label><br />


                            <form:input type="submit" path="choice"  value="B" />
                            <form:label path="choice">: ${pollingDatabase.option2}</form:label><br />

                            <form:input type="submit" path="choice" value="C" />
                            <form:label path="choice">: ${pollingDatabase.option3}</form:label><br />

                            <form:input type="submit" path="choice" value="D" />
                            <form:label path="choice">: ${pollingDatabase.option4}</form:label><br />

                            <form:input type="hidden" path="questionRecord"  value="${pollingDatabase.question}"/><br /><br /><br />


                            <a href="<c:url value="/polling/edit/${pollingDatabase.id}/${currentName}" />">Edit Polling</a><br /><br />
                        </security:authorize>


                        Voting Statistics:<br />
                        A:${pollingResultDatabase.totalchoiceA}  B:${pollingResultDatabase.totalchoiceB} C:${pollingResultDatabase.totalchoiceC} D:${pollingResultDatabase.totalchoiceD}<br />

                    </form:form>
            </c:otherwise>
        </c:choose>
        ========================================================================================<br />



        <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollingForm">
            <form:input type="hidden" path="questionRecord"  value="${pollingDatabase.question}"/><br /><br /><br />
            <form:label path="comment">Comment</form:label><br />
            <form:input type="text" path="comment" /><br /><br />
            <input type="submit" value="Comment" />
        </form:form>



        //==================================Comment=====================================================<br />

        <c:forEach items="${commentDatabase}" var="comment">
            <c:choose>
                <c:when test="${comment.question == pollingDatabase.question}">
                    ${comment.username}: ${comment.comment}<br />
                </c:when>
            </c:choose>
        </c:forEach>           

    </body>
</html>