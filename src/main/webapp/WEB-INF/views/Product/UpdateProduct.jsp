<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Cập nhật sản phẩm</h1>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->
        <form>
            <div class="row">
                <div class="col-lg">
                    <!-- Card -->
                    <input type="hidden" id="productId">
                    <div class="card mb-3 mb-lg-5">
                        <!-- Header -->
                        <div class="card-header">
                            <h4 class="card-header-title">Thông tin sản phẩm</h4>
                        </div>
                        <!-- End Header -->

                        <!-- Body -->
                        <div class="card-body">

                            <!-- product name -->
                            <div class="form-group">
                                <label for="productNameLabel" class="input-label">Tên sản phẩm</label>
                                <input type="text" class="form-control" name="productName" id="productNameLabel" aria-label="Shirt, t-shirts, etc." value="Phân bón NPK">
                            </div>
                            <!-- end product name -->

                            <div class="row">
                                <div class="col-sm-6">

                                    <!-- proudct type -->
                                    <div class="form-group">
                                        <label for="SKULabel" class="input-label">Quy cách</label>

                                        <input type="text" class="form-control" name="SKU" id="SKULabel" aria-label="" value="1000">
                                    </div>
                                    <!-- End proudct type -->
                                </div>

                                <div class="col-sm-6">
                                    <!-- product price -->
                                    <div class="form-group">
                                        <label for="weightLabel" class="input-label">Giá gốc</label>
                                        <div class="input-group input-group-merge">
                                            <input type="text" class="form-control" name="weightName" id="weightLabel" aria-label="0.0" value="2.000.000">
                                            <div class="input-group-append">
                                                <!-- Select -->
                                                <div id="weightSelect" class="select2-custom select2-custom-right">
                                                    <select class="js-select2-custom custom-select" size="1" style="opacity: 0;" data-hs-select2-options='{
                                                            "dropdownParent": "#weightSelect",
                                                            "dropdownAutoWidth": true,
                                                            "width": true
                                                            }'>
                                                        <option value="lb">EURA</option>
                                                        <option value="oz">USD</option>
                                                        <option value="kg" selected="">VNĐ</option>
                                                        <option value="g">GBP</option>
                                                    </select>
                                                </div>
                                                <!-- End Select -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End product price -->
                                </div>

                                <div class="col-sm-6">
                                    <label for="priceNameLabel" class="input-label">Ưu đãi</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="priceName" id="priceNameLabel" aria-label="0.00" value="5">
                                    </div>
                                </div>

                                <div class="col-sm-6 form-group">
                                    <label for="priceNameLabel" class="input-label">Chiết khấu</label>

                                    <div class="input-group">
                                        <input type="text" class="form-control" name="priceName" id="priceNameLabel" aria-label="0.00" value="10">

                                        <div class="input-group-append">
                                            <!-- Select -->
                                            <div id="priceSelect" class="select2-custom select2-custom-right">
                                                <select class="js-select2-custom custom-select" size="1" style="opacity: 0;" data-hs-select2-options='{
                                                        "dropdownParent": "#priceSelect",
                                                        "dropdownAutoWidth": true,
                                                        "width": true
                                                        }'>
                                                    <option value="USD" selected="">%</option>
                                                </select>
                                            </div>
                                            <!-- End Select -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- End Row --> 
                        </div>
                        <!-- Body -->
                    </div>
                    <!-- End Card -->
                </div>

            </div>
            <button type="button" class="btn btn-primary">Cập nhật sản phẩm</button>
        </form>
        <!-- End Row -->
    </div>
    <!-- End Content -->
</main>

