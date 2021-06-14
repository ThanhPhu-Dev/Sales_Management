<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required Meta Tags Always Come First -->
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link type="text/css" rel="stylesheet" href="<c:url value='/template/style.css' />" />
        <link type="text/css" rel="stylesheet" href="<c:url value='/template/customer.css' />" />

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <!-- Font -->
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

        <!--datatable-->
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.css" />
        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/1.10.25/css/dataTables.semanticui.css" />

        <!-- CSS Implementing Plugins -->
        <link type="text/css" rel="stylesheet" href="<c:url value='/template/assets/css/vendor.min.css' />">
        <link type="text/css" rel="stylesheet" href="<c:url value='/template/assets/vendor/icon-set/style.css' />">

        <!-- CSS Front Template -->
        <link type="text/css" rel="stylesheet" href="<c:url value='/template/assets/css/theme.min.css?v=1.0' />">
        <link type="text/css" rel="stylesheet" href="<c:url value='/template/Bill/Css/bill.css' />">

    </head>

    <body class="footer-offset footer-offset has-navbar-vertical-aside navbar-vertical-aside-show-xl">
        <header id="header" class="navbar navbar-expand-lg navbar-fixed navbar-height navbar-flush navbar-container">
            <div class="navbar-nav-wrap">
                <div class="navbar-brand-wrapper navbar-brand-wrapper-width">
                    <!-- Logo -->
                    <a class="navbar-brand" href="index.html" aria-label="Front">
                        <img class="navbar-brand-logo" src="<c:url value='/template/assets/svg/logos/logo.svg' />" alt="Logo">
                        <img class="navbar-brand-logo-mini" src="<c:url value='/template/assets/svg/logos/logo.svg' />" alt="Logo">
                    </a>
                    <!-- End Logo -->
                </div>
                <div class="navbar-nav-wrap d-flex justify-content-center title-header" >Phần mềm quản lý bán hàng</div>
            </div>
        </header>


        <aside class="js-navbar-vertical-aside navbar navbar-vertical-aside navbar-vertical navbar-vertical-fixed navbar-expand-xl navbar-bordered">
            <div class="navbar-vertical-container">
                <div class="navbar-vertical-footer-offset">
                    <div class="navbar-brand-wrapper justify-content-start">

                        <a class="navbar-brand" href="/SalesManagement/trang-chu" aria-label="Front" style="margin: 0 auto;">
                            <div class="menu-title">
                                <i class="fa fa-bars" aria-hidden="true"></i>
                                <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate"> MENU</span>
                            </div>
                        </a>
                    </div>

                    <!-- Content -->
                    <div class="navbar-vertical-content">
                        <ul class="navbar-nav navbar-nav-lg nav-tabs">
                            <!-- Overview -->
                            <li class="nav-item">
                                <a class="nav-link" href="/SalesManagement/trang-chu" title="Trang chủ">
                                    <i class="tio-home nav-icon left-menu-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate left-menu-content">Trang chủ</span>
                                </a> 
                                <!-- End Overview -->     
                            </li>
                            <!-- customer -->
                            <li class="navbar-vertical-aside-has-menu">
                                <a class="js-navbar-vertical-aside-menu-link nav-link nav-link-toggle" href="javascript:;" title="Cụm Rạp">
                                    <i class="fa fa-id-card nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate left-menu-content">Khách hàng</span>
                                </a>

                                <ul class="js-navbar-vertical-aside-submenu nav nav-sub">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/SalesManagement/customer/" title="Danh Sách khách hàng">
                                            <span class="tio-circle nav-indicator-icon"></span>
                                            <span class="text-truncate">Danh sách khách hàng</span>
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link" href="/SalesManagement/customer/add" title="Thêm khách hàng">
                                            <span class="tio-circle nav-indicator-icon"></span>
                                            <span class="text-truncate">Thêm khách hàng</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- End customer -->

                            <!-- product -->
                            <li class="navbar-vertical-aside-has-menu">
                                <a class="js-navbar-vertical-aside-menu-link nav-link nav-link-toggle" href="javascript:;" title="Rạp">
                                    <i class="fa fa-shopping-bag nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate left-menu-content">Sản phẩm</span>
                                </a>

                                <ul class="js-navbar-vertical-aside-submenu nav nav-sub">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/SalesManagement/product" title="Danh Sách Rạp">
                                            <span class="tio-circle nav-indicator-icon"></span>
                                            <span class="text-truncate">Danh Sách sản phẩm</span>
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link" href="/SalesManagement/product/add" title="Thêm Rạp">
                                            <span class="tio-circle nav-indicator-icon"></span>
                                            <span class="text-truncate">Thêm sản phẩm</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- End product -->
                            <li class="nav-item">
                                <a class="nav-link" href="/SalesManagement/bills" title="Trang chủ">
                                    <i class="fa fa-credit-card"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate receipt left-menu-content">Hóa đơn</span>
                                </a> 
                                <!-- End Overview -->     
                            </li>
                        </ul>
                    </div>
                    <!-- End Content -->

                </div>
            </div>
        </aside>



