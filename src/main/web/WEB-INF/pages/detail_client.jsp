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

    <title>Details of a client</title>

    <!-- Bootstrap core CSS -->

    <link href="/resources/css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/dashboard.css" rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
<script>
    var contractId = 0;
    $(function () {
        $("a").click(function () {
            console.log(this.id);
            if (this.id.indexOf('modalCall') !== -1) {
                contractId = $('#' + this.id).attr('name');
                console.log(contractId);
                $('#idContract').val(contractId);
                var inputText = $('#selector').val();
                $.ajax({
                    url: 'changetariff',
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    mimeType: 'application/json',
                    data: ({
                        tariffId: inputText,
                        contractId: contractId
                    }),
                    success: function (data) {
                        console.log(data);
                        var s = '';
                        s += '<h4 style="margin-top: 15px;">Options</h4>';
                        for (var j = 0; j < data.optionEntities.length; j++) {
                            var flag = false;
                            for (var i = 0; i < data.connectedOptions.length; i++) {
                                if (data.optionEntities[j].idOption == data.connectedOptions[i].idOption) {
                                    s += '<input name="checkbox" type="checkbox" checked value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                s += '<input name="checkbox" type="checkbox" value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                            }
                        }
                        $("#chekboxes").html('').append(s);
                    }
                });
            }
        });


        $("#selector").bind('input', function () {
            console.log(contractId);
            var inputText = $('#selector').val();
            $.ajax({
                url: 'changetariff',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    tariffId: inputText,
                    contractId: contractId
                }),
                success: function (data) {
                    console.log(data);


                    var s = '';
                    s += '<h5 style="margin-top: 15px;">Options</h5>';
                    for (var j = 0; j < data.optionEntities.length; j++) {
                        var flag = false;
                        for (var i = 0; i < data.connectedOptions.length; i++) {
                            if (data.optionEntities[j].idOption == data.connectedOptions[i].idOption) {
                                s += '<input name="checkbox" type="checkbox" checked value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            s += '<input name="checkbox" type="checkbox" value="' + data.optionEntities[j].idOption + '"/>' + data.optionEntities[j].nameOption + '<br>';
                        }
                    }
                    $("#chekboxes").html('').append(s);
                }
            });
        });

    });


</script>
<!-- Модальное окно -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Tariffs and option of the client</h4>
            </div>
            <form method="post" action="changeContract">
                <input type="hidden" name="idContract" value="${idContract}" id="idContract">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="modal-body">
                    <div class="col-md-12 mb-12">
                        <label for="tariff">Tariff</label>
                        <select name="selector" id="selector" class="custom-select d-block w-100 selectpicker"
                                id="tariff" required>
                            <%--<option value="">${contract.getTariff().getNameTariff()}</option>--%>
                            <c:forEach items="${tariffs}" var="tariff">
                                <option value="${tariff.getIdTariff()}">${tariff.getNameTariff()}</option>

                            </c:forEach>
                        </select>
                        <div id="chekboxes"></div>

                        <div class="invalid-feedback">
                            Please select a tariff for an option.
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

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
                        <a class="nav-link active" href="/contracts">
                            <p style="font-size: 24px">Contracts</p>
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/tariffs_admin">
                            <p style="font-size: 24px">Tariffs</p>
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
                <h1 class="h2">Client details (${client.getName()})</h1>

            </div>


            <%--<h2>Section title</h2>--%>
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr style="font-size: 20px">
                        <th>Client Number</th>
                        <th>Tariff</th>
                        <th>Options</th>
                        <th>Action</th>
                        <%--<th>Header</th>--%>
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
                        <td style="font-size: 20px">
                            <a data-toggle="modal" class="modalCall" id="modalCall_${contract.getIdContract()}"
                               name="${contract.getIdContract()}"
                               href="#myModal">${contract.getTariff().getNameTariff()}</a>
                        </td>
                        <td style="font-size: 20px">
                            <c:forEach items="${contract.getOptions()}" var="option">
                                ${option.getNameOption()}<br>
                            </c:forEach>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${contract.getAdminBlock() == 'N'}">
                                    <a class="btn btn-danger" style="width: 100px"
                                       href="/block_contract?id=${contract.getIdContract()}&idClient=${client.getIdClient()}">Block</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-success" style="width: 100px"
                                       href="/block_contract?id=${contract.getIdContract()}&idClient=${client.getIdClient()}">Unblock</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <%--<td>sit</td>--%>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a class="btn  btn-primary"
                   style="width: 200px;background-color: #343a40; border-color: #343a40 ; font-weight: bold;"
                   href="/contracts">Back</a>
            </div>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
<%--integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"--%>
<%--crossorigin="anonymous"></script>--%>
<%--<script>window.jQuery || document.write('<script src="/resources/js/jquery-slim.min.js"><\/script>')</script>--%>
<script src="/resources/js/popper.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

</body>
</html>