<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

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
                        <a class="nav-link" href="/SalesManagement/trang-chu" title="Trang chủ">
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