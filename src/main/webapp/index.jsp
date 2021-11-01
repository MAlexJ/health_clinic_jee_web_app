<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<body>
<h2>Home page</h2>

<br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
<br>
Web Application Context Path = ${pageContext.request.contextPath}


<br>
<br>
<a href="${pageContext.request.contextPath}/appointments">Appointment</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/clients">Clients</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/doctors">Doctors</a>
<br>

</body>
</html>
