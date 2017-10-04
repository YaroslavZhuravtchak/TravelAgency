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

<c:if test="${ role != 'USER' || empty user}">
    <jsp:forward page="/jsp/main.jsp"/>
</c:if>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/nav.jsp"%>

<main>

    <div class="tableContainer">
        <div class="tableRow">

            <section class="tour_info">
                <img src="../images/line.png" width="430px" >
            </section>
            <fmt:setBundle basename="messages" />
            <section class="tour_info" style="width: 30%">
                <img src="../images/line.png" width="308px">
                <img src="../images/line.png" width="308px">
                <img src="../images/line.png" width="308px">
                <c:choose>
                    <c:when test="${isBougth}">
                       <h1><fmt:message key="buy.ssuccessful1" /><br>
                           <fmt:message key="buy.ssuccessful2" /></h1>
                    </c:when>
                    <c:otherwise>
                       <h4><fmt:message key="buy.fail1" /><br>
                           <fmt:message key="buy.fail2" /></h4>
                    </c:otherwise>
                </c:choose>
                <img src="../images/line.png" width="308px">
                <img src="../images/line.png" width="308px">
                <img src="../images/line.png" width="308px">
            </section>

            <section class="tour_info">
                <img src="../images/line.png" width="430px" >
            </section>

        </div>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
