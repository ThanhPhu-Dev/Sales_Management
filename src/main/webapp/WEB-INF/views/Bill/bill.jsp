<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Hóa Đơn</title>
<main id="content" role="main" class="main">
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Danh sách Hóa Đơn</h1>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->
        <div class="card">
            <!-- Header -->
            <div class="card-header">
                <div class="row justify-content-between align-items-center flex-grow-1">
                    <div class="col-md-4 mb-3 mb-md-0">
                            <!-- Search -->
                            <div class="input-group input-group-merge input-group-flush searchheader">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tio-search"></i>
                                    </div>
                                </div>
                                <div id="new-search-area"></div>
                            </div>
                            <!-- End Search -->
                    </div>
                </div>
                <!-- End Row -->

            </div>
            <!-- End Header -->

            <table class="table table-striped" id="datatablebill" style="width: 100%;">
            <thead class="text-center">
                <tr id="list-header">
                    <th scope="col">STT</th>
                    <th scope="col">Mã Hóa Đơn</th>
                    <th scope="col">Tên Khách Hàng</th>
                    <th scope="col">Giảm Giá</th>
                    <th scope="col">Ưu Đãi</th>
                    <th scope="col">Ưu Đãi(%)</th>
                    <th scope="col">Tổng Tiền</th>
                    <th scope="col">Ngày Tạo</th>
                    <th scope="col">Chi tiết</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        </div>
    </div>
</main>

