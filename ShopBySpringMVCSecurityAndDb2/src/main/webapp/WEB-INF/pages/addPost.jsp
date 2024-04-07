<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add post here!</h1>


<form:form  method="POST" modelAttribute="post">
    Title:<form:input path="title"/>
    <br/>
    ImgUrl:<form:input path="imgUrl"/>
    <br/>
    <input type="submit" value="addPost">
</form:form>
</body>
</html>
