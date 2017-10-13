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
    <title>Order</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">
</head>
<body>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/user/nav.jsp"%>
<main>
    <div class="tableContainer">
            <div class="tableRow" >

                <section class="tour_info">
                    <img src="../../images/line.png" width="430px">
                </section>

                <section class="tour_data">
                    <h1><fmt:message key="order.title"/></h1>

                    <table style="width: 400px">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                            <tr><td> <fmt:message key="order.pass.name"/>:</td><td class="info">${tour.nameUA}</td></tr>
                        </c:when>
                        <c:otherwise>
                        <tr><td>   <fmt:message key="order.pass.name"/>:</td><td class="info"> ${tour.name}</td></tr>
                        </c:otherwise>
                    </c:choose>
                        <tr><td> <fmt:message key="tuor.data.departure"/>:</td> <td  class="info">${pass.leavingDate}</td></tr>
                        <tr><td>  <fmt:message key="tour.duration"/>: </td><td class="info">${tour.duration}</td></tr>
                        <tr><td>  <fmt:message key="tour.quantity"/>: </td><td class="info">${pass.quantityAvailable}</td></tr>
                    <c:choose>
                        <c:when test="${pass.hot}">
                        <tr><td>   <fmt:message key="tour.price"/>:</td> <td class="info"> ${pass.price/2}$</td></tr>
                        </c:when>
                        <c:when  test="${isRegular == true && pass.discountForRegular>0}">
                        <tr><td>   <fmt:message key="tour.price"/>: </td> <td class="info">${pass.price}</td></tr>
                        <tr><td>  <fmt:message key="tour.discount"/>: </td> <td class="info"> ${pass.discountForRegular}%</td></tr>
                        </c:when>
                        <c:otherwise>
                        <tr><td>   <fmt:message key="tour.price"/>:</td> <td class="info"> ${pass.price}$</td></tr>
                        </c:otherwise>
                    </c:choose>
                    <form  action="user" >
                        <input type="hidden" name="command" value="price" />
                        <input type="hidden" name="id" value="${pass.id}" />
                        <div class="field">
                            <tr><td> <label for="quantity" ><fmt:message key="order.number"/> :</label></td> <td class="info">
                            <input type="number" name="quantity" step="1" min="1" max="${pass.quantityAvailable}" id="quantity"
                                   title="<fmt:message key="order.number.rules"/> ${pass.quantityAvailable}" required></td></tr><br><br>
                        </div>
                        <tr><td><br></td><td> </td></tr>
                        <tr><td> </td> <td class="info"><input type="submit"  value="<fmt:message key="order.submit"/>" style="align-self: center"/></td></tr>
                    </form>
                    </table>
                    <br>
                </section>

                <section class="tour_info">
                    <img src="../../images/line.png" width="430px">
                </section>
            </div>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
