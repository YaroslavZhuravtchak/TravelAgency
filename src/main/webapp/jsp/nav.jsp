<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 30-Sep-17
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--NAV-->
<fmt:setBundle basename="text" />
<nav class="page-navigation">

    <div class="container">
        <ul>
                <li ><a href="controller?command=forward&page=main" style="color: aquamarine"><c:set var="command" value="updatelocal" scope="session"/><fmt:message key="menu.item1"/> </a></li>
                <li ><a href="controller?command=tour&type=shopping" style="color: aquamarine"><fmt:message key="menu.item4" /></a></li>
                <li ><a href="controller?command=tour&type=trip" style="color: aquamarine"><fmt:message key="menu.item3" /></a></li>
                <li ><a href="controller?command=tour&type=vacation" style="color: aquamarine"><fmt:message key="menu.item2" /></a></li>
        </ul>
        <!--LOGIN FORM-->

        <div id=logreg>
            <form action="controller"  class="login">
                <input type="hidden" name="command" value="logout" />
                <input type="hidden" name="page" value="logout" />
                <input type="submit" value="<fmt:message key="label.form.logout" />"/>
            </form>

        </div>
        <!--LOGIN FORM-->
    </div>
</nav>
<!--NAV-->
