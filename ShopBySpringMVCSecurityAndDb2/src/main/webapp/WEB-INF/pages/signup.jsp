<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Signup here!</h1>

<c:if test="${param.error!=null}">
    <i style="color: crimson"> THIS USERNAME IS NOT UNIQUE </i>
</c:if>

<form:form  method="post" modelAttribute="user">
    Username:<form:input path="username"/>
    <br/>
    password:<form:input path="password"/>
    <br/>
    <input type="submit" value="signup">
</form:form>
</body>
</html>
