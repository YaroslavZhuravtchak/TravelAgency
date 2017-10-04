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
    <title>Tours</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">

</head>
<body>

<c:if test="${ role != 'USER' || empty user}">
    <jsp:forward page="/jsp/main.jsp"/>
</c:if>

<jsp:include page="header.jsp"/>

<%@ include file="/jsp/nav.jsp"%>
<main>
    <div class="tableContainer">
        <c:forEach var="tour" items="${tours}" begin="0">
            <div class="tableRow" style="padding-bottom: 10px">

                <section class="tour_img">
                    <br><br>
                    <img src="${tour["pathImage"]}" width="300px" style="align-items: stretch">
                </section>

                <section class="tour_info">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                            <h2 style="color: red">${tour.nameUA}</h2>
                            <p> ${tour.descriptionUA}</p>

                            <strong><fmt:message key="tour.transport"/>: </strong>${tour.transportType.name}<br>
                        </c:when>
                        <c:otherwise>
                            <h2 style="color: red">${tour.name}</h2>
                            <p> ${tour.description}</p>

                             <strong><fmt:message key="tour.transport"/>: </strong> ${tour.transportType}<br>
                        </c:otherwise>
                    </c:choose>
                    <a><strong><fmt:message key="tour.duration"/>: </strong>${tour.duration}</a>
                    <h3><fmt:message key="tour.location"/></h3>
                        <table>
                            <tr>
                        <c:forEach var="city" items="${tour.cities}"  varStatus="i">
                                <td>
                                  <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                        <strong><fmt:message key="tour.country"/></strong>: ${city.country.nameUA}<br>
                                        <strong><fmt:message key="tour.city"/></strong>: ${city.nameUA}<br>
                                    </c:when>
                                     <c:otherwise>
                                         <strong><fmt:message key="tour.country"/></strong>: ${city.country.name}<br>
                                         <strong><fmt:message key="tour.city"/></strong>: ${city.name}<br>
                                     </c:otherwise>
                                   </c:choose>
                                </td>
                        </c:forEach>
                            </tr>
                        </table>

                </section>

                <section class="tour_data">
                    <a name="${tour.id}" ></a>

                    <img src="../images/line.png" width="200px">

                    <c:forEach var="pass" items="${tour.passes}" step="1" varStatus="i">
                        <table>
                            <tr style="border: dotted black">
                                <td>
                                    <h2> <fmt:message key="tour.journey"/> № ${i.count}</h2>
                                    <em><fmt:message key="tuor.data.departure"/></em>: ${pass.leavingDate}<br>
                                    <em><fmt:message key="tour.quantity"/></em>: ${pass.quantityAvailable}<br>
                                    <c:choose>
                                        <c:when test="${pass.hot}">
                                          <em><fmt:message key="tour.price"/></em>: ${pass.price/2}$<br>
                                        </c:when>
                                        <c:when test="${pass.discountForRegular > 0}">
                                            <em><fmt:message key="tour.price"/></em>: ${pass.price}$<br>
                                            <em><fmt:message key="tour.discount"/></em>: ${pass.discountForRegular}%<br>
                                        </c:when>
                                        <c:otherwise>
                                            <em><fmt:message key="tour.price"/></em>: ${pass.price}$<br>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                  <td>

                                    <form class="login"  action="controller"  >
                                        <input type="hidden" name="command" value="order" />
                                        <input type="hidden" name="id" value="${pass.id}" />
                                        <input type="submit"  value="<fmt:message key="tour.choose"/>"/>
                                    </form><br><br>

                                    <c:choose>
                                       <c:when test="${pass.hot}">
                                         <img src="../images/hot.png" width="50px">
                                       </c:when>
                                        <c:when test="${pass.discountForRegular>0}">
                                            <img src="../images/percentage.ico" width="40px">
                                        </c:when>
                                    </c:choose>
                                  </td>

                            </tr>
                        </table>
                        <br>
                    </c:forEach>

                </section>

            </div>
        </c:forEach>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
