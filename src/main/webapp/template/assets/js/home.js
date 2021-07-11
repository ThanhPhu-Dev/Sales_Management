let table;
async function initTableData() {

    let url = `/SalesManagement/api/trang-chu`;
    axios.post(url)
        .then(function (response) {
            let i = 1;
            let modified = response.data.map(e => {
                return {
                    STT: i++,
                    Id: "HD"+e.id,
                    name: e.customer.name,
                    Discount: e.discount != null ? e.discount : '',
                    promotionName:  e.promotionCustomer?.name,
                    promotionpercent: e.promotionCustomer?.percentDiscount,
                    total: e.total,
                    createdAt: e.dateCreate,
                    detail: "<a href='/SalesManagement/Detailbill?id="+e.id+"'><i class=\"fas fa-info-circle\"></i></a>",
                }
            });

            table = $('#homeTable').DataTable({
                "pageLength": 10,
                "oLanguage": {
                    "sSearch": "",
                    "sLengthMenu": "Showing _MENU_ ",
                    "oPaginate": {
                        "sPrevious": "Trước đó",
                        "sNext": "Tiếp theo"
                    }
                },
                "lengthMenu": [[10, 15, 20], [10, 15, 20]],

                "bInfo" : false,
                initComplete : function() {
                    $("#datatablebill_filter").detach().appendTo('#new-search-area');
                },
                "dom": '<"top"i>rt<"bottom"flp><"clear">',
                data: modified,
                columns: [

                    { data: 'name' },
                    {
                        class: 'text-right',
                        data: 'total',
                    },
                    {
                        class: 'text-center',
                        data: 'createdAt'
                    },

                ]
            });
        })
        .catch(function (error) {
            console.log(error);
        })

}
$(document).ready(async function () {
    if(window.location.href === "http://localhost:8080/SalesManagement/trang-chu") {
        await initTableData();
    }
});
