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
                                <input class="input-effect input-primary" name="name" id="name" type="text" 
                                       placeholder="Nguyễn Văn A" required minlength="5" maxlength="50">
                                <span class="focus-border"></span>
                            </div>
                            <span class="error-name" style="color: red; display: none"></span>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="mb-3 cus__form-group">
                                    <label for="name" class="form-label cus__form-lable">Chứng minh nhân dân</label>
                                    <div class="input-group">
                                        <input class="input-effect input-primary" name="identity" id="identity" type="text" 
                                               placeholder="CMND hoặc số căn cước công dân" required minlength="9" maxlength="12">
                                        <span class="focus-border"></span>
                                    </div>
                                    <span class="error-identity" style="color: red; display: none"></span>
                                </div>
                            </div>
                            <div class="col">
                                <div class="mb-3 cus__form-group">
                                    <label for="name" class="form-label cus__form-lable">Số điện thoại</label>
                                    <div class="input-group">
                                        <input class="input-effect input-primary" name="phone" id="phone" type="text" 
                                               placeholder="Số điện thoại" required minlength="10" maxlength="10">
                                        <span class="focus-border"></span>
                                    </div>
                                    <span class="error-phone" style="color: red; display: none"></span>
                                </div>
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
                                       minlength="8" maxlength="20" required>
                                <span class="focus-border"></span>
                            </div>
                            <span class="error-card" style="color: red; display: none">Số tài khoản đã tồn tại</span>
                        </div>
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

<script>
    const formApply = document.querySelector("#form-apply");
    const btnApply = document.querySelector('#btnApply');
    const errorCard = document.querySelector('.error-card');
    const errorPhone = document.querySelector('.error-phone');
    const errorIdentity = document.querySelector('.error-identity');
    const errorName = document.querySelector('.error-name');

    const ipCard = document.querySelector('#card');
    const ipPhone = document.querySelector('#phone');
    const ipIdentity = document.querySelector('#identity');
    const ipName = document.querySelector('#name');

    const setDefault = {
        setInput: function () {
            const ipName = document.querySelector('#name');
            const selectPromotion = document.querySelector('#promotion');

            ipName.value = '';
            selectPromotion.value = '-1';
            ipCard.value = '';
            ipPhone.value = '';
            ipIdentity.value = '';
            ipName.value = '';
        },
        setRemoveError: function () {
            errorCard.innerHTML = "";
            errorCard.style.display = "none";
            ipCard.style.borderBottom = "1px solid #ccc";

            errorPhone.innerHTML = "";
            errorPhone.style.display = "block";
            ipPhone.style.borderBottom = "1px solid #ccc";

            errorIdentity.innerHTML = "";
            errorIdentity.style.display = "block";
            ipIdentity.style.borderBottom = "1px solid #ccc";
            
            errorName.innerHTML = "";
            errorName.style.display = "block";
            ipName.style.borderBottom = "1px solid #ccc";
        },
        setError: function ({cardError, phoneError, identityError, nameError}) {
            if (cardError) {
                errorCard.innerHTML = cardError;
                errorCard.style.display = "block";
                ipCard.style.borderBottom = "1px solid red";
            }
            if (phoneError) {
                errorPhone.innerHTML = phoneError;
                errorPhone.style.display = "block";
                ipPhone.style.borderBottom = "1px solid red";
            }
            if (identityError) {
                errorIdentity.innerHTML = identityError;
                errorIdentity.style.display = "block";
                ipIdentity.style.borderBottom = "1px solid red";
            }
            if (nameError) {
                errorName.innerHTML = nameError;
                errorName.style.display = "block";
                ipName.style.borderBottom = "1px solid red";
            }
        }
    };

    formApply.addEventListener("submit", async e => {
        e.preventDefault();
        let formData = new FormData(formApply);
        const name = formData.get("name");
        const card = formData.get("card");
        const promotion = formData.get("promotion");

        setDefault.setRemoveError();

        await axios.post('/SalesManagement/api/addcustomer', {
            name: formData.get("name"),
            card: formData.get("card"),
            promotion: formData.get("promotion"),
            phone: formData.get("phone"),
            identity: formData.get("identity"),
        }).then(function (response) {
            const {cardError, phoneError, identityError, nameError} = response.data;

            //nếu tồn tại lỗi xuất UI thông báo
            if (cardError || phoneError || identityError || nameError) {
                setDefault.setError(response.data);
            } else {
                //xóa hết lỗi trc khi thông báo thành công 
                setDefault.setRemoveError();
                setDefault.setInput();

                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Thêm khách hàng thành công',
                    showConfirmButton: false,
                    timer: 1000
                });
            }
        }).catch(async function (error) {
            await Swal.fire('CÓ lỗi xảy ra, vui lòng thử lại!');
        });
    }, true);

</script>
