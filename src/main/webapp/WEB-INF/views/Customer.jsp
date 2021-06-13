<%-- Document : customer Created on : Jun 9, 2021, 3:13:42 PM Author : NghiaDX --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="container" style="margin: 40px 0">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <table id="example" class="ui celled table table table-nowrap table-text-center" style="width:100%">
                        <thead>
                            <tr>
                                <th>Tên KH</th>
                                <th>Số điện thoại</th>
                                <th>Số tài khoản</th>
                                <th>Số dư tài khoản</th>
                                <th>Mã ưu đãi</th>
                                <th>Công nợ</th>
                                <th>Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Tiger Nixon</td>
                                <td>039817262</td>
                                <td>123456789</td>
                                <td>10.000.000</td>
                                <td>2011/04/25</td>
                                <td>$320,800</td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a class="btn btn-sm btn-white" href="/SalesManagement/payment">
                                            <i class="fa fa-credit-card" aria-hidden="true"></i> Nạp tiền
                                        </a>
                                         <a class="btn btn-sm btn-white btn-checkout" href="/SalesManagement/checkout">
                                            <i class="fa fa-book" aria-hidden="true"></i> Thanh toán
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-2"></div>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 30px;">
                <a class="btn btn-secondary" href="/SalesManagement/customer/add"><i class="fas fa-plus-circle"></i> Thêm khách hàng</a>
            </div>
        </div>
    </div>

    <!-- End Content -->


</main>