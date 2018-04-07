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


    <title>Edit option</title>

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
                        <a class="nav-link" href="/tariffs_admin">
                            <p style="font-size: 24px">Tariffs</p>

                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/options_admin">
                            <p style="font-size: 24px">Options</p>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>

                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">New option</h1>
            </div>


            <form method="POST" action="/edit_nameAndprice_option"  class="form-signin" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="idOption" value="${option.getIdOption()}"/>
                <div class="row">

                        <div class="col-md-2 mb-3">
                            <label for="optionName">Name of an option</label>
                            <input path="nameOption" type="text" class="form-control" id="optionName" name="optionName"
                                       value ="${option.getNameOption()}" autofocus="true"/>


                        </div>

                        <div class="col-md-2 mb-3">
                            <label for="optionPrice">Price</label>
                            <input type="number" class="form-control" id="optionPrice" name="optionPrice"
                                        value ="${option.getPriceOption()}" autofocus="true"/>
                        </div>

                        <div class="col-md-3 mb-3">
                            <label for="optionConCost">Connection cost</label>
                            <input type="number" class="form-control" id="optionConCost" name="optionCost"
                                        value ="${option.getConnectionCostOption()}" autofocus="true"/>
                        </div>



                    <div class="col-md-3 mb-3">
                        <label for="tariff">Tariff</label>
                        <select name="id"  class="custom-select d-block w-100" id="tariff" >
                            <option value="">Default (${option.getTariff().getNameTariff()})</option>
                            <c:forEach items="${tariffs}" var="tariff">
                                <option value="${tariff.getIdTariff()}">${tariff.getNameTariff()}</option>
                            </c:forEach>

                        </select>
                        <%--<div class="invalid-feedback">--%>
                            <%--Please select a tariff for an option.--%>
                        <%--</div>--%>
                    </div>

                    <div class="col-md-3 mb-3">
                        <label for="typeOption">Type of the option</label>
                        <select name="typeOption"  class="custom-select d-block w-100" id="typeOption" >
                            <option value="">Default (${option.getCompatibility()})</option>
                            <option value="Compatible">Compatible</option>
                            <option value="Noncompatible">Noncompatible</option>
                            <option value="Compatible+">Compatible+</option>
                            <option value="Double">Double</option>
                        </select>
                        <%--<div class="invalid-feedback">--%>
                            <%--Please select a type of an option.--%>
                        <%--</div>--%>
                    </div>


                </div>
                <button class="btn btn-primary btn-block" style="width: 150px" type="submit">Edit option</button>
            </form>

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