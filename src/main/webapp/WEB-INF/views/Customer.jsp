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
        
        <script>
            
        </script>
    </body>

</html>