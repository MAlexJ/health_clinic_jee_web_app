<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css"/>

</head>
<body style="text-align: center">
<form method="post" style="display: inline-block; padding-top: 200px">
    <p>
        <label for="user">Username</label>
        <%-- handle  name="username" in LoginControler --%>
        <input id="user" name="username" type="text">
    </p>
    <p>
        <label for="pwd">Password</label>
        <%-- handle  name="password" in LoginControler --%>
        <input id="pwd" name="password" type="password">
    </p>
    <p>
        <button type="submit" class="btn btn-primary">Login</button>
    </p>

    <c:if test="${not empty requestScope.error}">
        <p style="color: red">${requestScope.error}</p>
    </c:if>

</form>
</body>
</html>