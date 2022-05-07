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

    </body>
</html>