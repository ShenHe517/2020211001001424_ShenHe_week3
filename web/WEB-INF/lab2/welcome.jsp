<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome,
    <jsp:useBean id="login" class="lab2.login" scope="request"/>
    <jsp:getProperty name="login" property="username"/>
</h2>
</body>
</html>