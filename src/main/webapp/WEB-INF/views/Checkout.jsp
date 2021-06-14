<%-- 
    Document   : Checkout
    Created on : Jun 12, 2021, 10:47:53 PM
    Author     : USER
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Thanh toán</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Step Form -->
        <form>

            <!-- Content Step Form -->
            <div class="row">
                <div class="col-lg-4 order-lg-2 mb-5 mb-lg-0">
                    <div id="checkoutStepOrderSummary">
                        <!-- Card -->
                        <div class="card mb-3">
                            <!-- Header -->
                            <div class="card-header">
                                <h4 class="card-header-title">2 sản phẩm</h4>                               
                            </div>
                            <!-- End Header -->

                            <!-- Body -->
                            <div class="card-body">
                                <!-- Media -->
                                <div class="media">
                                    <div class="media-body">
                                        <h4 class="mb-0">phân bón NPK</h4>
                                        <div class="text-body font-size-sm">
                                            <span>số lượng: </span>
                                            <span class="text-dark font-weight-bold">1</span>
                                        </div>
                                        <div class="text-body font-size-sm">
                                            <span>giá gốc: </span>
                                            <span class="text-dark font-weight-bold">1.000.000 VNĐ</span>
                                        </div>
                                        <div class="text-body font-size-sm">
                                            <span>Khuyến mãi: </span>
                                            <span class="text-dark font-weight-bold">10%</span>
                                        </div>  
                                        <div class="text-body font-size-sm">
                                            <span>Tạm tính: </span>
                                            <span class="text-dark font-weight-bold">900.000 VNĐ</span>
                                        </div>  
                                    </div>
                                </div>
                                <!-- End Media -->

                                <!-- Input Group -->
                                <div class="input-group input-group-merge">
                                    <input type="text" class="form-control" name="name" placeholder="mã ưu đãi" aria-label="Promo code">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-block btn-primary">Áp dụng</button>
                                    </div>
                                </div>
                                <!-- End Input Group -->
                                <hr class="my-4">

                                <div class="row align-items-center mb-3">
                                    <span class="col-6">Tạm tính:</span>
                                    <h4 class="col-6 text-right text-dark mb-0">1.000.000 VNĐ</h4>
                                </div>
                                <!-- End Row -->

                                <hr class="my-4">

                                <div class="row align-items-center">  
                                    <span class="col-6">Giảm giá:</span>
                                    <h4 class="col-6 text-right text-dark mb-0">100.000 VNĐ</h4>
                                </div>
                                <!-- End Row -->

                                <hr class="my-4">

                                <div class="row align-items-center">
                                    <span class="col-6 text-dark font-weight-bold">Tổng cộng:</span>
                                    <h3 class="col-6 text-right text-dark mb-0">900.000 VNĐ</h3>
                                </div>
                                <!-- End Row -->
                            </div>
                            <!-- Body -->
                        </div>
                        <!-- End Card -->
                    </div>
                </div>

                <div class="col-lg-8">
                    <div id="checkoutStepFormContent">
                        <!-- Card -->
                        <div id="checkoutStepDelivery" class="active">
                            <div class="card mb-3 mb-lg-5">
                                <!-- Header -->
                                <div class="card-header">
                                    <h4 class="card-header-title">Hóa đơn</h4>
                                </div>
                                <!-- End Header -->

                                <!-- Body -->
                                <div class="card-body">
                                    <!-- Form Group -->
                                    <div class="form-group">
                                        <label for="deliveryCityLabel" class="input-label">Tên khách hàng</label>
                                        <input type="text" class="form-control" name="deliveryCity" id="deliveryCityLabel" aria-label="London" value="Tiger Nixon">
                                    </div>
                                    <!-- End Form Group -->

                                    <div class="row">
                                        <div class="col-sm-6">
                                            <!-- Form Group -->
                                            <div class="form-group">
                                                <label for="emailDeliveryAddressLabel" class="input-label">Số tài khoản</label>
                                                <input type="number" class="form-control" name="emailDeliveryAddress" id="emailDeliveryAddressLabel" value="123456789">
                                            </div>
                                            <!-- End Form Group -->
                                        </div>

                                        <div class="col-sm-6">
                                            <!-- Form Group -->
                                            <div class="form-group">
                                                <label for="phoneDeliveryLabel" class="input-label">Số dư tài khoản</label>
                                                <input type="text" class="js-masked-input form-control" name="phoneDeliveryName" id="phoneDeliveryLabel" value="10.000.000">
                                            </div>
                                            <!-- End Form Group -->
                                        </div>
                                    </div>

                                    <!-- Row -->
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <!-- Form Group -->
                                            <div class="form-group">
                                                <label for="DeliveryZipCodeLabel">Ưu đãi</label>
                                                <input type="text" class="js-masked-input form-control" name="DeliveryZipCode" id="DeliveryZipCodeLabel" value="10%">
                                            </div>
                                            <!-- End Form Group -->
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="input-label">khách hàng</label>
                                            <!-- Custom Radio -->
                                            <div class="custom-control custom-radio">
                                                <input type="radio" class="custom-control-input" name="userAccountTypeRadio" id="userAccountTypeRadio1" checked>
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
                                    <!-- End Row -->                           
                                </div>
                                <!-- Body -->
                            </div>
                            <!-- End Card -->
                            <div class="row justify-content-center">
                                <button id="checkoutFinishBtn" type="button" class="btn btn-primary">
                                    Thanh toán
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Row -->
            </div>
            <!-- End Step Form -->
        </form>
        <!-- End Step Form -->

        <!-- Message Body -->
        <div id="checkoutStepSuccessMessage" style="display: none;">
            <div class="text-center">
                <img class="img-fluid mb-3" src="<c:url value='/template/assets/svg/illustrations/hi-five.svg' />" alt="Image Description" style="max-width: 15rem;">

                <div class="mb-4">
                    <h2>ĐÃ THANH TOÁN THÀNH CÔNG</h2>
                    <p>TỔNG CỘNG: 10.000.000</p>
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