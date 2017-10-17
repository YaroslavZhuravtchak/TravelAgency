<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 10-Oct-17
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.text" var="rb" />
<fmt:setLocale value="${lang}" />

<html>
<head>
    <meta charset="utf-8">
    <title>Main Admin</title>
    <link rel="stylesheet" type="text/css" href="../../css/travel.css">
</head>

<body>

<c:if test="${ empty user or role ne 'ADMIN'}">
    <jsp:forward page="/jsp/login.jsp"/>
</c:if>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/admin/nav_admin.jsp"%>


<div id="tableContainer">
    <div id="tableRow">

        <section id="main">
            <img src="../../images/admin.png" width="1254 px" height="300px">
        </section>

    </div> <!-- tableRow -->
</div> <!-- tableContainer -->

<%@ include file="/jsp/footer.jsp"%>

<script type="text/javascript" src="script/script.js"></script>
</body>
</html>

