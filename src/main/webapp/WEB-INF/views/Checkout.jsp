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
                    <div class="cart-wrapper">
                        <!-- Card -->
                        <div class="card mb-3">
                            <!-- Header -->
                            <div class="card-header">
                                <h4 class="card-header-title">Giỏ hàng (2)</h4>
                            </div>
                            <!-- End Header -->

                            <!-- Body -->
                            <div class="card-body">
                                <div class="row align-items-center mb-3">
                                    <span class="col-6">Tạm tính:</span>
                                    <h4 class="col-6 text-right text-dark mb-0">1.000.000 VNĐ</h4>
                                </div>

                                <hr class="my-4">

                                <div class="row align-items-center">
                                    <span class="col-6">Giảm giá:</span>
                                    <h4 class="col-6 text-right text-dark mb-0">100.000 VNĐ</h4>
                                </div>

                                <hr class="my-4">

                                <div class="row align-items-center">
                                    <span class="col-6 text-dark font-weight-bold">Tổng cộng:</span>
                                    <h3 class="col-6 text-right text-dark mb-0">900.000 VNĐ</h3>
                                </div>

                                <hr class="my-4">
                                <div class="row justify-content-center">
                                    <button id="clearCart" type="button" class="btn btn-danger mr-2">
                                        Xoá tất cả
                                    </button>
                                    <button id="checkoutFinishBtn" type="button" class="btn btn-primary">
                                        Thanh toán
                                    </button>
                                </div>
                            </div>
                            <!-- Body -->
                        </div>
                        <!-- End Card -->
                    </div>
                </div>

                <div class="col-lg-8">
                    <!-- Card -->
                    <div class="card mb-3 mb-lg-5">
                        <!-- Header -->
                        <div class="card-header">
                            <h4 class="card-header-title">Khách hàng</h4>
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
                                        <input type="number" class="form-control"
                                               name=""
                                               value="123456789"
                                               readonly>
                                    </div>
                                    <!-- End Form Group -->
                                </div>

                                <div class="col-sm-6">
                                    <!-- Form Group -->
                                    <div class="form-group">
                                        <label for="phoneDeliveryLabel" class="input-label">Số dư tài khoản</label>
                                        <input type="text" class="js-masked-input form-control" readonly
                                               name="accountBalance" value="10.000.000"
                                               >
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
                                        <input type="text" class="js-masked-input form-control"
                                               name=""
                                               value="10%"
                                               readonly>
                                    </div>
                                    <!-- End Form Group -->
                                </div>
                                <div class="col-sm-6">
                                    <label class="input-label">Khách hàng</label>
                                    <!-- Custom Radio -->
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input"
                                               name="accountType"
                                               checked
                                               readonly>
                                        <label class="custom-control-label" for="userAccountTypeRadio1">Thường</label>
                                    </div>
                                    <!-- End Custom Radio -->

                                    <!-- Custom Radio -->
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input"
                                               name="accountType"
                                               readonly>
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

                    <!-- Card -->
                    <div class="card mb-3 mb-lg-5">
                        <!-- Header -->
                        <div class="card-header">
                            <h4 class="card-header-title">Danh sách sản phẩm</h4>
                        </div>
                        <!-- End Header -->

                        <!-- Body -->
                        <div class="card-body">
                            <table class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Chiết khấu (%)</th>
                                        <th>Ưu đãi (%)</th>
                                        <th>Giá bán</th>
                                        <th>Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="lead">1</td>
                                        <td class="lead">Tên sản phẩm</td>
                                        <td class="lead">5</td>
                                        <td class="lead">0</td>
                                        <td class="lead">100.000</td>
                                        <td class="text-center">
                                            <button type="button" class="btn btn-sm btn-white">
                                                <i class="fas fa-check-square"></i>
                                                Chọn
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- Body -->
                    </div>
                    <!-- End Card -->

                    <!-- Card -->
                    <div class="card mb-3 mb-lg-5">
                        <!-- Header -->
                        <div class="card-header">
                            <h4 class="card-header-title">Sản phẩm đã chọn</h4>
                        </div>
                        <!-- End Header -->

                        <!-- Body -->
                        <div class="card-body">
                            <table class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Chiết khấu (%)</th>
                                        <th>Ưu đãi (%)</th>
                                        <th>Giá bán</th>
                                        <th>Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="lead">1</td>
                                        <td class="lead">Tên sản phẩm</td>
                                        <td class="lead">5</td>
                                        <td class="lead">0</td>
                                        <td class="lead">100.000</td>
                                        <td class="text-center">
                                            <button type="button" class="btn btn-sm btn-white">
                                                <i class="fas fa-trash-alt"></i>
                                                Bỏ chọn
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- Body -->
                    </div>
                    <!-- End Card -->
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