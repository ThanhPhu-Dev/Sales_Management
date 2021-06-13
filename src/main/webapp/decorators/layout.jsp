  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">   
        <title>
            <dec:title />
        </title>
        <jsp:include page="/common/link-header.jsp" />
        <dec:head />
    </head>

    <body class="footer-offset footer-offset has-navbar-vertical-aside navbar-vertical-aside-show-xl"> 
        
        <!-- HEADER -->
            <jsp:include page="/common/header.jsp" />
        <!-- END HEADER -->

        <!-- MENU -->
            <jsp:include page="/common/sidebar-menu.jsp" />
        <!-- END MENU -->

        <!-- BODY -->
            <dec:body />
        <!-- END BODY -->

        <!-- FOOTER -->
            <jsp:include page="/common/footer.jsp" />
        <!-- END FOOTER -->
        
    </body>
</html>