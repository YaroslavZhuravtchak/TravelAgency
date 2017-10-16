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

<jsp:include page="../header.jsp"/>

<%@ include file="/jsp/user/nav.jsp"%>
<main>
    <div class="tableContainer" >
        <c:forEach var="tour" items="${tours}" begin="0">
            <div class="tableRow" style="border-bottom: solid #b5a789 3px" >
                <section class="tour_img">
                    <br>
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">

                            <h3 style="text-align: center;  font-family: Calibri ">
                                <c:forEach var="city" items="${tour.cities}" begin="0" varStatus="i" >
                                    <c:if test="${i.count>1}">-</c:if>
                                    <em style="color: darkblue">${city.nameUA}</em>
                                </c:forEach>

                                <br>
                                <c:forEach var="city" items="${tour.cities}" begin="0" varStatus="i" >
                                    <c:if test="${i.count>1}">-</c:if>
                                    <em style="color: darkblue">${city.country.nameUA}</em>
                                </c:forEach>
                            </h3>
                        </c:when>
                        <c:otherwise>

                            <h3 style="text-align: center; font-family: Calibri">
                                <c:forEach var="city" items="${tour.cities}" begin="0" varStatus="i" >
                                    <c:if test="${i.count>1}">-</c:if>
                                    <em style="color: darkblue">${city.name}</em>
                                </c:forEach>

                                <br>
                                <c:forEach var="city" items="${tour.cities}" begin="0" varStatus="i" >
                                    <c:if test="${i.count>1}">-</c:if>
                                    <em style="color: darkblue">${city.country.name}</em>
                                </c:forEach>
                            </h3>
                        </c:otherwise>
                    </c:choose>
                    <img src="${tour["pathImage"]}" width="300px" style="align-items: stretch">
                    <br><br>

                    <table style="padding-left: 40px; ">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                        <tr><td> <strong><fmt:message key="tour.city.from"/>: </td><td class="info"></strong>${tour.cityFrom.name}</td></tr>
                        <tr><td> <strong><fmt:message key="tour.transport"/>: </td><td class="info"></strong>${tour.transportType.name}</td></tr>
                        </c:when>
                        <c:otherwise>
                        <tr><td> <strong><fmt:message key="tour.transport"/>: </td><td class="info"></strong> ${tour.transportType}</td></tr>
                        <tr><td>  <strong><fmt:message key="tour.city.from"/>: </td><td class="info"></strong>${tour.cityFrom}</td></tr>
                        </c:otherwise>
                    </c:choose>
                        <tr><td><strong><fmt:message key="tour.duration"/>: </td><td class="info"></strong>${tour.duration}</td></tr>
                    </table>
                </section>

                <section class="tour_info">
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                            <h1 style="color: #aa1a17; text-align: center">${tour.nameUA}</h1>
                            <p> ${tour.descriptionUA}</p>
                        </c:when>
                        <c:otherwise>
                            <h1 style="color: #aa1a17; text-align: center">${tour.name}</h1>
                            <p> ${tour.description}</p>
                        </c:otherwise>
                    </c:choose>


                </section>

                <section class="tour_data">
                    <a name="${tour.id}" ></a>

                    <img src="../../images/line.png" width="200px">

                    <c:forEach var="pass" items="${tour.passes}" step="1" varStatus="i">
                        <table style=" background-color:  #b5a789; border-radius: 10px; width: 390px">
                            <tr>
                                <td style="padding-left: 10px">
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

                                    <form class="login"  action="user"  >
                                        <input type="hidden" name="command" value="select" />
                                      <input type="hidden" name="id" value="${pass.id}" />
                                      <input type="submit"  value="<fmt:message key="tour.choose"/>"/>
                                      </form><br><br>

                                    <c:choose>
                                       <c:when test="${pass.hot}">
                                         <img src="../../images/hot.png" width="50px">
                                       </c:when>
                                        <c:when test="${pass.discountForRegular>0}">
                                            <img src="../../images/percentage.ico" width="40px">
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
