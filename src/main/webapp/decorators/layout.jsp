<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<c:url value='/template/style.css' />" />
    <title><dec:title default="Trang chủ" /></title>
</head>
<body>
    <!-- header -->
    <%@ include file="/common/header.jsp" %>
    <!-- header -->
    <div style="display: flex">
        <!-- menu left -->
        <%@ include file="/common/leftmenu.jsp" %>
        <!-- menu left -->
        <!-- content -->
        <dec:body/>
        <!-- /content -->
    </div>
</body>
</html>