<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<c:if test="${param.error != null}">
  <i style="color: crimson"> INVALID USERNAME OR PASSWORD</i>
</c:if>

<c:if test="${param.logout != null}">
  <i style="color: darkblue"> YOU ARE SUCCESSFULLY LOGGED OUT</i>
</c:if>

<h2>My Custom Login Page</h2>

<form:form  method="post" modelAttribute="user">
  Username: <input type="text" name="username">
  Password: <input type="password" name="password">

  <input type="submit" value="login">
</form:form>

<form:form action="logout" method="post">

  <input type="submit" value="logout">
</form:form>


</body>
</html>
