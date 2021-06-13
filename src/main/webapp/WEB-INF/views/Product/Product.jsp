<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>sản phẩm</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Danh sách sản phẩm</h1>
                </div>

                <div class="col-sm-auto">
                    <a class="btn btn-primary" href="/SalesManagement/product/add">Thêm sản phẩm</a>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->

        <!-- Card -->
        <div class="card">
            <!-- Header -->
            <div class="card-header">
                <div class="row justify-content-between align-items-center flex-grow-1">
                    <div class="col-md-4 mb-3 mb-md-0">
                        <form>
                            <!-- Search -->
                            <div class="input-group input-group-merge input-group-flush">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="tio-search"></i>
                                    </div>
                                </div>
                                <input id="datatableSearch" type="search" class="form-control" placeholder="Tìm kiếm sản phẩm" aria-label="Search users">
                            </div>
                            <!-- End Search -->
                        </form>
                    </div>
                </div>
                <!-- End Row -->
            </div>
            <!-- End Header -->

            <!-- Table -->
            <div class="table-responsive datatable-custom">
                <table id="datatable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options='{
                       "columnDefs": [{
                       "targets": [0, 4, 9],
                       "width": "5%",
                       "orderable": false
                       }],
                       "order": [],
                       "info": {
                       "totalQty": "#datatableWithPaginationInfoTotalQty"
                       },
                       "search": "#datatableSearch",
                       "entries": "#datatableEntries",
                       "pageLength": 12,
                       "isResponsive": false,
                       "isShowPaging": false,
                       "pagination": "datatablePagination"
                       }'>
                    <thead class="thead-light">
                        <tr>
                            <th scope="col" class="table-column-pr-0"></th>
                            <th class="table-column-pl-0">Sản phẩm</th>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Quy cách</th>
                            <th>Giá gốc</th>
                            <th>Chiết khấu (%)</th>
                            <th>Ưu đãi (%)</th>
                            <th>Chỉnh sửa</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td class="table-column-pr-0"></td>
                            <td class="table-column-pl-0"></td>
                            <td>1</td>
                            <td>Phân bón NPK</td>
                            <td>1000</td>
                            <td>2.000.000</td>
                            <td>10</td>
                            <td>5</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a class="btn btn-sm btn-white" href="/SalesManagement/product/update">
                                        <i class="tio-edit"></i> Cập nhật
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- End Table -->

            <!-- Footer -->
            <div class="card-footer">
                <!-- Pagination -->
                <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
                    <div class="col-sm mb-2 mb-sm-0">
                        <div class="d-flex justify-content-center justify-content-sm-start align-items-center">
                            <span class="mr-2">Showing:</span>

                            <!-- Select -->
                            <select id="datatableEntries" class="js-select2-custom" data-hs-select2-options='{
                                    "minimumResultsForSearch": "Infinity",
                                    "customClass": "custom-select custom-select-sm custom-select-borderless",
                                    "dropdownAutoWidth": true,
                                    "width": true
                                    }'>
                                <option value="12" selected="">12</option>
                                <option value="14">14</option>
                                <option value="16">16</option>
                                <option value="18">18</option>
                            </select>
                            <!-- Pagination Quantity -->
                            <span id="datatableWithPaginationInfoTotalQty"></span>
                        </div>
                    </div>

                    <div class="col-sm-auto">
                        <div class="d-flex justify-content-center justify-content-sm-end">
                            <!-- Pagination -->
                            <nav id="datatablePagination" aria-label="Activity pagination"></nav>
                        </div>
                    </div>
                </div>
                <!-- End Pagination -->
            </div>
            <!-- End Footer -->
        </div>
        <!-- End Card -->
    </div>
    <!-- End Content -->

    <!-- Footer -->

    <div class="footer">
        <div class="row justify-content-between align-items-center">
            <div class="col-auto">
                <div class="d-flex justify-content-end">
                    <!-- List Dot -->
                    <ul class="list-inline list-separator">
                        <li class="list-inline-item">
                            <a class="list-separator-link" href="#">FAQ</a>
                        </li>

                        <li class="list-inline-item">
                            <a class="list-separator-link" href="#">License</a>
                        </li>

                        <li class="list-inline-item">
                            <!-- Keyboard Shortcuts Toggle -->
                            <div class="hs-unfold">
                                <a class="js-hs-unfold-invoker btn btn-icon btn-ghost-secondary rounded-circle" href="javascript:;" data-hs-unfold-options='{
                                   "target": "#keyboardShortcutsSidebar",
                                   "type": "css-animation",
                                   "animationIn": "fadeInRight",
                                   "animationOut": "fadeOutRight",
                                   "hasOverlay": true,
                                   "smartPositionOff": true
                                   }'>
                                    <i class="tio-command-key"></i>
                                </a>
                            </div>
                            <!-- End Keyboard Shortcuts Toggle -->
                        </li>
                    </ul>
                    <!-- End List Dot -->
                </div>
            </div>
        </div>
    </div>

    <!-- End Footer -->
</main>