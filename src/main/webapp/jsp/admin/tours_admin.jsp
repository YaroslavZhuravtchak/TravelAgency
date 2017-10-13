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
    <title>Tours Admin</title>
    <link rel="stylesheet" type="text/css" href="css/travel.css">

</head>
<body>



<jsp:include page="../header.jsp"/>

<%@ include file="/jsp/admin/nav_admin.jsp"%>
<main>
    <div class="tableContainer">
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
                    <c:choose>
                        <c:when test="${lang=='uk_UA'}">
                            <strong><fmt:message key="tour.city.from"/>: </strong>${tour.cityFrom.name}<br>
                            <strong><fmt:message key="tour.transport"/>: </strong>${tour.transportType.name}<br>
                        </c:when>
                        <c:otherwise>
                            <strong><fmt:message key="tour.transport"/>: </strong> ${tour.transportType}<br>
                            <strong><fmt:message key="tour.city.from"/>: </strong>${tour.cityFrom}<br>
                        </c:otherwise>
                    </c:choose>
                    <a><strong><fmt:message key="tour.duration"/>: </strong>${tour.duration}</a>

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

                    <form class="login"  action="admin"  >
                        <input type="hidden" name="command" value="prepareaddpass" />
                        <input type="hidden" name="tourId" value="${tour.id}" />
                        <input type="submit"  value="<fmt:message key="admin.addpass"/>" style="float: right" />
                    </form>

                </section>

                <section class="tour_data" style="width: 500px">
                    <a name="${tour.id}" ></a>
                    <br><br>

                    <c:forEach var="pass" items="${tour.passes}" step="1" varStatus="i">
                        <table style=" background-color: #b5a789; border-radius: 10px" >
                            <tr >
                                <td style="padding-left: 10px">
                                    <div style="width: 250px">
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
                                    </div>
                                </td>

                                <td>
                                    <form class="login"  action="admin" style="padding-bottom: 0px; padding-top: 10px" >
                                        <input type="hidden" name="command" value="resethot" />
                                        <input type="hidden" name="id" value="${pass.id}" />
                                        <input type="hidden" name="anchor" value="${tour.id}" />
                                        <input type="submit"  value="<fmt:message key="admin.resethot" />" style="float: right; width: 60px" />
                                    </form>

                                    <form class="login"  action="admin"  style="padding-bottom: 0px; padding-top: 10px;" >
                                        <input type="hidden" name="command" value="makehot" />
                                        <input type="hidden" name="id" value="${pass.id}" />
                                        <input type="hidden" name="anchor" value="${tour.id}" />
                                        <input type="submit"  value="<fmt:message key="admin.makehot"/>" style=" width: 65px" />
                                    </form>

                                    <form class="login"  action="admin"  style="padding-bottom: 0px; padding-top: 0px" >
                                        <input type="hidden" name="command" value="setquantity" />
                                        <input type="hidden" name="id" value="${pass.id}" />
                                        <input type="hidden" name="anchor" value="${tour.id}" />
                                        <input type="number" name="quantity" min="0"  step="1" required style="height: 27px; width: 65px">
                                        <input type="submit"  value="<fmt:message key="admin.setquantity"/>" style="float: right; width: 80px" />
                                    </form>

                                    <form class="login"  action="admin"  style="padding-bottom: 0px; padding-top: 0px" >
                                        <input type="hidden" name="command" value="setdiscount" />
                                        <input type="hidden" name="id" value="${pass.id}" />
                                        <input type="hidden" name="anchor" value="${tour.id}" />
                                        <input type="number" name="discount" min="0" max="50" step="1" required style="height: 27px;width: 65px">
                                        <input type="submit"  value="<fmt:message key="admin.setdiscount"/>" style="float: right; width: 80px" />
                                    </form>

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
