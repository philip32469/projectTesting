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

        You haven't voted
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="redirect">


            <a href="<c:url value="/polling/${pollingDatabase.id}" />">Go Back</a>
        </form:form>






    </body>
</html>