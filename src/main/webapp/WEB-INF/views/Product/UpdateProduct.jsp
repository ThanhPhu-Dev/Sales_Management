<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<title>Cập nhật sản phẩm</title>
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
                                            <input type="text" class="form-control" name="historicalCost" id="historicalCost" placeholder="0" aria-label="0.0">
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
            <button type="button" id="btnSubmit" class="btn btn-primary">Cập nhật sản phẩm</button>
        </form>
        <!-- End Row -->
    </div>
    <!-- End Content -->
</main>

<script>

    const pathArray = window.location.pathname.split('/');

    var expiredPromotions;
    // Lấy dữ liệu ban đầu
    document.addEventListener('DOMContentLoaded', async () => {
        // Lấy danh sách chương trình khuyến mãi
        await axios.get('/SalesManagement/api/promotionsProduct')
            .then((res) => {
                let html = res.data.map((promotionProduct) => {
                    if(promotionProduct.status == 1) {
                        return '<option value="' + promotionProduct.id + '">' +
                            promotionProduct.name +  ' (' + promotionProduct.percentDiscount + '%)' +
                            '</option>';
                    }
                }).join(' ');

                $('#promotionsId').append('<option value="0"> Không áp dụng </option>' + html);
            });

        // Lấy thông tin sản phẩm
        await axios.get('/SalesManagement/api/product/' + pathArray.slice(-1))
            .then((res) => {
                $('#sku').val(res.data.sku);
                $('#name').val(res.data.name);
                $('#specification').val(res.data.specification);
                $('#historicalCost').val(res.data.historicalCost.toLocaleString("it-IT"));
                $('#tradeDiscount').val(res.data.tradeDiscount);

                const promotions = res.data.promotions;
                if(promotions != null) {
                    if(promotions.status == 1) {
                        $('#promotionsId').val(promotions.id).trigger('change');
                    } else {
                        let html = ' <option data-option-template="<span class=text-danger>' + promotions.name +  ' (' + promotions.percentDiscount + '%) - ' + 'Đã hết hạn ưu đãi'
                            + '</span>" value="' + promotions.id + '">'
                            + promotions.name +  ' (' + promotions.percentDiscount + '%) - ' + 'Đã hết hạn ưu đãi'
                            + '</option>';

                        $('#promotionsId').append(html);
                        $('#promotionsId').val(promotions.id).trigger('change');
                        expiredPromotions = promotions.id;
                    }
                }
            }).catch((error) => {
                Swal.fire({
                    icon: 'error',
                    title: 'Có lỗi xảy ra',
                    text: 'Sản phẩm không tồn tại!',
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location = '/SalesManagement/product';
                    }
                });
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
        } else if($('#promotionsId option:selected').val() == expiredPromotions) {
            $('#error-promotionsId').text('Vui lòng chọn chương trình ưu đãi còn hiệu lực').css('display', 'block');
            $('#promotionsId').css('borderBottom', '1px solid red');
        } else {
            await axios.put('/SalesManagement/api/product/' + pathArray.slice(-1), {
                sku: $('#sku').val(),
                name: $('#name').val(),
                specification: specification,
                historicalCost: parseInt($('#historicalCost').val().replace(/\D/g,''), 10),
                tradeDiscount: tradeDiscount,
                promotionsId: $('#promotionsId').val() == 0 ? null : $('#promotionsId').val(),
            }).then((res) => {
                Swal.fire({
                    title: 'Cập nhật sản phẩm thành công',
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