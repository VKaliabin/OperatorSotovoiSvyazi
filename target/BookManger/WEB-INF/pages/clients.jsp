<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Clients Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Clients list</h1>
<c:if test="${!empty listClients}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Age</th>

        </tr>
        <c:forEach items="${listClients}" var="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.age}</td>
                <td><a href="<c:url value='/edit/${client.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${client.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Client</h1>

<c:url var="addAction" value="/clients/add"/>

<form:form action="${addAction}" commandName="client">
    <table>
        <c:if test="${!empty client.id}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty client.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Client"/>"/>
                </c:if>
                <c:if test="${empty client.name}">
                    <input type="submit"
                           value="<spring:message text="Add Client"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>