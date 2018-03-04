<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

    <title>Available options for tariffs</title>

    <!-- Bootstrap core CSS -->

    <link href="/resources/css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/dashboard.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">OperatorSotovoiSvyazi</a>

    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/logout">Log out (${user})</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="/welcome">
                            <p style="font-size: 24px">Contract</p>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/tariffs_user">
                            <p style="font-size: 24px">Tariffs</p>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/cart">
                            <p style="font-size: 24px">Cart</p>
                        </a>
                    </li>

                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

            <c:choose>
                <c:when test="${tariff.getIdTariff()== currentTariff}">
                    <h2>${tariff.getNameTariff()}(Current tariff)</h2>
                </c:when>
                <c:otherwise>
                    <h2>${tariff.getNameTariff()}</h2>
                </c:otherwise>
            </c:choose>

            <div class="table-responsive">
                <form:form method="post" action="cart" modelAttribute="contract">

                    <spring:bind path="idContract">
                        <form:input type="hidden" path="idContract" value="${contractEntity.getIdContract()}"/>
                    </spring:bind>
                    <spring:bind path="contractNumber">
                        <form:input type="hidden" path="contractNumber" value="${contractEntity.getContractNumber()}"/>
                    </spring:bind>
                    <spring:bind path="tariffId">
                        <form:input type="hidden" path="tariffId" value="${tariff.getIdTariff()}"/>
                    </spring:bind>

                    <table class="table table-striped table-sm">
                        <thead>
                        <h5>Available options for the tariff</h5>
                        <tr>
                            <th>...</th>
                            <th style="width: 600px">Name of the Option</th>
                            <th style="width: 600px">Price</th>
                            <th style="width: 600px">Cost of the connection</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${options}" var="option">
                            <tr>
                                <td>
                                    <spring:bind path="options">
                                        <c:set var="contains" value="false"/>
                                        <c:forEach items="${connectedOptions}" var="connected">
                                            <c:if test="${connected.getIdOption() == option.getIdOption()}">
                                                <form:checkbox path="options" name="checkbox" checked="checked"
                                                       value="${option.getIdOption()}"/>
                                                <c:set var="contains" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${contains == false}">
                                            <form:checkbox path="options" name="checkbox" value="${option.getIdOption()}"/>
                                        </c:if>
                                    </spring:bind>
                                </td>

                                <td>${option.getNameOption()}</td>
                                <td>${option.getPriceOption()}</td>
                                <td>${option.getConnectionCostOption()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit" class="btn  btn-warning" style="width: 200px;">Add to the cart</button>

                </form:form>
                <a class="btn  btn-primary"
                   style="width: 200px;background-color: #343a40; border-color: #343a40 ; font-weight: bold;"
                   href="tariffs_user">Back</a>

            </div>
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