<%-- 
    Document   : AddCustomer
    Created on : Jun 10, 2021, 8:23:04 AM
    Author     : NghiaDX
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>them KH</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <div class="tilte-primary d-flex align-items-start"> 
                        <i class="fas fa-id-card" style="font-size: 65px; margin-right: 40px;"></i>
                        <div class="d-flex flex-column align-items-start">
                         <h1>Tài khoản khách hàng</h1>
                        <span>Quản lý thông tin cá nhận và thông tin tiền tệ của khách hàng tại đây </span>   
                        </div>
                        
                    </div>
                    <form style="margin: 30px 0 0 0;">
                        <div class="cus__info-lable">
                            Thông tin cá nhân
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">HỌ TÊN</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" type="text" >
                                <span class="focus-border"></span>
                            </div>
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">SỐ ĐIỆN THOẠI</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" type="text" placeholder="Số điện thoại">
                                <span class="focus-border"></span>
                            </div>
                        </div>
                        <div class="cus__info-lable">
                            Thông tin tiền tệ
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">SỐ TÀI KHOẢN</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" type="text" >
                                <span class="focus-border"></span>
                            </div>
                        </div>
                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">SỐ DƯ TÀI KHOẢN</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" type="text" >
                                <span class="focus-border"></span>
                            </div>
                        </div>
<!--                        <div class="mb-3 cus__form-group">
                            <label for="name" class="form-label cus__form-lable">CÔNG NỢ</label>
                            <div class="input-group">
                                <input class="input-effect input-primary" type="text">
                                <span class="focus-border"></span>
                            </div>
                        </div>-->
                        <div class="d-flex justify-content-center" style="margin: 20px 0 50px 0; ">
                            <button type="button" class="btn btn-secondary">Xác nhận</button>
                        </div>
                    </form>
                </div>
                <div class="col-3"></div>


            </div>
        </div>
    </body>
</html>
