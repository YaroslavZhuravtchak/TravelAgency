<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 21-Sep-17
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="resources.text" var="rb" />
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Price</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">

</head>
<body>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/user/nav.jsp"%>
<main>
    <div class="tableContainer" >

            <section class="tour_info">
                <img src="../../images/line.png" width="430px" >
            </section>

            <section class="tour_data" style="width: 30%">
                <h1 style="text-align: center"><fmt:message key="price.title"/></h1>
                <img src="../../images/line.png" width="308px">
                <table style="width: 400px">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                        <tr><td><fmt:message key="order.pass.name"/>: </td><td class="info">${tour.nameUA}</td></tr>
                        </c:when>
                        <c:otherwise>
                        <tr><td><fmt:message key="order.pass.name"/>: </td><td class="info">${tour.name}</td></tr>
                        </c:otherwise>
                    </c:choose>
                        <tr><td><fmt:message key="tuor.data.departure"/>: </td><td class="info">${pass.leavingDate}</td></tr>
                        <tr><td><fmt:message key="tour.duration"/>: </td><td class="info">${tour.duration}</td></tr>
                        <tr><td><fmt:message key="order.number"/>: </td><td class="info">${quantity}</td></tr>
                        <tr><td><fmt:message key="tour.price"/>: </td><td class="info">${price}$</a></td></tr>
                    <tr><td><br></td><td> </td></tr>
                    <tr><td></td><td>
                <form action="user" method="post" >
                    <input type="hidden" name="command" value="buy" />
                    <input type="hidden" name="passId" value="${pass.id}" />
                    <input type="hidden" name="quantity" value="${quantity}" />
                    <input type="hidden" name="price" value="${price}" />
                    <input type="submit"  value="<fmt:message key="price.submit"/>"/>
                </form>
                    </td></tr>
                </table>
                <img src="../../images/line.png" width="308px">

            </section>

            <section class="tour_info">
                <img src="../../images/line.png" width="430px">
            </section>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
