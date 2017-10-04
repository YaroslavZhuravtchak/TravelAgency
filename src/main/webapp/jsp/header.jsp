<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 30-Sep-17
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "travel" uri = "/WEB-INF/tld/custom.tld" %>
<header class="top"  style="background-image: url(../images/hb.jpg)";>
    <travel:showUser />
    <img id="headerLogo" src="../images/yy.png"  height="200px" alt="Header logo image">

        <div id="flagUa" style="position: absolute; top: 17px; right: 15px;">
            <a href="/controller?command=updatelocal&local=uk_UA"><img src="../images/ua.png" width="32px"></a></div>
        <div id="flagEn" style="position: absolute; top: 11px; right: 50px;">
            <a href="/controller?command=updatelocal&local=en_UK"><img src="../images/en.png" width="35px"></a>
        </div>
</header>
