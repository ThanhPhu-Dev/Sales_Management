<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>Thanh toán</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Step Form -->
        <form>

            <!-- Content Step Form -->
            <div class="row">
                <%--                <div class="col-lg-3 order-lg-2 mb-5 mb-lg-0">--%>
                <%--                    <div class="cart-wrapper">--%>
                <%--                        <!-- Card -->--%>
                <%--                        <div class="card mb-3">--%>
                <%--                            <!-- Header -->--%>
                <%--                            <div class="card-header">--%>
                <%--                                <h4 class="card-header-title">Giỏ hàng (2)</h4>--%>
                <%--                            </div>--%>
                <%--                            <!-- End Header -->--%>

                <%--                            <!-- Body -->--%>
                <%--                            <div class="card-body">--%>
                <%--                                <div class="row align-items-center mb-3">--%>
                <%--                                    <span class="col-5">Tạm tính:</span>--%>
                <%--                                    <h4 class="col-7 text-right text-dark mb-0">1.000.000 VNĐ</h4>--%>
                <%--                                </div>--%>

                <%--                                <hr class="my-4">--%>

                <%--                                <div class="row align-items-center">--%>
                <%--                                    <span class="col-5">Giảm giá:</span>--%>
                <%--                                    <h4 class="col-7 text-right text-dark mb-0">100.000 VNĐ</h4>--%>
                <%--                                </div>--%>

                <%--                                <hr class="my-4">--%>

                <%--                                <div class="row align-items-center">--%>
                <%--                                    <span class="col-5 text-dark font-weight-bold">Tổng cộng:</span>--%>
                <%--                                    <h3 class="col-7 text-right text-dark mb-0">900.000 VNĐ</h3>--%>
                <%--                                </div>--%>

                <%--                                <hr class="my-4">--%>
                <%--                                <div class="row justify-content-center">--%>
                <%--                                    <button id="clearCart" type="button" class="btn btn-danger mr-2">--%>
                <%--                                        Xoá tất cả--%>
                <%--                                    </button>--%>
                <%--                                    <button id="checkoutFinishBtn" type="button" class="btn btn-primary">--%>
                <%--                                        Thanh toán--%>
                <%--                                    </button>--%>
                <%--                                </div>--%>
                <%--                            </div>--%>
                <%--                            <!-- Body -->--%>
                <%--                        </div>--%>
                <%--                        <!-- End Card -->--%>
                <%--                    </div>--%>
                <%--                </div>--%>
                <div class="col-lg-1 order-lg-2">
                    <%--CART BUTTON--%>
                    <div class="cart-wrapper">
                        <div class="btn-cart rounded-circle shadow d-flex align-items-center justify-content-center"
                             data-hidden="true" >
                            <span class="text-dark">
                                <i class="fas fa-shopping-cart fa-2x"></i>
                                <span id="cart-notify" class="cart-quantity cart-quantity-text hidden"></span>
                            </span>
                        </div>
                        <%--CART BODY--%>
                        <div class="cart-body">
                            <%--CARD--%>
                            <div class="card">
                                <!-- Header -->
                                <div class="card-header">
                                    <h4 class="card-header-title">Giỏ hàng (<span class="cart-quantity-text">0</span>)</h4>
                                </div>
                                <!-- End Header -->

                                <!-- Body -->
                                <div class="card-body">
                                    <div class="row align-items-center mb-3">
                                        <span class="col-5">Tạm tính:</span>
                                        <h4 id="cart-origin" class="col-7 text-right text-dark mb-0">0 VNĐ</h4>
                                    </div>

                                    <hr class="my-4">

                                    <div class="row align-items-center">
                                        <span class="col-5">Chiết khấu:</span>
                                        <h4 id="cart-discount" class="col-7 text-right text-dark mb-0">0 VNĐ</h4>
                                    </div>

                                    <hr class="my-4">

                                    <div class="row align-items-center">
                                        <span class="col-5">Giảm giá:</span>
                                        <h4 id="cart-sale" class="col-7 text-right text-dark mb-0">0 VNĐ</h4>
                                    </div>

                                    <hr class="my-4">

                                    <div class="row align-items-center">
                                        <span class="col-5 text-dark font-weight-bold">Tổng cộng:</span>
                                        <h3 id="cart-total" class="col-7 text-right text-dark mb-0">0 VNĐ</h3>
                                    </div>

                                    <hr class="my-4">
                                    <div class="row justify-content-center">
                                        <button id="btn-clear-cart" type="button" class="btn btn-danger mr-2">
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
                        <%--END CART BODY--%>
                    </div>
                    <%--END CART BUTTON--%>
                </div>

                <div class="col-lg-11">
                    <%--CUSTOMER--%>
                    <!-- Card -->
                    <c:if test="${not empty customer}">
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
                                    <label for="customerName" class="input-label">Tên khách hàng</label>
                                    <input type="text" class="form-control" name="customerName"
                                           id="customerName"
                                           value="${customer.getName()}"
                                           readonly>
                                </div>
                                <!-- End Form Group -->

                                <div class="row">
                                    <div class="col-sm-6">
                                        <!-- Form Group -->
                                        <div class="form-group">
                                            <label for="accountNumber" class="input-label">Số tài
                                                khoản</label>
                                            <input type="number" class="form-control"
                                                   name="accountNumber"
                                                   id="accountNumber"
                                                   value="${customer.getNumberCard()}"
                                                   readonly>
                                        </div>
                                        <!-- End Form Group -->
                                    </div>

                                    <div class="col-sm-6">
                                        <!-- Form Group -->
                                        <div class="form-group">
                                            <label for="accountBalance" class="input-label">Số dư tài
                                                khoản</label>
                                            <input type="text" class="js-masked-input form-control"
                                                   readonly id="accountBalance"
                                                   name="accountBalance" value="<fmt:formatNumber type = "number"
                                                  currencyCode="" value = "${customer.getAccountBalance()}" /> VND"
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
                                            <label for="customerPromotions">Ưu đãi</label>
                                            <input type="text" class="js-masked-input form-control"
                                                   name="customerPromotions"
                                                   id="customerPromotions"
                                                   value="${customer.getPromotion().getPercentDiscount()}%"
                                                   readonly>
                                        </div>
                                        <!-- End Form Group -->
                                    </div>
                                    <div class="col-sm-6">
                                            <%--                                        <label class="input-label">Khách hàng</label>--%>
                                            <%--                                        <!-- Custom Radio -->--%>
                                            <%--                                        <div class="custom-control custom-radio">--%>
                                            <%--                                            <input type="radio" class="custom-control-input"--%>
                                            <%--                                                   name="accountType"--%>
                                            <%--                                                   checked--%>
                                            <%--                                                   readonly>--%>
                                            <%--                                            <label class="custom-control-label"--%>
                                            <%--                                                   for="userAccountTypeRadio1">Thường</label>--%>
                                            <%--                                        </div>--%>
                                            <%--                                        <!-- End Custom Radio -->--%>

                                            <%--                                        <!-- Custom Radio -->--%>
                                            <%--                                        <div class="custom-control custom-radio">--%>
                                            <%--                                            <input type="radio" class="custom-control-input"--%>
                                            <%--                                                   name="accountType"--%>
                                            <%--                                                   readonly>--%>
                                            <%--                                            <label class="custom-control-label" for="userAccountTypeRadio2">Công--%>
                                            <%--                                                nợ</label>--%>
                                            <%--                                        </div>--%>
                                        <!-- End Custom Radio -->
                                    </div>
                                </div>
                                <!-- End Row -->
                            </div>
                            <!-- Body -->
                        </div>
                    </c:if>
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
                            <table id="productsTable" class="table table-borderless table-thead-bordered table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                    <tr>
                                        <th>Mã</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Quy cách (kg)</th>
                                        <th>Giá gốc</th>
                                        <th>Ưu đãi (%)</th>
                                        <th>Giá bán</th>
                                        <th>Số lượng</th>
                                        <th>Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- Body -->

                        <%--PAGINATION--%>
                        <nav aria-label="navigation" class="mr-2">
                            <ul id="checkout-products-pagination" class="pagination justify-content-end">

                            </ul>
                        </nav>
                        <%--END PAGINATION--%>
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
                            <table id="productsSelectedTable" class="table table-borderless table-thead-bordered table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                <tr>
                                    <th>Mã</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Quy cách (kg)</th>
                                    <th>Giá gốc</th>
                                    <th>Ưu đãi (%)</th>
                                    <th>Giá bán</th>
                                    <th>Số lượng</th>
                                    <th>Chức năng</th>
                                </tr>
                                </thead>
                                <tbody>

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
                <img class="img-fluid mb-3" src="<c:url value='/template/assets/svg/illustrations/hi-five.svg' />"
                     alt="Image Description" style="max-width: 15rem;">

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
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="<c:url value='/template/assets/js/checkout.js' />"></script>
