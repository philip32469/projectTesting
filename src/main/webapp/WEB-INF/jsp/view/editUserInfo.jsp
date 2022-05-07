<!DOCTYPE html>
<html>
    <head>
        <title>Customer Support</title>
    </head>
    <body>
        <h2>Create a Ticket</h2>
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="editUserInfoForm">


            <form:label path="newFullname">Full Name</form:label><br />
            <form:input type="text" path="newFullname" /><br /><br />

            <form:label path="newPhonenumber">Phone Number</form:label><br />
            <form:input type="text" path="newPhonenumber" /><br /><br />

            <form:label path="newAddress">Address</form:label><br />
            <form:input type="text" path="newAddress" /><br /><br />

            Only Can click one:
            <form:checkbox path="newUser_role" value="ROLE_STUDENT" />ROLE_STUDENT
            <form:checkbox path="newUser_role" value="ROLE_LECTURE" />ROLE_LECTURE
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>