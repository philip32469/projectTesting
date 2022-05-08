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

        <h2>Voting History</h2>


        <c:forEach items="${pollingRecordDatabase}" var="pollingRecord">

            ${pollingRecord.question}: ${pollingRecord.username}: ${pollingRecord.choice}<br />   <br />         

        </c:forEach>   
        <security:authorize access="hasRole('LECTURER')">            
            <br /><a href="<c:url value="" />">Go Back To index</a>
        </security:authorize>

    </body>
</html>