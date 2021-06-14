let table;
async function initTableData() {
    let url = `/SalesManagement/api/bills`;
    axios.post(url)
        .then(function (response) {
            var i = 1;
            var modified = response.data.map(e => {
                return {
                    STT: i++,
                    name: e.customer.name,
                    Discount: e.discount != null ? e.discount : '',
                    promotionName: e.promotion.name != null ? e.promotion.name : '',
                    promotionpercent: e.promotion.percentDiscount != null ? e.promotion.percentDiscount : '',
                    total: e.total,
                    createdAt: e.dateCreate,
                    detail: "<i class=\"fas fa-info-circle\" data-toggle=\"modal\" data-target=\"#detailbill\"></i>",
                }
            });

            table = $('#datatablebill').DataTable({
                "pageLength": 1,
                "oLanguage": {
                    "sSearch": "",
                    "sLengthMenu": "Showing _MENU_ ",
                    "oPaginate": {
                        "sPrevious": "Trước đó",
                        "sNext": "Tiếp theo"
                    }
                },
                language: {
                    searchPlaceholder: "Tìm kiếm hóa đơn"
                },
                "bInfo" : false,
                initComplete : function() {
                    $("#datatablebill_filter").detach().appendTo('#new-search-area');
                    // $("#datatablebill_length").detach().appendTo('#new-lengthbill-datable');
                },
                "dom": '<"top"i>rt<"bottom"flp><"clear">',
                data: modified,
                columns: [
                    {
                        data: 'STT',
                    },
                    { data: 'name' },
                    { data: 'Discount' },
                    { data: 'promotionName' },
                    { data: 'promotionpercent' },
                    { data: 'total' },
                    { data: 'createdAt' },
                    { data: 'detail' },
                ]
            });
        })
        .catch(function (error) {
            console.log(error);
        })

}
$(document).ready(async function () {
    await initTableData();

    // $('#element1_id').attr('placeholder','Some New Text 1');
});
