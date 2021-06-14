<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Trang chủ</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Card -->
        <div class="card">
            <!-- Flatpickr -->
            <form class="form-inline">
                <div class="form-group mb-2">
                    <div class="card-header">
                        <h4 class="card-header-title">Lịch bán hàng</h4>
                    </div>
                </div>
                <div class="form-group mx-sm-3 mb-2">
                    <label for="date" class="date-statis">Chọn ngày </label>
                    <input type="text" class="js-daterangepicker form-control daterangepicker-custom-input"
                           data-hs-daterangepicker-options='{
                           "autoApply": true
                           }'>
                </div>
                <button type="submit" class="btn btn-primary mb-2">Thống kê</button>
            </form>
            <!-- End Flatpickr -->
            <!-- Table -->
            <div class="table-responsive">
                <table class="table table-lg table-borderless table-thead-bordered table-nowrap table-align-middle">
                    <thead class="thead-light">
                        <tr>
                            <th>Tên khách hàng</th>
                            <th>01/04/2021</th>
                            <th>02/04/2021</th>
                            <th>03/04/2021</th>
                            <th>04/04/2021</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- End Table -->
        </div>
        <!-- End Card -->
    </div>
    <!-- End Content -->

</main>