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
<%@ taglib prefix="travel" uri = "/WEB-INF/tld/custom.tld" %>


<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Buy</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">

</head>
<body>



<header class="top"  style="background-image: url(../images/hb.jpg)";>
    <travel:showUser />
    <img id="headerLogo" src="../images/yy.png"  height="200px" alt="Header logo image">
</header>
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
                        <fmt:setBundle basename="text" />
                        <table style="width: 400px;  ">
                            <tr><td><fmt:message key="tour.buy.number" />:</td><td class="info">${order.id}</td></tr>
                              <tr><td><fmt:message key="tour.buy.customer" />:</td><td class="info">${user.firstName} ${user.lastname}</td></tr>
                        <c:choose>
                            <c:when test="${lang=='uk_UA'}">
                           <tr><td><fmt:message key="tour.buy.tour.name" />:</td><td class="info">${tour.nameUA}</td></tr>
                            </c:when>
                            <c:otherwise>
                            <tr><td><fmt:message key="tour.buy.tour.name" />:</td><td class="info">${tour.name}</td></tr>
                            </c:otherwise>
                        </c:choose>
                            <tr><td><fmt:message key="tour.buy.quantity" />:</td><td class="info">${order.quantity}</td></tr>
                            <tr><td><fmt:message key="tour.buy.departure.date" />:</td><td class="info">${pass.leavingDate}</td></tr>
                            <tr><td><fmt:message key="tour.buy.order.date" />:</td><td class="info">${order.orderDate}</td></tr>
                            <tr><td><fmt:message key="tour.buy.price" />:</td><td class="info">${order.totalPrice}</td></tr>
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
