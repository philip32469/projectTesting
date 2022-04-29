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
        <security:authorize access="hasAnyRole('ADMIN', 'LECTURER')">    
            <h2>Add Lecture Note</h2>
            <form:form method="POST" enctype="multipart/form-data"
                       modelAttribute="ticketForm">
                <b>Attachments</b><br />
                <input type="file" name="attachments" multiple="multiple" /><br /><br />
                <input type="submit" value="Submit"/>
            </form:form>
        </security:authorize>

    </body>
</html>