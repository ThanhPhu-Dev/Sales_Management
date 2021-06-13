<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>

<!--datatable-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>

<!-- JS Implementing Plugins -->
<script type="text/javascript" src="<c:url value='/template/assets/js/vendor.min.js' />"></script>

<script type="text/javascript"
src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
<script type="text/javascript"
src="https://cdn.datatables.net/1.10.25/js/dataTables.semanticui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<!-- JS Front -->
<script type="text/javascript" src="<c:url value='/template/assets/js/theme.min.js' />"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
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
    });
</script>

<script>
    $(document).on('ready', function () {
        // ONLY DEV
        // =======================================================

        if (window.localStorage.getItem('hs-builder-popover') === null) {
            $('#builderPopover').popover('show')
                    .on('shown.bs.popover', function () {
                        $('.popover').last().addClass('popover-dark')
                    });

            $(document).on('click', '#closeBuilderPopover', function () {
                window.localStorage.setItem('hs-builder-popover', true);
                $('#builderPopover').popover('dispose');
            });
        } else {
            $('#builderPopover').on('show.bs.popover', function () {
                return false
            });
        }

        // END ONLY DEV
        // =======================================================


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


        // INITIALIZATION OF UNFOLD
        // =======================================================
        $('.js-hs-unfold-invoker').each(function () {
            var unfold = new HSUnfold($(this)).init();
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


        // INITIALIZATION OF DROPZONE FILE ATTACH MODULE
        // =======================================================
        $('.js-dropzone').each(function () {
            var dropzone = $.HSCore.components.HSDropzone.init('#' + $(this).attr('id'));
        });

        // INITIALIZATION OF FLATPICKR
        // =======================================================
        $('.js-flatpickr').each(function () {
            $.HSCore.components.HSFlatpickr.init($(this));
        });

        // INITIALIZATION OF BUBBLE CHARTJS WITH DATALABELS PLUGIN
        // =======================================================
        $('.js-chart-datalabels').each(function () {
            $.HSCore.components.HSChartJS.init($(this), {
                plugins: [ChartDataLabels],
                options: {
                    plugins: {
                        datalabels: {
                            anchor: function (context) {
                                var value = context.dataset.data[context.dataIndex];
                                return value.r < 20 ? 'end' : 'center';
                            },
                            align: function (context) {
                                var value = context.dataset.data[context.dataIndex];
                                return value.r < 20 ? 'end' : 'center';
                            },
                            color: function (context) {
                                var value = context.dataset.data[context.dataIndex];
                                return value.r < 20 ? context.dataset.backgroundColor : context.dataset.color;
                            },
                            font: function (context) {
                                var value = context.dataset.data[context.dataIndex],
                                        fontSize = 25;

                                if (value.r > 50) {
                                    fontSize = 35;
                                }

                                if (value.r > 70) {
                                    fontSize = 55;
                                }

                                return {
                                    weight: 'lighter',
                                    size: fontSize
                                };
                            },
                            offset: 2,
                            padding: 0
                        }
                    }
                },
            });
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

</body>

</html>