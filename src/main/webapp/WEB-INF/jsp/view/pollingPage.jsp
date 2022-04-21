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



        好似work work 地<br /><br />
        ${pollingDatabase.question}<br /><br />

        <input TYPE=checkbox> ${pollingDatabase.option1}<br /><br />
        <input TYPE=checkbox> ${pollingDatabase.option2}<br /><br />
        <input TYPE=checkbox> ${pollingDatabase.option3}<br /><br />
        <input TYPE=checkbox> ${pollingDatabase.option4}<br /><br />

    </body>