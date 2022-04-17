<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
 <input type="submit" value="Log out" />
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>list</h1>
    </body>
</html>
