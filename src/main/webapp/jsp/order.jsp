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

<c:if test="${ role != 'USER' || empty user}">
    <jsp:forward page="/jsp/main.jsp"/>
</c:if>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/nav.jsp"%>
<main>
    <div class="tableContainer">
            <div class="tableRow" >

                <section class="tour_info">
                    <img src="../images/line.png" width="430px">
                </section>

                <section class="tour_data">
                    <h1><fmt:message key="order.title"/></h1>
                    <img src="../images/line.png" width="308px">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                            <em><fmt:message key="order.pass.name"/>: ${tour.nameUA}</em><br>
                             <em><fmt:message key="tour.transport"/>: </em>${tour.transportType.name}<br>
                        </c:when>
                        <c:otherwise>
                            <em><fmt:message key="order.pass.name"/>: ${tour.name}</em><br>
                            <em><fmt:message key="tour.transport"/>: </em> ${tour.transportType}<br>
                        </c:otherwise>
                    </c:choose>
                    <em><fmt:message key="tour.duration"/>: </em>${tour.duration}<br>


                    <em><fmt:message key="tuor.data.departure"/></em>: ${pass.leavingDate}<br>
                    <em><fmt:message key="tour.quantity"/></em>: ${pass.quantityAvailable}<br>
                    <table>
                        <tr>
                            <c:forEach var="city" items="${tour.cities}"  varStatus="i">
                                <td>
                                    <c:choose>
                                        <c:when test="${lang=='uk_UA'}">
                                            <em><fmt:message key="tour.country"/></em>: ${city.country.nameUA}<br>
                                            <em><fmt:message key="tour.city"/></em>: ${city.nameUA}<br>
                                        </c:when>
                                        <c:otherwise>
                                            <em><fmt:message key="tour.country"/></em>: ${city.country.name}<br>
                                            <em><fmt:message key="tour.city"/></em>: ${city.name}<br>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>
                        </tr>
                    </table>
                    <c:choose>
                        <c:when test="${pass.hot}">
                            <em><fmt:message key="tour.price"/></em>: ${pass.price/2}$<br>
                        </c:when>
                        <c:when  test="${isRegular == true && pass.discountForRegular>0}">
                            <em><fmt:message key="tour.price"/></em>: ${pass.price}$<br>
                            <em><fmt:message key="tour.discount"/></em>: ${pass.discountForRegular}%<br>
                        </c:when>
                        <c:otherwise>
                            <em><fmt:message key="tour.price"/></em>: ${pass.price}$<br>
                        </c:otherwise>
                    </c:choose>

                    <br>
                    <form  action="controller" >
                        <input type="hidden" name="command" value="price" />
                        <input type="hidden" name="id" value="${pass.id}" />
                        <div class="field">
                            <label for="quantity" ><fmt:message key="order.number"/> :</label>
                            <input type="number" name="quantity" step="1" min="1" max="${pass.quantityAvailable}" id="quantity"
                                   title="<fmt:message key="order.number.rules"/> ${pass.quantityAvailable}" required><br><br>
                        </div>
                        <input type="submit"  value="<fmt:message key="order.submit"/>" style="align-self: center"/>
                    </form>
                </section>

                <section class="tour_info">
                    <img src="../images/line.png" width="430px">
                </section>
            </div>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
