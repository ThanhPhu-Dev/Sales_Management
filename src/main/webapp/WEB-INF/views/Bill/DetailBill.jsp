<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Hóa Đơn</title>
<main id="content" role="main" class="main">
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0 d-flex justify-content-between align-items-center">
                    <h1 class="page-header-title">Chi Tiết Hóa Đơn</h1>
                  <a href="/SalesManagement/report?id=${bill.id}" target="_blank"><button class="btn btn-danger" style="font-size: 13px">In Hóa Đơn</button></a>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->
        <div class="d-flex justify-content-around">
            <ul class="list-cus-order">
                <li class="d-flex">
                    <p class="name-cu">Mã Hóa Đơn :</p>
                    <p class="ct-cu">HD${bill.id}</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Họ Tên Khách Hàng :</p>
                    <p class="ct-cu">${bill.customer.name}</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Loại khách Hàng :</p>
                    <p class="ct-cu">${bill.promotionCustomer.name}</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Số Tài Khoản :</p>
                    <p class="ct-cu">${bill.customer.numberCard}</p>
                </li>
            </ul>
            <ul class="list-cus-order">
                <li class="d-flex">
                    <p class="name-cu">Ưu Đãi Loại Khách Hàng(%) :</p>
                    <p class="ct-cu">${bill.promotionCustomer.percentDiscount}%</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Giảm Giá(Tổng Hóa Đơn) :</p>
                    <p class="ct-cu">${bill.discount}%</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Số Tiền Còn Dư :</p>
                    <c:set var = "cvt" value = "${bill.customer.accountBalance}" />
                    <p class="ct-cu"><fmt:formatNumber type = "number"
                                                       maxFractionDigits = "3" value = "${cvt}" /> VNĐ</p>
                </li>
                <li class="d-flex">
                    <p class="name-cu">Ngày Thanh Toán :</p>
                    <p class="ct-cu">${date}</p>
                </li>
            </ul>
        </div>
        <div class="card">
            <!-- Header -->

            <!-- End Header -->

            <table class="table table-striped" style="width: 100%;">
                <thead >
                <tr id="list-header" class="text-center">
                    <th scope="col">STT</th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Quy Cách</th>
                    <th scope="col">Giá Gốc(VNĐ)</th>
                    <th scope="col">Chiết Khấu(%)</th>
                    <th scope="col">Tên Ưu Đãi</th>
                    <th scope="col">Ưu Đãi(%)</th>
                    <th scope="col">Số Lượng</th>
                    <th scope="col">Giá Cuối(VNĐ)</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dt" items="${detail}">
                    <%! int i=1; %>
                    <tr>
                        <td class="text-center"><%= i++%></td>
                        <td>${dt.product.name}</td>
                        <td class="text-right">${dt.product.specification}</td>
                        <c:set var = "cvt" value = "${dt.product.historicalCost}" />
                        <td class="text-right"><fmt:formatNumber type = "number"
                                                maxFractionDigits = "3" value = "${cvt}" /></td>
                        <td class="text-center">${dt.product.tradeDiscount}</td>
                        <td>${dt.promotion.name}</td>
                        <td class="text-center">${dt.promotion.percentDiscount}</td>
                        <td class="text-center">${dt.quantity}</td>
                        <c:set var = "cvt" value = "${dt.lastPrice}" />
                        <td class="text-right"><fmt:formatNumber type = "number"
                                                                 maxFractionDigits = "3" value = "${cvt}" /></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7" class="text-right font-weight-bold">Tổng Hóa Đơn(VNĐ): </td>
                    <c:set var = "cvt" value = "${sumbill}" />
                    <td colspan="3" class="text-right"><fmt:formatNumber type = "number"
                                                       maxFractionDigits = "3" value = "${cvt}" /></td>

                </tr>
                <tr>
                    <c:set var = "cvt" value = "${promotion}" />
                    <td colspan="7" class="text-right font-weight-bold">Ưu Đãi Khách Hàng(VNĐ): </td>
                    <td colspan="3" class="text-right text-danger">-<fmt:formatNumber type = "number"
                                                                    maxFractionDigits = "3" value = "${cvt}" /> </td>
                </tr>
                <tr>
                    <c:set var = "cvt" value = "${discount}" />
                    <td colspan="7" class="text-right font-weight-bold">Giảm Giá(VNĐ): </td>
                    <td colspan="3" class="text-right text-danger">-<fmt:formatNumber type = "number"
                                                                   maxFractionDigits = "3" value = "${cvt}" /></td>
                </tr>
                <tr>
                    <c:set var = "cvt" value = "${bill.total}" />
                    <td colspan="7" class="text-right font-weight-bold">Thành Tiền(VNĐ): </td>
                    <td colspan="3" class="text-right"><fmt:formatNumber type = "number"
                                                       maxFractionDigits = "3" value = "${cvt}" /></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>

