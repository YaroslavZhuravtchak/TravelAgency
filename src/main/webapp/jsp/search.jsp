<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 05-Oct-17
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="../css/travel.css">

</head>
<body>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/nav.jsp"%>

<main>
    <div class="tableContainer">
        <div id="regform" >
            <section class="tour_img">

            </section>
            <section class="tour_img">
            </section>

            <section class="tour_info" >
                <br>
                <form name="regForm" method="get" action="controller" id="form">
                    <input type="hidden" name="command" value="search" />
                    <h1 id="k"><fmt:message key="search.title" /></h1>


                    <div class="field">
                        <label for="country"><fmt:message key="search.country" /></label>
                        <select name="countryId"  id="country" style="width: 150px">
                            <c:forEach var="country" items="${countries}" begin="0">
                                <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                     <option value = "${country.id}" >${country.nameUA}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value = "${country.id}" >${country.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="field" >
                        <label for="tourTypes"><fmt:message key="search.type" /></label>
                        <select name="tourType"  id="tourTypes" style="width: 150px">
                            <c:forEach var="tourType" items="${tourTypes}" begin="0">
                                <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                        <option value = "${tourType}"  >${tourType.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value = "${tourType}"  >${tourType}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="field">
                        <label for="cityFromType"><fmt:message key="search.city" /></label>
                        <select name="cityFrom"  id="cityFromType" style="width: 150px">
                            <c:forEach var="cityFrom" items="${citiesFrom}" begin="0">
                                <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                        <option value = "${cityFrom}"  >${cityFrom.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value = "${cityFrom}"  >${cityFrom}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>

                    <br>
                    <br>

                    <input type="submit" value="<fmt:message key="search.find" />" id="button" >
                </form>
                <br>
                <img src="../images/line.png" width="330px">

            </section>

            <section class="tour_img">

            </section>
            <section class="tour_data">
            </section>

        </div>
    </div>
    </div>
</main>

<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
