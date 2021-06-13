<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<main id="content" role="main" class="main" >
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="container" style="margin: 40px 0">
            <div class="row">
                <div class="col-1"></div>
                <div class="col-10">
                    <form class="d-flex justify-content-end" id="form-search">
                        <input class="form-control me-2" id="name" name="name" type="search" placeholder="Tên khách hàng" style="width: 300px">
                        <button id="btnSearch" class="btn btn-outline-success" type="submit">Tìm kiếm</button>
                    </form>     
                    <table id="example" class="ui celled table" style="width:100%">
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
                        <tbody id="tbody-cus">
                            <c:forEach var="customer" items="${customers}">
                                <tr>
                                    <td>${customer.getId()}</td>
                                    <td>${customer.getName()}</td>
                                    <td>${customer.getNumberCard()}</td>
                                    <td>
                                        <fmt:formatNumber type = "number" 
                                                          currencyCode="" value = "${customer.getAccountBalance()}" />
                                        VNĐ
                                    </td>
                                    <td>${customer.getPromotionsId()}</td>
                                    <td>
                                        <fmt:formatNumber type = "number" 
                                                          currencyCode="" value = "${customer.getDebtMax()}" />
                                        VNĐ
                                    </td>
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
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Tên KH</th>
                                <th>Số điện thoại</th>
                                <th>Số tài khoản</th>
                                <th>Số dư tài khoản</th>
                                <th>Mã ưu đãi</th>
                                <th>Công nợ</th>
                                <th>Chức năng</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="col-1"></div>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 30px;">
                <a href="/SalesManagement/customer/add" class="btn btn-secondary">
                    <i class="fas fa-plus-circle"></i> 
                    Thêm khách hàng
                </a>
            </div>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script >
//            const axios = require('axios');
    const btnSearch = document.querySelector('#btnSearch');
    const ipSearch = document.querySelector('#name');
    const tbodyCus = document.querySelector('#tbody-cus');

    btnSearch.addEventListener('click', async e => {
        e.preventDefault();
        axios.post('/SalesManagement/api/customer', {
            name: ipSearch.value,
            id: "123",
        }).then(function (response) {
            let registerList = response.data.map(u => {
                console.log(u.id);
                const id = u.id;
                return `<tr>
                                <td>` + u.id + `</td>
                                <td>` + u.name + `</td>
                                <td>` + u.numberCard + `</td>
                                <td>` + u.accountBalance + `</td>
                                <td>` + u.promotionsId + `</td>
                                <td>` + u.debtMax + `</td>
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
                                </tr>`;
            })
            tbodyCus.innerHTML = registerList.join('');
        }).catch(function (error) {
            console.log("bad");
        });
    })
</script>
