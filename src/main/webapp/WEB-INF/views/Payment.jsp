<%-- 
    Document   : Payment
    Created on : Jun 12, 2021, 9:35:21 PM
    Author     : USER
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Phiếu thu</h1>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->
        <form>
            <div id="addUserStepFormProgress" class="row justify-content-lg-center">
                <div class="col-lg-8">
                    <!-- Card -->
                    <div class="card mb-3 mb-lg-5">
                        <!-- Body -->
                        <div class="card-body">
                            <!-- Form Group -->
                            <div class="row form-group">
                                <label for="firstNameLabel" class="col-sm-3 col-form-label input-label">Họ tên</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="firstName" id="firstNameLabel" aria-label="Clarice" value="Tiger Nixo">
                                </div>
                            </div>
                            <!-- End Form Group -->

                            <!-- Form Group -->
                            <div class="row form-group">
                                <label for="emailLabel" class="col-sm-3 col-form-label input-label">Số điện thoại</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" name="phoneNumber" id="emailLabel" aria-label="clarice@example.com" value="039817262">
                                </div>
                            </div>
                            <!-- End Form Group -->

                            <!-- Form Group -->
                            <div class="js-add-field row form-group">
                                <label for="phoneLabel" class="col-sm-3 col-form-label input-label">Số tài khoản</label>
                                <div class="col-sm-9">
                                    <input type="number" class="js-masked-input form-control" name="phone" id="phoneLabel" value="123456789">                                    
                                </div>
                            </div>
                            <!-- End Form Group -->

                            <!-- Add Phone Input Field -->
                            <div class="js-add-field row form-group">
                                <label for="phoneLabel" class="col-sm-3 col-form-label input-label">Số dư tài khoản</label>
                                <div class="col-sm-9">
                                    <input type="number" class="js-masked-input form-control" name="" id="phoneLabel" value="10.000.000">                                    
                                </div>
                            </div>
                            <!-- End Add Phone Input Field -->

                            <!-- Form Group -->
                            <div class="row">
                                <label class="col-sm-3 col-form-label input-label">Tình trạng khách hàng</label>

                                <div class="col-sm-9">

                                    <!-- Custom Radio -->
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="userAccountTypeRadio" id="userAccountTypeRadio1">
                                        <label class="custom-control-label" for="userAccountTypeRadio1">Thường</label>
                                    </div>
                                    <!-- End Custom Radio -->

                                    <!-- Custom Radio -->
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" name="userAccountTypeRadio" id="userAccountTypeRadio2">
                                        <label class="custom-control-label" for="userAccountTypeRadio2">Công nợ</label>
                                    </div>
                                    <!-- End Custom Radio -->

                                </div>
                            </div>
                            <!-- End Form Group -->

                            <!-- Form Group -->
                            <div class="row form-group" style="margin-Top: 15px">
                                <label for="organizationLabel" class="col-sm-3 col-form-label input-label">Số tiền cần nạp</label>
                                <div class="col-sm-9">
                                    <input type="number" class="form-control" name="organization" id="organizationLabel" aria-label="Htmlstream">
                                </div>
                            </div>
                            <!-- End Form Group -->
                        </div>
                        <!-- End Body -->
                    </div>
                </div>
            </div>
            <div class="row justify-content-lg-center">
                <button id="confirmPaymentFinish" type="button" class="btn btn-primary">Xác nhận</button>
            </div>
        </form>
        <!-- End Row -->
        <!-- Message Body -->
        <div id="checkoutStepSuccessMessage" style="display: none;">
            <div class="text-center">
                <img class="img-fluid mb-3" src="<c:url value='/template/assets/svg/illustrations/hi-five.svg' />" alt="Image Description" style="max-width: 15rem;">

                <div class="mb-4">
                    <h2>ĐÃ NẠP TIỀN THÀNH CÔNG</h2>
                </div>

                <a class="btn btn-primary" href="/SalesManagement/customer">
                    <i class="tio-shopping-basket-outlined mr-1"></i> Tiếp tục
                </a>
            </div>
        </div>
        <!-- End Message Body -->
    </div>
    <!-- End Content -->
</main>