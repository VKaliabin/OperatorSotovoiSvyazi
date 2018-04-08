<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 16.02.2018
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../../../favicon.ico">--%>
    <title>Access Denied</title>


    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <%--<link href="/resources/css/signin.css" rel="stylesheet">--%>
</head>

<body class="text-center" style="background-color: #f5f5f5;">
<h1 style="margin-top: 15%;">Access denied</h1>

<div class="container">
    <div class="row">
        <a class="btn btn-lg btn-primary btn-block center-block" style="width: 200px;background-color: #343a40; border-color: #343a40 ;"
                        href="/login">To login page</a>
    </div>

</div>

<%--<div></div>--%>
</body>
</html>
