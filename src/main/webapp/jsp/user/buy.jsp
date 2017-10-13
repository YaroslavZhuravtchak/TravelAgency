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



<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Buy</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">

</head>
<body>



<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/user/nav.jsp"%>

<main>

    <div class="tableContainer">
        <div class="tableRow">

            <fmt:setBundle basename="messages" />
            <section class="tour_info" style="width: 30%; border: solid black 1px;height: 350px;padding-left: 430px; padding-right: 430px ">

                <c:choose>
                    <c:when test="${isBougth}">
                       <h1 style="text-align: center"><fmt:message key="buy.ssuccessful1" /><br>
                           <fmt:message key="buy.ssuccessful2" /></h1>
                        <br>
                        <table style="width: 400px;  ">
                            <tr><td>Order №:</td><td class="info">${order.id}</td></tr>
                              <tr><td>Customer:</td><td class="info">${user.firstName} ${user.lastname}</td></tr>
                           <tr><td>Tour name:</td><td class="info">${tour.name}</td></tr>
                            <tr><td>Quantity:</td><td class="info">${order.quantity}</td></tr>
                            <tr><td>Date of departure:</td><td class="info">${pass.leavingDate}</td></tr>
                            <tr><td>Date of order:</td><td class="info">${order.orderDate}</td></tr>
                            <tr><td>Price:</td><td class="info">${order.totalPrice}</td></tr>

                        </table>

                    </c:when>
                    <c:otherwise>
                        <br><br><br><br>
                       <h1 style="text-align: center"><fmt:message key="buy.fail1" /><br>
                           <fmt:message key="buy.fail2" /></h1>
                    </c:otherwise>
                </c:choose>
                <img src="../../images/line.png" width="308px">
            </section>


        </div>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
