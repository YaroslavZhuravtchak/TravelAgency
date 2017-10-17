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

    <c:if test="${not empty user and role eq 'ADMIN'}">
        <jsp:forward page="/jsp/admin/main_admin.jsp"/>
    </c:if>
    <c:if test="${not empty user and role eq 'USER'}">
        <jsp:forward page="/jsp/user/main.jsp"/>
    </c:if>

    <%@ include file="/jsp/header.jsp"%>
    <nav class="page-navigation">

        <div class="container">
            <div id=logreg>
                <form action="login" class="login">
                    <input type="hidden" name="command" value="forward" />
                    <input type="hidden" name="page" value="login" />
                    <input type="submit" value="<fmt:message key="label.form.login"/>">
                </form>
            </div>
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
                    <form name="regForm" method="POST" action="login">
                        <input type="hidden" name="command" value="register" />
                        <h1 id="k"><fmt:message key="register.fillform" /></h1>

                                <div class="field">
                                    <label for="login"><fmt:message key="login.login" /></label>
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
                                    <label for="lastname" ><fmt:message key="register.lastname" /></label>
                                    <input type="text" name="lastname" id="lastname" pattern="([A-ZА-Яa-zа-я]){2,30}" required
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
                                <br/>
                                <span style="color: #ff0000;">${errorRegisterPassMessage}</span>

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
    </main>

    <%@ include file="/jsp/footer.jsp"%>
</body>
</html>
