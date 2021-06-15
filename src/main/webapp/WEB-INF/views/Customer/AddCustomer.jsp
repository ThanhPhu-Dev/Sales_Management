<%-- 
    Document   : AddCustomer
    Created on : Jun 10, 2021, 8:23:04 AM
    Author     : NghiaDX
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Thêm khách hàng</title>
<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <div class="container">
            <div class="row">
                <div class=" col-2"></div>
                <div class=" col-8">
                    <div class="tilte-primary d-flex align-items-start"> 
                        <i class="fas fa-id-card" style="font-size: 65px; margin-right: 40px;"></i>
                        <div class="d-flex flex-column align-items-start">
                            <h1>Tài khoản khách hàng</h1>
                            <span>Quản lý thông tin cá nhận và thông tin tiền tệ của khách hàng tại đây </span>   
                        </div>

                    </div>
                    <form id="form-apply" style="margin: 30px 0 0 0;">
                        <div class="cus__info-lable">
                            Thông tin cá nhân
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">HỌ TÊN</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" name="name" id="name" type="text" placeholder="Nguyễn Văn A" required>
                                <span class="focus-border"></span>
                            </div>
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">ƯU ĐÃI</label>
                            <div class="input-group">
                                <select name="promotion" id="promotion" class="form-select select-primary" >
                                    <option selected value="-1">Không áp dụng</option>
                                    <c:forEach var="promotion" items="${proCus}">
                                        <option value="${promotion.getId()}">${promotion.getName()}</option>
                                    </c:forEach>
                                    <!--                                <option selected>Open this select menu</option>
                                                                    <option value="1">One</option>
                                                                    <option value="2">Two</option>
                                                                    <option value="3">Three</option>-->
                                </select>
                            </div>
                        </div>

                        <div class="cus__info-lable">
                            Thông tin thanh toán
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">SỐ TÀI KHOẢN</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" name="card" id="card" type="text" placeholder="123456789"
                                       minlength="1" maxlength="16" required>
                                <span class="focus-border"></span>
                            </div>
                            <span class="error-card" style="color: red; display: none">Số tài khoản đã tồn tại</span>
                        </div>
                        <!--                        <div class="mb-3 cus__form-group">
                                                    <label for="name" class="form-label cus__form-lable">SỐ DƯ TÀI KHOẢN</label>
                                                    <div class="input-group">
                                                        <input class="input-effect input-primary" type="text" >
                                                        <span class="focus-border"></span>
                                                    </div>
                                                </div>-->
                        <div class="d-flex justify-content-center" style="margin: 20px 0 50px 0; ">
                            <button type="submit" id="btnApply" class="btn btn-secondary">Xác nhận</button>
                        </div>
                    </form>
                </div>
                <div class=" col-2"></div>


            </div>
        </div>
    </div>
    <!-- End Content -->

</main>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!--<script src="sweetalert2.all.min.js"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">-->

<script>
    const formApply = document.querySelector("#form-apply");
    const btnApply = document.querySelector('#btnApply');
    const ipSearch = document.querySelector('#name');
    const errorCard = document.querySelector('.error-card');

    const setDefault = {
        setInput: function () {
            const ipName = document.querySelector('#name');
            const selectPromotion = document.querySelector('#promotion');
            const ipCard = document.querySelector('#card');
            ipName.value = '';
            selectPromotion.value = '';
            ipCard.value = '';
        },
        setError: function () {
            errorCard.innerHTML = "";
            errorCard.style.display = "none";
        }
    };

    formApply.addEventListener("submit", async e => {
        e.preventDefault();
        let formData = new FormData(formApply);
        const name = formData.get("name");
        const card = formData.get("card");
        const promotion = formData.get("promotion");

        setDefault.setError();

        await axios.post('/SalesManagement/api/addcustomer', {
            name: formData.get("name"),
            card: formData.get("card"),
            promotion: formData.get("promotion"),
        }).then(function (response) {

            if (response.data.card) {
                errorCard.innerHTML = response.data.card;
                errorCard.style.display = "block";
            } else {
                setDefault.setError();
                setDefault.setInput();

                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Thêm khách hàng thành công',
                    showConfirmButton: false,
                    timer: 1000
                })
            }
        }).catch(async function (error) {
            await Swal.fire('CÓ lỗi xảy ra, vui lòng thử lại!');
        });
    }, true);

</script>
