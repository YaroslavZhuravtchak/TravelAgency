<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 10-Oct-17
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--NAV-->
<fmt:setBundle basename="text" />
<nav class="page-navigation">

    <div class="container">
        <ul>
            <li ><a href="admin?command=forward&page=mainadmin" style="color: aquamarine"><c:set var="command" value="updatelocal" scope="session"/><fmt:message key="menu.item1"/> </a></li>
            <li ><a href="admin?command=touradmin&type=shopping" style="color: aquamarine"><fmt:message key="menu.item4" /></a></li>
            <li ><a href="admin?command=touradmin&type=trip" style="color: aquamarine"><fmt:message key="menu.item3" /></a></li>
            <li ><a href="admin?command=touradmin&type=vacation" style="color: aquamarine"><fmt:message key="menu.item2" /></a></li>
            <li ><a href="admin?command=prepareadminsearch" style="color: aquamarine"><fmt:message key="nav.search" /></a></li>
        </ul>
        <!--LOGIN FORM-->

        <div id=logreg>
            <form action="main"  class="login">
                <input type="hidden" name="command" value="logout" />
                <input type="hidden" name="page" value="logout" />
                <input type="submit" value="<fmt:message key="label.form.logout" />"/>
            </form>

        </div>
        <!--LOGIN FORM-->
    </div>
</nav>
<!--NAV-->
