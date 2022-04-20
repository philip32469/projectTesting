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

<h2>Add Lecture Course</h2>
    <form:form method="POST" enctype="multipart/form-data"
                             modelAttribute="lectureForm">
        <form:label path="courseCode">Course Code</form:label><br />
        <form:input type="text" path="courseCode" /><br /><br />
        <form:label path="courseName">Course Name</form:label><br />
        <form:input type="text" path="courseName" /><br /><br />

        <input type="submit" value="Add"/>
    </form:form>
</body>
</html>
