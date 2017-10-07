<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 07-Oct-17
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Add pass</title>
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
                    <input type="hidden" name="command" value="addpass" />
                    <input type="hidden" name="tourId" value="${tourId}" />
                    <input type="hidden" name="redir" value="${redir}" />
                    <h1 id="k"><fmt:message key="admin.addpass.title" /></h1>

                    <div class="field">
                        <label for="date"><fmt:message key="admin.addpass.date" /></label>
                        <input type="date" name="date" id="date" min="${date}" required>
                    </div>

                    <div class="field" >
                        <label for="quantity"><fmt:message key="admin.addpass.quantity" /></label>
                        <input type="number" name="quantity" id="quantity" min="1" step="1" required>
                    </div>

                    <div class="field">
                        <label for="price"><fmt:message key="admin.addpass.price" /></label>
                        <input type="number" name="price" id="price" min="1" step="0.01" required>
                    </div>

                    <br>
                    <br>

                    <input type="submit" value="<fmt:message key="admin.addpass.submit" />" id="button" >
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
