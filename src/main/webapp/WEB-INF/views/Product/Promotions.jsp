<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Chương trình ưu đãi</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Danh sách chương trình ưu đãi</h1>
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
                                        <i class="fas fa-search"></i>
                                    </div>
                                </div>
                                <input id="datatableSearch" type="search" class="form-control" placeholder="Tìm kiếm ưu đãi" aria-label="Search users">
                            </div>
                            <!-- End Search -->
                        </form>
                    </div>
                </div>
                <!-- End Row -->
            </div>
            <!-- End Header -->

            <!-- Product Table -->
            <div class="table-responsive datatable-custom">
                <table id="promotionsTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options="{
                       &quot;columnDefs&quot;: [{
                            &quot;targets&quot;: [0, 1, 2, 3, 4],
                            &quot;orderable&quot;: false
                       }],
                       &quot;order&quot;: [],
                       &quot;info&quot;: {
                            &quot;totalQty&quot;: &quot;#datatableWithPaginationInfoTotalQty&quot;
                       },
                       &quot;search&quot;: &quot;#datatableSearch&quot;,
                       &quot;entries&quot;: &quot;#datatableEntries&quot;,
                       &quot;isResponsive&quot;: false,
                       &quot;isShowPaging&quot;: false,
                       &quot;pagination&quot;: &quot;datatablePagination&quot;
                       }">
                    <thead class="thead-light">
                    <tr>
                        <th>STT</th>
                        <th>Tên chương trình</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Ưu đãi (%)</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <!-- Product Table -->

            <!-- Footer -->
            <div class="card-footer">
                <!-- Pagination -->
                <div class="row justify-content-center justify-content-sm-between align-items-sm-center">
                    <div class="col-sm mb-2 mb-sm-0">
                        <div class="d-flex justify-content-center justify-content-sm-start align-items-center">
                            <span class="mr-2">Hiển thị:</span>

                            <!-- Select -->
                            <select id="datatableEntries" class="js-select2-custom" data-hs-select2-options='{
                                    "customClass": "custom-select custom-select-sm custom-select-borderless",
                                    "dropdownAutoWidth": true,
                                    "width": true
                                    }'>
                                <option value="8" selected >8</option>
                                <option value="12">12</option>
                                <option value="14">14</option>
                                <option value="16">16</option>
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

<script>

    document.addEventListener('DOMContentLoaded', () => {
        const datatable = $.HSCore.components.HSDatatables.init($('#promotionsTable'), {
            "pageLength": 8,
            "columnDefs": [{
                "targets": [1, 2, 3, 4],
                "orderable": false
            }],
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "/SalesManagement/api/promotionsProduct/table",
                "async": false
            },
            "columns": [
                { "data": "id" },
                {
                    "data": "name",
                    "render": function (data, type, row, meta) {
                        if(row.status == 1){
                            return data;
                        } else {
                            return "<span style='color: red;'>" + data + "</span>";
                        }
                    }
                },
                { "data": "startDate" },
                { "data": "endDate" },
                { "data": "percentDiscount" }
            ]
        });
    });

</script>
