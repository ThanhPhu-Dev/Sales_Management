<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Thêm sản phẩm</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Thêm sản phẩm</h1>
                </div>
            </div>
            <!-- End Row -->
        </div>
        <!-- End Page Header -->
        <form method="POST" id="addProductForm">
            <div class="row">
                <div class="col-lg">
                    <!-- Card -->
                    <div class="card mb-3 mb-lg-5">
                        <!-- Header -->
                        <div class="card-header">
                            <h4 class="card-header-title">Thông tin sản phẩm</h4>
                        </div>
                        <!-- End Header -->

                        <!-- Body -->
                        <div class="card-body">

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="sku" class="input-label">SKU</label>
                                        <input type="text" class="form-control" name="sku" id="sku" placeholder="SKU" >
                                    </div>
                                    <span id="error-sku" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="name" class="input-label">Tên sản phẩm</label>
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Tên sản phẩm" >
                                    </div>
                                    <span id="error-name" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-sm-6">

                                    <div class="form-group">
                                        <label for="specification" class="input-label">Quy cách</label>

                                        <div class="input-group input-group-merge">
                                            <input type="number" class="form-control" name="specification" id="specification" placeholder="0" aria-label="0.0">
                                            <div class="input-group-append">
                                                <!-- Select -->
                                                <div id="specificationSelect" class="select2-custom select2-custom-right">
                                                    <select class="js-select2-custom custom-select" size="1" style="opacity: 0;" data-hs-select2-options='{
                                                            "dropdownParent": "#specificationSelect",
                                                            "dropdownAutoWidth": true,
                                                            "width": true
                                                            }'>
                                                        <option value="" selected>KG</option>
                                                    </select>
                                                </div>
                                                <!-- End Select -->
                                            </div>
                                        </div>
                                    </div>
                                    <span id="error-specification" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>

                                </div>

                                <div class="col-sm-6">

                                    <div class="form-group">
                                        <label for="historicalCost" class="input-label">Giá gốc</label>

                                        <div class="input-group input-group-merge">
                                            <input type="number" class="form-control" name="historicalCost" id="historicalCost" placeholder="0" aria-label="0.0">
                                            <div class="input-group-append">
                                                <!-- Select -->
                                                <div id="historicalCostSelect" class="select2-custom select2-custom-right">
                                                    <select class="js-select2-custom custom-select" size="1" style="opacity: 0;" data-hs-select2-options='{
                                                            "dropdownParent": "#historicalCostSelect",
                                                            "dropdownAutoWidth": true,
                                                            "width": true
                                                            }'>
                                                        <option value="" selected>VNĐ</option>
                                                    </select>
                                                </div>
                                                <!-- End Select -->
                                            </div>
                                        </div>
                                    </div>
                                    <span id="error-historicalCost" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>

                                </div>

                            </div>

                            <div class="row">

                                <div class="col-sm-6">

                                    <div class="form-group">
                                        <label for="tradeDiscount" class="input-label">Chiết khấu</label>

                                        <div class="input-group input-group-merge">
                                            <input type="number" class="form-control" name="tradeDiscount" id="tradeDiscount" placeholder="0" aria-label="0.0">
                                            <div class="input-group-append">
                                                <!-- Select -->
                                                <div id="tradeDiscountSelect" class="select2-custom select2-custom-right">
                                                    <select class="js-select2-custom custom-select" size="1" style="opacity: 0;" data-hs-select2-options='{
                                                            "dropdownParent": "#tradeDiscountSelect",
                                                            "dropdownAutoWidth": true,
                                                            "width": true
                                                            }'>
                                                        <option value="" selected>%</option>
                                                    </select>
                                                </div>
                                                <!-- End Select -->
                                            </div>
                                        </div>
                                    </div>
                                    <span id="error-tradeDiscount" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>

                                </div>

                                <div class="col-sm-6">

                                    <div class="form-group">
                                        <label for="promotionsId" class="input-label">Chương trình ưu đãi</label>

                                        <select class="js-select2-custom custom-select" name="promotionsId" id="promotionsId" size="1" style="opacity: 0;"
                                                data-hs-select2-options='{
                                          "placeholder": "Chọn chương trình ưu đãi",
                                          "searchInputPlaceholder": "Tìm chương trình ưu đãi"
                                        }'>
                                        </select>
                                    </div>
                                    <span id="error-promotionsId" style="color: #ff0000; display: none; margin-top: -10px; margin-bottom: 10px;"></span>

                                </div>

                            </div>

                        </div>
                        <!-- Body -->
                    </div>
                    <!-- End Card -->
                </div>

            </div>
            <button type="button" id="btnSubmit" class="btn btn-primary">Thêm sản phẩm</button>
        </form>
        <!-- End Row -->
    </div>
    <!-- End Content -->
</main>

<script>

    // Lấy danh sách chương trình khuyến mãi
    document.addEventListener('DOMContentLoaded', async () => {
        const rs = await axios.get('/SalesManagement/api/promotionsProduct')
            .then((res) => {
                let html = res.data.map((promotionProduct) => {
                    if(promotionProduct.status == 1) {
                        return '<option value="' + promotionProduct.id + '">' +
                            promotionProduct.name +  ' (' + promotionProduct.percentDiscount + '%)' +
                            '</option>';
                    }
                }).join(' ');

                $('#promotionsId').append(html);
            });
    });

    // Thêm file js vào cuối body
    var script = document.createElement('script');
    document.body.appendChild(script);
    script.src = '<c:url value='/template/assets/js/product.js' />';

    // Xử lý trước khi submit
    $('#btnSubmit').on('click', async function () {

        const specification = $('#specification').val();
        const tradeDiscount = $('#tradeDiscount').val();

        if(!isNumeric(specification)) {
            $('#error-specification').text('Quy cách phải là số hoặc không được bỏ trống').css('display', 'block');
            $('#specification').css('borderBottom', '1px solid red');
        } else if(!isNumeric(tradeDiscount)) {
            $('#error-tradeDiscount').text('Chiết khấu phải là số hoặc không được bỏ trống').css('display', 'block');
            $('#tradeDiscount').css('borderBottom', '1px solid red');
        } else {
            await axios.post('/SalesManagement/api/product', {
                sku: $('#sku').val(),
                name: $('#name').val(),
                specification: specification,
                historicalCost: parseInt($('#historicalCost').val().replace(/\D/g,''), 10),
                tradeDiscount: tradeDiscount,
                promotionsId: $('#promotionsId').val(),
            }).then((res) => {
                Swal.fire({
                    title: 'Thêm sản phẩm thành công',
                    html:
                        'SKU: ' + res.data.sku +
                        '<br>Tên sản phẩm: ' + res.data.name +
                        '<br>Quy cách (KG): ' + res.data.specification +
                        '<br>Giá gốc: ' + res.data.historicalCost.toLocaleString("it-IT") +
                        '<br>Chiết khấu (%): ' + res.data.tradeDiscount +
                        '<br>Chương trình khuyến mãi: ' + $('#promotionsId option:selected').text(),
                    icon: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#A5DC86',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.reload();
                    }
                })
            }).catch((error) => {
                removeErrors();
                const errors = error.response.data;
                setErrors(errors);
            });
        }
    });

</script>