<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 21-Sep-17
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/travel.css">

</head>
<body>


<%@ include file="/jsp/header.jsp"%>

<nav class="page-navigation">
    <div class="container">

        <div id=logreg>

            <form action="register" class="login">
                <input type="hidden" name="command" value="forward" />
                <input type="hidden" name="page" value="register" />
                <input type="submit" value="<fmt:message key="label.form.registration"/> "  >
            </form>
        </div>

    </div>
</nav>
<!--LOGIN FORM-->
<main>
    <div class="tableContainer">
        <div class="tableRow">
            <div id="logform">
                <section class="tour_img">
                </section>
                <section class="tour_img">
                </section>
                <section class="tour_info" >
                    <br><br>
                    <form name="loginForm"method="post" action="main">
                        <input type="hidden" name="command" value="login" />
                        <h1 id="k"><fmt:message key="login.enter"  /></h1>
                        <fmt:message key="login.login"  /><br/>
                        <input type="text" name="login" required />
                        <br/><fmt:message key="login.password"  /><br/>
                        <input type="password" name="password" required/>
                        <br><br><br>
                            ${errorLoginPassMessage}
                        <br/>
                        <input type="submit" value="<fmt:message key="login.submit"  />"/>
                    </form>
                    <img src="../images/line.png" width="200px">
                </section>
                <section class="tour_data">
                </section>
                <section class="tour_img">
                </section>
            </div>
        </div>
    </div>
</main>
<!--LOGIN FORM-->
<%@ include file="/jsp/footer.jsp"%>
</body>
</html>
