<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Trang chủ</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Card -->
        <div class="card">
            <!-- Flatpickr -->
            <form class="form-inline" id="formDate" method="get">
                <div class="form-group mb-2">
                    <div class="card-header">
                        <h4 class="card-header-title">Lịch bán hàng</h4>
                    </div>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                    <label class="date-statis">Chọn tháng</label>
                    <select name="month" id="month" class="form-control">
                        <option value="1"> 1 </option>
                        <option value="2"> 2 </option>
                        <option value="3"> 3 </option>
                        <option value="4"> 4 </option>
                        <option value="5"> 5 </option>
                        <option value="6" selected> 6 </option>
                        <option value="7"> 7 </option>
                        <option value="8"> 8 </option>
                        <option value="8"> 8 </option>
                        <option value="10"> 10 </option>
                        <option value="11"> 11 </option>
                        <option value="12"> 12 </option>
                    </select>
                    <label class="date-statis">Chọn năm</label>
                    <select name="year" id="year" class="form-control">
                        <option value="2019"> 2019 </option>
                        <option value="2020"> 2020 </option>
                        <option value="2021" selected> 2021 </option>
                    </select>
                </div>
                <button id="btnSubmit" type="submit" class="btn btn-primary mb-2">Thống kê</button>
            </form>
            <!-- End Flatpickr -->
            <!-- Table -->
            <div class="table-responsive datatable-custom">
                <table id="homeTable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table" data-hs-datatables-options="{
                       &quot;columnDefs&quot;: [{
                            &quot;targets&quot;: [0, 1, 2],
                            &quot;orderable&quot;: false
                       }]
                       }">
                    <thead class="thead-light">
                        <tr>
                            <th>Tên khách hàng</th>
                            <th>Ngày bán hàng</th>
                            <th>Tổng tiền</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <!-- End Table -->

        </div>
        <!-- End Card -->
    </div>
    <!-- End Content -->

</main>

<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>--%>
<%--<script src="<c:url value='/template/assets/js/home.js' />"></script>--%>
<script>
    var hometable;
    document.addEventListener('DOMContentLoaded', () => {
        hometable = $.HSCore.components.HSDatatables.init($('#homeTable'), {
            "pageLength": 8,
            "columnDefs": [{
                "targets": [1, 2],
                "orderable": true
            }],
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "/SalesManagement/api/trang-chu",
                "type": "GET",
                "async": false,
                "data": function (d) {
                    d.month = $('#month').val();
                    d.year = $('#year').val();
                }
            },
            "columns": [
                { "data": "customer.name" },
                { "data": "dateCreate" },
                { "data": "total" }
                ]
        });
    });

    $('#btnSubmit').on('click', async function (e) {
        e.preventDefault();
        await hometable.ajax.reload();
    });

</script>