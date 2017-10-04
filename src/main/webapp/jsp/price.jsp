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

<c:if test="${ role != 'USER' || empty user}">
    <jsp:forward page="/jsp/main.jsp"/>
</c:if>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/nav.jsp"%>
<main>
    <div class="tableContainer">

            <section class="tour_info">
                <img src="../images/line.png" width="430px" >
            </section>

            <section class="tour_data" style="width: 30%">
                <h1><fmt:message key="price.title"/></h1>
                <img src="../images/line.png" width="308px">
                <img src="../images/line.png" width="308px">
                <c:choose>
                    <c:when test="${lang=='uk_UA'}">
                        <em><fmt:message key="order.pass.name"/></em>: ${tour.nameUA}<br>
                    </c:when>
                    <c:otherwise>
                        <em><fmt:message key="order.pass.name"/></em>: ${tour.name}<br>
                    </c:otherwise>
                </c:choose>
                <em><fmt:message key="tuor.data.departure"/>: </em> ${pass.leavingDate}<br>
                <em><fmt:message key="tour.duration"/>: </em> ${tour.duration}<br>
                <em><fmt:message key="order.number"/></em>: ${quantity}<br>
                <em><fmt:message key="tour.price"/></em>: ${price}$</a><br>

                <form class="login" name="updatelocal" action="controller" method="post" >
                    <input type="hidden" name="command" value="buy" />
                    <input type="hidden" name="passId" value="${pass.id}" />
                    <input type="hidden" name="quantity" value="${quantity}" />
                    <input type="hidden" name="price" value="${price}" />
                    <input type="submit"  value="<fmt:message key="price.submit"/>"/>
                </form>
                <img src="../images/line.png" width="308px">

            </section>

            <section class="tour_info">
                <img src="../images/line.png" width="430px">
            </section>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
