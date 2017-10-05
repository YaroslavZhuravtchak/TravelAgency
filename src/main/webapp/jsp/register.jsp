<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 21-Sep-17
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/travel.css">

</head>
<body>

<c:if test="${ not empty user  }">
    <jsp:forward page="/jsp/main.jsp"/>
</c:if>

<%@ include file="/jsp/header.jsp"%>
<nav class="page-navigation">

    <div class="container">

        <!--LOGIN FORM-->

        <div id=logreg>

            <form class="login" name="updatelocal" action="controller"  >
                <input type="hidden" name="command" value="UpdateLocal" />
                <input type="hidden" name="local" value="en_UK" />
                <input type="submit"  value="EN"/>
            </form>
            <form class="login" name="updatelocal" action="controller" >
                <input type="hidden" name="command" value="UpdateLocal" />
                <input type="hidden" name="local" value="uk_UA" />
                <input type="submit"  value="UA"/>
            </form>
            <form action="controller" class="login">
                <input type="hidden" name="command" value="forward" />
                <input type="hidden" name="page" value="login" />
                <input type="submit" value="<fmt:message key="label.form.login"/>">
            </form>


        </div>
        <!--LOGIN FORM-->
    </div>
</nav>

<main>
    <div class="tableContainer">
          <div id="regform" >
            <section class="tour_img">

            </section>
            <section class="tour_img">
            </section>

            <section class="tour_info" >
                <br>
                <form name="regForm" method="POST" action="/controller">
                    <input type="hidden" name="command" value="register" />
                    <h1 id="k"><fmt:message key="register.fillform" /></h1>

                            <div class="field">
                                <label for="login"><fmt:message key="login.login"  /></label>
                                <input type="text" name="login" id="login" required pattern="([A-Za-zА-Яа-я0-9_-]){4,30}"
                                       title="<fmt:message key="register.login.roles" />">
                            </div>

                            <div class="field">
                                <label for="password" ><fmt:message key="login.password"  /></label>
                                <input type="password" id="password" name="password"  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                       title="<fmt:message key="register.password.rules" />" required>
                            </div>

                            <div class="field">
                                <label for="firstname" ><fmt:message key="register.firstname" /></label>
                                <input type="text"  name="firstname" id="firstname" pattern="([A-ZА-Яa-zа-я]){2,30}" required
                                       title="<fmt:message key="register.firstname.rules" />">
                            </div>

                            <div class="field">
                                <label for="secondname" ><fmt:message key="register.lastname" /></label>
                                <input type="text" name="secondname" id="secondname" pattern="([A-ZА-Яa-zа-я]){2,30}" required
                                       title="<fmt:message key="register.lastname.rules" />">
                            </div>

                            <div class="field">
                                <label for="email" >Emale</label>
                                <input type="email" name="email" id="email"  required>
                            </div>

                            <div class="field">
                                <label for="phone" ><fmt:message key="register.phone" /></label>
                                <input type="tel" name="phone" id="phone" value="+380" pattern="[+][3][8][0][0-9]{9}"
                                       title="<fmt:message key="register.phone.rules" />" required/>
                            </div>
                    <c:choose>
                        <c:when test="${ not empty errorRegisterPassMessage }">
                            <span style="color: #ff0000;">${errorRegisterPassMessage}</span>
                            <br/>
                            <br/>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                            <br>
                            <input type="submit" value="<fmt:message key="register.submit" />" id="button" >
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
