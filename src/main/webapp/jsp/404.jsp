<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 15-Oct-17
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "travel" uri = "/WEB-INF/tld/custom.tld" %>

<fmt:setBundle basename="text"  />
<fmt:setLocale value="${lang}" />

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="../../css/travel.css">
</head>
<body>

<header class="top"  style="background-image: url(../images/hb.jpg)";>
    <travel:showUser />
    <img id="headerLogo" src="../images/yy.png"  height="200px" alt="Header logo image">
</header>

<nav class="page-navigation">
    <div class="container">
        <ul>
            <c:choose>
                <c:when test="${not empty user and role eq 'USER'}">
                    <li ><a href="main?command=forward&page=main" style="color: aquamarine"><fmt:message key="menu.item1"/> </a></li>
                </c:when>
                <c:when test="${not empty user and role eq 'ADMIN'}">
                    <li ><a href="main?command=forward&page=mainadmin" style="color: aquamarine"><fmt:message key="menu.item1"/> </a></li>
                </c:when>
                <c:otherwise>
                    <li ><a href="main?command=forward&page=login" style="color: aquamarine"><fmt:message key="menu.item1"/> </a></li>
                </c:otherwise>
            </c:choose>
        </ul>

        <div id=logreg>
            <form action="main"  class="login">
                <input type="hidden" name="command" value="logout" />
                <input type="hidden" name="page" value="logout" />
                <input type="submit" value="<fmt:message key="label.form.logout" />"/>
            </form>
        </div>

    </div>
</nav>
<main>
    <section class="tour_info" style="width: 30%; border: solid black 1px;height: 350px;padding-left: 500px; padding-right: 430px ">
        <br><br>
        <h1 style="text-align: left; color: red"><fmt:message key="404.title"/></h1>
        <br>
        <c:if test="${not empty pageContext.errorData.requestURI}">
            <fmt:message key="error.request"/><span style="color: red">: ${pageContext.errorData.requestURI}</span> <br/>
        </c:if>
        <c:if test="${not empty pageContext.errorData.statusCode}">
            <fmt:message key="error.code"/>: <span style="color: red">${pageContext.errorData.statusCode}</span> <br/>
        </c:if>
    </section>
</main>
<%@ include file="/jsp/footer.jsp"%>
</body>
</html>