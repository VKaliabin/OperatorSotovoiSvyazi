<%--
  Created by IntelliJ IDEA.
  User: MASTER
  Date: 17.02.2018
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Registration</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/resources/css/form-validation.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="http://www.pvhc.net/img142/asevnxrcumhfyvtwbbnd.png" alt=""
             width="72" height="72">
        <h2>Registration form</h2>
    </div>

    <div class="row">

        <div class="col-md-12">

            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <div class="row">
                    <spring:bind path="name">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <form:input required="required" type="text" class="form-control" id="firstName"
                                       placeholder="First name" path="name" autofocus="true" />
                        </div>
                    </spring:bind>


                    <spring:bind path="surname">
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <form:input type="text" class="form-control" id="lastName" placeholder="Last name"
                                        path="surname" autofocus="true" required="required"/>

                        </div>
                    </spring:bind>
                </div>
                <spring:bind path="passportData">
                    <div class="mb-3">
                        <label for="passport">Passport data</label>
                        <form:input type="text" class="form-control" id="passport" placeholder="Passport data"
                                    path="passportData" autofocus="true" required="required"/>
                    </div>
                </spring:bind>

                <spring:bind path="emailOfEmail">
                    <div class="mb-3">
                        <label for="emailOfEmail">Email</label>
                        <form:input type="email" class="form-control" id="emailOfEmail" placeholder="Email"
                                    path="emailOfEmail" autofocus="true" required="required"/>
                        <form:errors path="emailOfEmail"/>
                    </div>
                </spring:bind>

                <spring:bind path="adress">
                    <div class="mb-3">
                        <label for="address">Address</label>
                        <form:input type="text" class="form-control" id="address" placeholder="1234 Main St"
                                    path="adress" autofocus="true" required="required"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="mb-3 ${status.error ? 'has-error' : ''}">
                        <label for="password"> Password</label>
                        <form:input type="password" path="password" class="form-control" placeholder="Password"
                        id="password" required="required"/>
                        <form:errors path="password"/>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label for="confirmpassword">Confirm passord</label>
                        <form:input type="password" path="confirmPassword" class="form-control" id ="confirmpassword"
                                    placeholder="Confirm your password" required="required"/>
                        <form:errors path="confirmPassword"/>
                    </div>
                </spring:bind>

                <%--<div class="well">--%>
                <label for="birthday">Date of Birth</label>
                <div id="datetimepicker4" class="input-append">
                    <spring:bind path="dateOfBirth">
                    <div class="input-group" style="width: 30%;">
                        <form:input class="form-control" data-format="yyyy-MM-dd" type="text" id="birthday" path="dateOfBirth"
                        placeholder="2010-10-10" required="required"/>
                        <span class=" add-on input-group-addon" id="basic-addon1">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar"></i>
                            </span>
                    </div>
                    </spring:bind>
                </div>


                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Sign in</button>
            </form:form>
        </div>
    </div>


</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<%--<script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>--%>
<%--<script src="../../../../assets/js/vendor/popper.min.js"></script>--%>
<%--<script src="../../../../dist/js/bootstrap.min.js"></script>--%>
<%--<script src="../../../../assets/js/vendor/holder.min.js"></script>--%>
<script src="/resources/js/bootstrap-datetimepicker.min.js"></script>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';

        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');

            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker4').datetimepicker({
            pickTime: false
        });
    });
</script>
</body>
</html>
