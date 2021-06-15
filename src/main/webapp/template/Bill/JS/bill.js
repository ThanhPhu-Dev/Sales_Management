let table;
async function initTableData() {

    let url = `/SalesManagement/api/bills`;
    axios.post(url)
        .then(function (response) {
            let i = 1;
            let modified = response.data.map(e => {
                return {
                    STT: i++,
                    Id: "HD"+e.id,
                    name: e.customer.name,
                    Discount: e.discount != null ? e.discount : '',
                    promotionName: e.promotion != null ? e.promotion.name : '',
                    promotionpercent: e.promotion != null ? e.promotion.percentDiscount : '',
                    total: e.total,
                    createdAt: e.dateCreate,
                    detail: "<a href='/SalesManagement/Detailbill?id="+e.id+"'><i class=\"fas fa-info-circle\"></i></a>",
                }
            });

            table = $('#datatablebill').DataTable({
                "pageLength": 5,
                "oLanguage": {
                    "sSearch": "",
                    "sLengthMenu": "Showing _MENU_ ",
                    "oPaginate": {
                        "sPrevious": "Trước đó",
                        "sNext": "Tiếp theo"
                    }
                },
                "lengthMenu": [[5, 10, 15], [5, 10, 15]],
                language: {
                    searchPlaceholder: "Tìm kiếm hóa đơn"
                },
                "bInfo" : false,
                initComplete : function() {
                    $("#datatablebill_filter").detach().appendTo('#new-search-area');
                },
                "dom": '<"top"i>rt<"bottom"flp><"clear">',
                data: modified,
                columns: [
                    {
                        class: 'text-center',
                        data: 'STT',
                    },
                    { data: 'Id' },
                    { data: 'name' },
                    {
                        class: 'text-center',
                        data: 'Discount'
                    },
                    { data: 'promotionName' },
                    {
                        class: 'text-center',
                        data: 'promotionpercent'
                    },
                    {
                        class: 'text-right',
                        data: 'total'
                    },
                    {
                        class: 'text-center',
                        data: 'createdAt'
                    },
                    {
                        class: 'text-center',
                        data: 'detail'
                    },
                ]
            });
        })
        .catch(function (error) {
            console.log(error);
        })

}
$(document).ready(async function () {
    if(window.location.href === "http://localhost:8080/SalesManagement/bills") {
        await initTableData();
    }
});
