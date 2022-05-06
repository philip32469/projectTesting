<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <h2>Create a Ticket</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="registrationForm">
            <form:label path="username">User Name</form:label><br />
            <form:input type="text" path="username" /><br /><br />

            <form:label path="password">Password</form:label><br />
            <form:input type="text" path="password" /><br /><br />

            <form:label path="fullname">Full Name</form:label><br />
            <form:input type="text" path="fullname" /><br /><br />

            <form:label path="phonenumber">Phone Number</form:label><br />
            <form:input type="text" path="phonenumber" /><br /><br />
            
            <form:label path="address">Address</form:label><br />
            <form:input type="text" path="address" /><br /><br />
            
            <form:input type="hidden" path="userrole"  value="ROLE_LECTURER"/><br /><br />
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>