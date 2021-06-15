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
                                <span class="cart-quantity">1</span>
                            </span>
                        </div>
                        <%--CART BODY--%>
                        <div class="cart-body">
                            <%--CARD--%>
                            <div class="card">
                                <!-- Header -->
                                <div class="card-header">
                                    <h4 class="card-header-title">Giỏ hàng (2)</h4>
                                </div>
                                <!-- End Header -->

                                <!-- Body -->
                                <div class="card-body">
                                    <div class="row align-items-center mb-3">
                                        <span class="col-5">Tạm tính:</span>
                                        <h4 class="col-7 text-right text-dark mb-0">1.000.000 VNĐ</h4>
                                    </div>

                                    <hr class="my-4">

                                    <div class="row align-items-center">
                                        <span class="col-5">Giảm giá:</span>
                                        <h4 class="col-7 text-right text-dark mb-0">100.000 VNĐ</h4>
                                    </div>

                                    <hr class="my-4">

                                    <div class="row align-items-center">
                                        <span class="col-5 text-dark font-weight-bold">Tổng cộng:</span>
                                        <h3 class="col-7 text-right text-dark mb-0">900.000 VNĐ</h3>
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
                                           value="${customer.getName()}">
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
                            <table class="table table-borderless table-thead-bordered table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                <tr>
                                    <th>Mã</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Quy cách (kg)</th>
                                    <th>Giá gốc</th>
                                    <th>Chiết khấu (%)</th>
                                    <th>Ưu đãi (%)</th>
                                    <th>Giá bán</th>
                                    <th>Số lượng</th>
                                    <th>Chức năng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty products}">
                                    <c:forEach var="product" items="${products}">
                                        <tr>
                                            <td class="lead">${product.getId()}</td>
                                            <td class="lead">${product.getName()}</td>
                                            <td class="lead">${product.getSpecification()}</td>
                                            <td class="lead">
                                                <fmt:formatNumber type="number"
                                                                  currencyCode=""
                                                                  value="${product.getHistoricalCost()}"/>
                                            </td>
                                            <td class="lead">${product.getTradeDiscount()}</td>
                                            <td class="lead">
                                                <c:choose>
                                                    <c:when test="${not empty product.getPromotions()}">
                                                        ${product.getPromotions().getPercentDiscount()}
                                                    </c:when>
                                                    <c:otherwise>
                                                        0
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="lead">
                                                <fmt:formatNumber type="number"
                                                                  currencyCode=""
                                                                  value="${product.getProductSalePrice()}"/>
                                            </td>
                                            <td class="lead">
                                                <input type="number"
                                                       class="js-masked-input form-control checkout-table__input"
                                                       name=""
                                                       value="1"
                                                       min="1"
                                                >
                                            </td>
                                            <td class="text-center">
                                                <button type="button" class="btn btn-sm btn-white">
                                                    <i class="fas fa-plus-square"></i>
                                                    Thêm
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
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
                            <table class="table table-borderless table-thead-bordered table-align-middle card-table dataTable">
                                <thead class="thead-light">
                                <tr>
                                    <th>Mã</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Quy cách (kg)</th>
                                    <th>Giá gốc</th>
                                    <th>Chiết khấu (%)</th>
                                    <th>Ưu đãi (%)</th>
                                    <th>Giá bán</th>
                                    <th>Số lượng</th>
                                    <th>Chức năng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="lead">1</td>
                                    <td class="lead">Tên sản phẩm</td>
                                    <td class="lead">5</td>
                                    <td class="lead">100</td>
                                    <td class="lead">100</td>
                                    <td class="lead">0</td>
                                    <td class="lead">100.000</td>
                                    <td class="lead">
                                        <input type="number" class="js-masked-input form-control checkout-table__input"
                                               name=""
                                               value="1"
                                               min="1"
                                        >
                                    </td>
                                    <td class="text-center">
                                        <button type="button" class="btn btn-sm btn-white">
                                            <i class="fas fa-trash-alt"></i>
                                            Xoá
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

<script>
    document.addEventListener('DOMContentLoaded', () => {
        let button = document.querySelector('.btn-cart');
        const cartBody = document.querySelector('.cart-body');
        button.addEventListener('click', (e) => {
            let isHidden = button.dataset.hidden === 'true';
            console.log(isHidden);

           !!isHidden ? cartBody.classList.add('active') : cartBody.classList.remove('active');

            button.dataset.hidden = !isHidden;
       })
    });
</script>
