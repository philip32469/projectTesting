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

        ${pollingDatabase.question}<br /><br />
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollingForm">

            <form:input type="submit" path="choice"  value="A" />
            <form:label path="choice">: ${pollingDatabase.option1}</form:label><br />
            

            <form:input type="submit" path="choice"  value="B" />
            <form:label path="choice">: ${pollingDatabase.option2}</form:label><br />

            <form:input type="submit" path="choice" value="C" />
            <form:label path="choice">: ${pollingDatabase.option3}</form:label><br />

            <form:input type="submit" path="choice" value="D" />
            <form:label path="choice">: ${pollingDatabase.option4}</form:label><br />

            <form:input type="hidden" path="questionRecord"  value="${pollingDatabase.question}"/><br /><br /><br />

            Voting Statistics:<br />
            A:${pollingResultDatabase.totalchoiceA}  B:${pollingResultDatabase.totalchoiceB} C:${pollingResultDatabase.totalchoiceC} D:${pollingResultDatabase.totalchoiceD}<br />
        </form:form>
            
            
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="pollingForm">
            <form:input type="hidden" path="questionRecord"  value="${pollingDatabase.question}"/><br /><br /><br />
            <form:label path="comment">Comment</form:label><br />
            <form:input type="text" path="comment" /><br /><br />
            <input type="submit" value="Comment" />
        </form:form>

    </body>
</html>