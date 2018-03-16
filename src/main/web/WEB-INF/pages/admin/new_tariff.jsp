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
<!doctype html>
<html lang="en">
<style>

</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../../../favicon.ico">--%>

    <title>New tariff</title>

    <!-- Bootstrap core CSS -->

    <link href="/resources/css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/dashboard.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">OperatorSotovoiSvyazi</a>

    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/logout">Log out(${user})</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin">
                            <p style="font-size: 24px">Clients</p>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/contracts">
                            <p style="font-size: 24px">Contracts</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/tariffs_admin">
                            <p style="font-size: 24px">Tariffs</p>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/options_admin">
                            <p style="font-size: 24px">Options</p>
                        </a>
                    </li>

                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">New Tariff</h1>
            </div>


            <%--<h2>Section title</h2>--%>

            <form:form method="POST" action="/new_tariff" modelAttribute="tariff" class="form-signin">
                <div class="row">
                    <spring:bind path="nameTariff">
                        <div class="col-md-3 mb-3">
                            <label for="tariffName">Name of a tariff</label>
                            <form:input type="text" class="form-control" id="tariffName"
                                        path="nameTariff" autofocus="true"/>
                            <form:errors path="nameTariff"/>
                        </div>
                    </spring:bind>

                    <spring:bind path="priceTariff">
                        <div class="col-md-3 mb-3">
                            <label for="tariffPrice">Price</label>
                            <form:input type="number" class="form-control" id="tariffPrice"
                                        path="priceTariff" autofocus="true"/>
                        </div>
                    </spring:bind>


                </div>
                <button class="btn btn-primary btn-block" style="width: 150px" type="submit">Add new tariff</button>
            </form:form>

        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/resources/js/jquery-slim.min.js"><\/script>')</script>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

</body>
</html>