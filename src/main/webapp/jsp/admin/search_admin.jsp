<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 11-Oct-17
  Time: 5:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "travel" uri = "/WEB-INF/tld/custom.tld" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Search Admin</title>
    <link rel="stylesheet" type="text/css" href="../../css/travel.css">

</head>
<body>

<%@ include file="/jsp/header.jsp"%>
<%@ include file="/jsp/admin/nav_admin.jsp"%>

<main>
    <travel:sortTours list="${tours}"/>
    <travel:sortCities list="${cities}"/>
    <div class="tableContainer">
        <div id="regform" >
            <section class="tour_img">

            </section>
            <section class="tour_img">
            </section>

            <section class="tour_info" >
                <br><br>
                <h1 id="k"><fmt:message key="search.admin.title" /></h1>

                <form  method="get" action="admin" id="form">
                    <input type="hidden" name="command" value="searchadmin" />

                    <div class="field">
                        <label for="city"><fmt:message key="admin.search.city" /></label>
                        <select name="cityId"  id="city" style="width: 150px">
                            <c:forEach var="city" items="${cities}" begin="0">
                                <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                        <option value = "${city.id}" >${city.nameUA}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value = "${city.id}" >${city.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key="search.find" />" >
                    </div>
                </form>

                <form  method="get" action="admin">
                    <input type="hidden" name="command" value="searchadmin" />

                    <div class="field" >

                        <label for="tourName"><fmt:message key="admin.search.tour.name" /></label>
                        <select name="tourIdByName"  id="tourName" style="width: 150px">
                            <c:forEach var="tour" items="${tours}" begin="0">
                                <c:choose>
                                    <c:when test="${lang=='uk_UA'}">
                                        <option value = "${tour.id}"  >${tour.nameUA}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value = "${tour.id}"  >${tour.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key="search.find" />">
                    </div>

                </form>

                    <form  method="get" action="admin" >
                    <div class="field">
                        <input type="hidden" name="command" value="searchadmin" />
                        <label for="ID"><fmt:message key="admin.search.tour.id" /></label>
                        <input type="number" name="tourId" min="1" step="1" id="ID" required style="width: 150px" />
                        <input type="submit" value="<fmt:message key="search.find" />" >
                    </div>
                </form>
                <br><br><br>
                <img src="../../images/line.png" width="330px">

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