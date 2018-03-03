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

    <title>Dashboard Template for Bootstrap</title>

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
                        <a class="nav-link active" href="/welcome">
                            <p style="font-size: 24px">Contract</p>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/tariffs_user">
                            <p style="font-size: 24px">Tariffs</p>
                        </a>
                    </li>

                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">Contracts</h1>

            </div>


            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr style="font-size: 20px">
                        <th>Your Number</th>
                        <th>Tariff</th>
                        <th>Options</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${contracts}" var="contract">
                        <c:choose>
                            <c:when test="${contract.getBlockedContract() == 'Unblocked'}">
                            </c:when>
                            <c:otherwise>
                                <tr style="font-size: 18px; background-color: #efc5c5;">
                            </c:otherwise>
                        </c:choose>

                        <td style="font-size: 20px">${contract.getContractNumber()}</td>
                        <td style="font-size: 20px"><a>${contract.getTariff().getNameTariff()}</a></td>
                        <td style="font-size: 20px">
                            <c:forEach items="${contract.getOptions()}" var="options">
                                ${options.getNameOption()}<br>
                            </c:forEach>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${contract.getAdminBlock() == 'Y'}">
                                    <a style="font-size: 20px; color: #ac2925">Your number is blocked</a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${contract.getBlockedContract() == 'Unblocked'}">
                                            <a class="btn btn-lg btn-danger"
                                               href="/block?id=${contract.getIdContract()}"><i
                                                    class="fa fa-minus-square"></i></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-lg btn-primary"
                                               href="/unblock?id=${contract.getIdContract()}"><i
                                                    class="fa fa-plus-square"></i></a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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