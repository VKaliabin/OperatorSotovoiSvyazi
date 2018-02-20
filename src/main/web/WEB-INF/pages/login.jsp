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
  <link rel="icon" href="../../../../favicon.ico">

  <title>Signin Template for Bootstrap</title>

  <!-- Bootstrap core CSS -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/resources/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form method="POST" action="${contextPath}/login" class="form-signin">
  <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
  <h1 class="h3 mb-3 font-weight-normal">Please sign in or log in</h1>
  <label for="inputEmail" class="sr-only">Email address</label>
  <input name="username" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
  <label for="inputPassword" class="sr-only">Password</label>
  <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
  <%--<div class="checkbox mb-3">--%>
    <%--<label>--%>
      <%--<input type="checkbox" value="remember-me"> Remember me--%>
    <%--</label>--%>
  <%--</div>--%>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button><br>
  <%--<button class="btn btn-lg btn-primary btn-block" type="submit">--%>
    <%--<a class="btn btn-lg btn-primary btn-block" href="<c:url value="/registration"/>" target="_blank">Sign in</a>--%>
    <a class="btn btn-lg btn-primary btn-block" href="registration">Sign in</a>
  <%--</button>--%>
  <%--<p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>--%>
</form>
</body>
</html>
