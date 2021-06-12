<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link rel="stylesheet" href="<c:url value='/template/style.css' />" />
        <link rel="stylesheet" href="<c:url value='/template/customer.css' />" />
        <!--bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

        <!--datatable-->
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.css" />
        <link rel="stylesheet" type="text/css"
              href="https://cdn.datatables.net/1.10.25/css/dataTables.semanticui.css" />

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
        <script type="text/javascript"
        src="https://cdn.datatables.net/1.10.25/js/dataTables.semanticui.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        
        <title>
            <dec:title default="Trang chủ" />
        </title>
    </head>

    <body>
        <!-- header -->
        <%@ include file="/common/header.jsp" %>
        <!-- header -->
        <div style="display: flex">
            <!-- menu left -->
            <%@ include file="/common/leftmenu.jsp" %>
            <!-- menu left -->
            <!-- content -->
            <dec:body />
            <!-- /content -->
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable({
                    "language": {
                        "lengthMenu": "Số dòng  _MENU_ ",
                        "info": "Trang _PAGE_ / _PAGES_ ",
                        "search": "",
                        "paginate": {
                            "previous": "Trước đó",
                            "next": "Tiếp theo",
                        },
                        searchPlaceholder: "Tìm kiếm",
                    }
                });
            });
        </script>
    </body>

</html>