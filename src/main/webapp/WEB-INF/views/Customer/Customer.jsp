<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<title>Khách hàng</title>
<style>
    .ui.grid{
        margin-top: -2rem; 
        margin-bottom: -1rem;
        margin-left: 0; 
        margin-right: -2rem;
    }
    main #example_wrapper{
        padding: 15px;
    }
</style>
<main id="content" role="main" class="main" >
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header" style="padding-bottom: 0">
            <div class="row d-flex align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Danh sách khách hàng</h1>
                </div>

                <div class="d-flex justify-content-center" >
                    <a href="/SalesManagement/customer/add" class="btn btn-secondary">
                        <i class="fas fa-plus-circle"></i> 
                        Thêm khách hàng
                    </a>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->

        <!-- Card -->
        <div class="card">
            <!-- Header -->
            <div class="card-header">
                <div class="row justify-content-end align-items-center flex-grow-1">
                    <div class="col-md-4 mb-3 mb-md-0">
                        <!--form tìm kiếm--> 
                        <form class="d-flex justify-content-end" id="form-search" method="GET" action="/SalesManagement/customer/search">
                            <input class="form-control me-2" id="numberSearch" name="numberSearch" type="search" placeholder="Nhập số diện thoại hoặc CMND" style="width: 300px">
                            <button id="btnSearch" class="btn btn-outline-success" type="submit">Tìm kiếm</button>
                        </form> 
                        <!--end form tìm kiếm--> 
                    </div>
                </div>
            </div>
            <!-- End Header -->
            <!--table-->
            <!--            <table id="example" class="ui celled table" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên KH</th>
                                    <th>CMND</th>
                                    <th>Số điện thoại</th>
                                    <th>Số tài khoản</th>
                                    <th>Mã ưu đãi</th>
                                    <th>Chức năng</th>
                                </tr>
                            </thead>
                            <tbody id="tbody-cus">
            
            <c:forEach var="customer" items="${customers}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${customer.getName()}</td>
                    <td>${customer.getIdentityCard()}</td>
                    <td>${customer.getPhone()}</td>
                    <td>${customer.getNumberCard()}</td>
                    
                    ưu đãi khách hàng
                <c:choose >
                    <c:when test="${customer.promotion.getName() == null}">
                        <td>Chưa áp dụng</td>
                    </c:when>
                    <c:otherwise>
                        <td>${customer.promotion.getName()}</td>
                    </c:otherwise>
                </c:choose>
                end ưu đãi
                
                <td class="d-flex justify-content-center">
                    <div class="btn-group" role="group">
                        <a class="btn btn-sm btn-white" href="/SalesManagement/payment?id=${customer.getId()}">
                            <i class="fa fa-credit-card" aria-hidden="true"></i> Nạp tiền
                        </a>
                        <a class="btn btn-sm btn-white btn-checkout" href="/SalesManagement/checkout?id=${customer.getId()}">
                            <i class="fa fa-book" aria-hidden="true"></i> Thanh toán
                        </a>
                        <a class="btn btn-sm btn-white btn-checkout" href="/SalesManagement/customer/update?id=${customer.getId()}">
                            <i class="fa fa-book" aria-hidden="true"></i> Sửa thông tin
                        </a>
                    </div>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>-->
            <div class="card-body">
                <table id="customerTable" class="table table-borderless table-thead-bordered table-align-middle card-table dataTable">
                    <thead class="thead-light">
                        <tr>
                            <th>STT</th>
                            <th>Tên KH</th>
                            <th>CMND</th>
                            <th>Số điện thoại</th>
                            <th>Số tài khoản</th>
                            <th>Mã ưu đãi</th>
                            <th>Chức năng</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <%--PAGINATION--%>
            <nav aria-label="navigation" class="mr-2">
                <ul id="checkout-products-pagination" class="pagination justify-content-end">

                </ul>
            </nav>
            <%--END PAGINATION--%>
            <!--end table-->
        </div>
    </div>
    <!-- End Card -->
</div>

</div>
</div>
</main>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="<c:url value='/template/assets/js/customer.js' />"></script>
<script src="<c:url value='/template/assets/js/test.js' />"></script>
