<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 30-Sep-17
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--NAV-->
<fmt:setBundle basename="text" />
<nav class="page-navigation">

    <div class="container">
        <ul>
                <li ><a href="user?command=forward&page=main" style="color: aquamarine"><fmt:message key="menu.item1"/> </a></li>
                <li ><a href="user?command=tour&type=shopping" style="color: aquamarine"><fmt:message key="menu.item4" /></a></li>
                <li ><a href="user?command=tour&type=trip" style="color: aquamarine"><fmt:message key="menu.item3" /></a></li>
                <li ><a href="user?command=tour&type=vacation" style="color: aquamarine"><fmt:message key="menu.item2" /></a></li>
                <li ><a href="user?command=tour&type=hot" style="color: aquamarine"><fmt:message key="nav.hot" /></a></li>
                <li ><a href="user?command=tour&type=discount" style="color: aquamarine"><fmt:message key="nav.discount" /></a></li>
                <li ><a href="user?command=preparesearch" style="color: aquamarine"><fmt:message key="nav.search" /></a></li>
        </ul>

        <!--LOGOUT FORM-->
        <div id=logreg>
            <form action="main"  class="login">
                <input type="hidden" name="command" value="logout" />
                <input type="hidden" name="page" value="logout" />
                <input type="submit" value="<fmt:message key="label.form.logout" />"/>
            </form>
        </div>
        <!--LOGOUT FORM-->
    </div>
</nav>
<!--NAV-->
