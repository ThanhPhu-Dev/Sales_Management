<%-- Document : customer Created on : Jun 9, 2021, 3:13:42 PM Author : NghiaDX --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Khach hang</title>

        <style>
            .icon-modal {
                font-size: 40px;

            }
        </style>
    </head>

    <body>
        <div class="container" style="margin: 40px 0">
            <div class="row">
                <div class="col-2"></div>
                <div class="col-8">
                    <form class="d-flex " id="form-search">
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
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="customer" items="${customers}">
                                <tr>
                                    <td>${customer.getId()}</td>
                                    <td>${customer.getName()}</td>
                                    <td>${customer.getNumberCard()}</td>
                                    <td>${customer.getAccountBalance()}</td>
                                    <td>${customer.getPromotionsId()}</td>
                                    <td>${customer.getDebtMax()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Name</th>
                                <th>Position</th>
                                <th>Office</th>
                                <th>Age</th>
                                <th>Start date</th>
                                <th>Salary</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="d-flex justify-content-center" style="margin-top: 30px;">
                <button type="button" class="btn btn-secondary">
                    <i class="fas fa-plus-circle"></i> 
                    Thêm khách hàng
                </button>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script >
//            const axios = require('axios');
            const btnSearch = document.querySelector('#btnSearch');
            const ipSearch = document.querySelector('#name');

            btnSearch.addEventListener('click', async e => {
                e.preventDefault();
                axios.post('/SalesManagement/customer', {
                    name: ipSearch.value,
                }).then(function (response) {
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                });
            })
        </script>
    </body>

</html>