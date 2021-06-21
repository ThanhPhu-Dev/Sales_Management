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
                            <!--<button id="btnSearch" class="btn btn-outline-success" type="submit">Tìm kiếm</button>-->
                            <button id="btnSearch" type="submit" class="btn btn-outline-primary">Tìm kiếm</button>
                        </form> 
                        <!--end form tìm kiếm--> 
                    </div>
                </div>
            </div>
            <!-- End Header -->
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
