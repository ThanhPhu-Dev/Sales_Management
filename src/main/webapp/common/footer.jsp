<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!--datatable-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
      type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js" defer></script>
    <%--axios--%>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- JS Implementing Plugins -->
<script type="text/javascript" src="<c:url value='/template/assets/js/vendor.min.js' />"></script>

<%--BillJS--%>
<script type="text/javascript" src="<c:url value='/template/Bill/JS/bill.js' />"></script>

<script type="text/javascript"
src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.js"></script>
<script type="text/javascript"
src="https://cdn.datatables.net/1.10.25/js/dataTables.semanticui.js"></script>

<script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/dataTables.semanticui.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<!-- JS Front -->
<script type="text/javascript" src="<c:url value='/template/assets/js/theme.min.js' />"></script>

<!--message-->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="sweetalert2.all.min.js"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">

<script>
    $(document).on('ready', function () {

        $('#example').DataTable({
            "searching": false,
            "lengthChange": false,
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

        // BUILDER TOGGLE INVOKER
        // =======================================================
        $('.js-navbar-vertical-aside-toggle-invoker').click(function () {
            $('.js-navbar-vertical-aside-toggle-invoker i').tooltip('hide');
        });



        // INITIALIZATION OF MEGA MENU
        // =======================================================
        var megaMenu = new HSMegaMenu($('.js-mega-menu'), {
            desktop: {
                position: 'left'
            }
        }).init();



        // INITIALIZATION OF NAVBAR VERTICAL NAVIGATION
        // =======================================================
        var sidebar = $('.js-navbar-vertical-aside').hsSideNav();


        // INITIALIZATION OF TOOLTIP IN NAVBAR VERTICAL MENU
        // =======================================================
        $('.js-nav-tooltip-link').tooltip({boundary: 'window'})

        $(".js-nav-tooltip-link").on("show.bs.tooltip", function (e) {
            if (!$("body").hasClass("navbar-vertical-aside-mini-mode")) {
                return false;
            }
        });

        // INITIALIZATION OF FORM SEARCH
        // =======================================================
        $('.js-form-search').each(function () {
            new HSFormSearch($(this)).init()
        });


        // INITIALIZATION OF SELECT2
        // =======================================================
        $('.js-select2-custom').each(function () {
            var select2 = $.HSCore.components.HSSelect2.init($(this));
        });


        // INITIALIZATION OF FLATPICKR
        // =======================================================
        $('.js-flatpickr').each(function () {
            $.HSCore.components.HSFlatpickr.init($(this));
        });

        // INITIALIZATION OF DATERANGEPICKER
        // =======================================================
        $('.js-daterangepicker').daterangepicker();

        $('.js-daterangepicker-times').daterangepicker({
            timePicker: true,
            startDate: moment().startOf('hour'),
            endDate: moment().startOf('hour').add(32, 'hour'),
            locale: {
                format: 'M/DD hh:mm A'
            }
        });

        var start = moment();
        var end = moment();

        function cb(start, end) {
            $('#js-daterangepicker-predefined .js-daterangepicker-predefined-preview').html(start.format('MMM D') + ' - ' + end.format('MMM D, YYYY'));
        }

        $('#js-daterangepicker-predefined').daterangepicker({
            startDate: start,
            endDate: end,
            ranges: {
                'Hôm Nay': [moment(), moment()],

            }
        }, cb);

        cb(start, end);


        // INITIALIZATION OF DATATABLES
        // =======================================================
        var datatable = $.HSCore.components.HSDatatables.init($('#datatable'), {
            select: {
                style: 'multi',
                selector: 'td:first-child input[type="checkbox"]',
                classMap: {
                    checkAll: '#datatableCheckAll',
                    counter: '#datatableCounter',
                    counterInfo: '#datatableCounterInfo'
                }
            },
            language: {
                zeroRecords: '<div class="text-center p-4">' +
                        '<img class="mb-3" src="/admin/assets/svg/illustrations/sorry.svg" alt="Image Description" style="width: 7rem;">' +
                        '<p class="mb-0">No data to show</p>' +
                        '</div>'
            }
        });

        $('.js-datatable-filter').on('change', function () {
            var $this = $(this),
                    elVal = $this.val(),
                    targetColumnIndex = $this.data('target-column-index');

            datatable.column(targetColumnIndex).search(elVal).draw();
        });

        $('#datatableSearch').on('mouseup', function (e) {
            var $input = $(this),
                    oldValue = $input.val();

            if (oldValue == "")
                return;

            setTimeout(function () {
                var newValue = $input.val();

                if (newValue == "") {
                    // Gotcha
                    datatable.search('').draw();
                }
            }, 1);
        });


        // INITIALIZATION OF CLIPBOARD
        // =======================================================
        $('.js-clipboard').each(function () {
            var clipboard = $.HSCore.components.HSClipboard.init(this);
        });

        $('a.btn.btn-sm.btn-danger').click(function () {
            var href = $(this).attr('href');
            $('#btnConfirm').attr('href', href);
        });

        $('#checkoutFinishBtn').click(function () {
            $("#checkoutStepFormProgress").hide();
            $("#checkoutStepFormContent").hide();
            $("#checkoutStepOrderSummary").hide();
            $("#checkoutStepSuccessMessage").show();
        });

        $('#confirmPaymentFinish').click(function () {
            $("#addUserStepFormProgress").hide();
            $("#confirmPaymentFinish").css("display", "none");
            $("#checkoutStepSuccessMessage").show();
        });
    });

</script>
