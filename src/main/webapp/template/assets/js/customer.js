const state = {
    customersTableId: 'customerTable',
    formSearchId: 'form-search',
    numberSearchId: 'numberSearch',
}

const pagination = {
    containerId: 'checkout-products-pagination',
    currentPage: 1,
    totalPage: 1,
    offset: 0,
    limit: 7,
    pageNumberShow: 5,
}

let searchValue = '';
const urlParams = new URLSearchParams(window.location.search);
const customerId = urlParams.get('id');

document.addEventListener('DOMContentLoaded', async () => {
    const formSearch = document.querySelector(`#${state.formSearchId}`);
    
    //bắt event khi ấn tìm kiếm
    formSearch.addEventListener('submit', async e => {
        e.preventDefault();
        searchValue = document.querySelector(`#${state.numberSearchId}`).value;
        
        //set lại cái cái số trang
        pagination.offset = 0;
        pagination.currentPage = 1;
        
        await getCustomersAPI();
    });
    
    await getCustomersAPI();
});

// get customers pagination
const getCustomersAPI = async () => {
    try {
        const response = await axios.get("/SalesManagement/api/customers", {
            params: {
                offset: pagination.offset,
                limit: pagination.limit,
                searchValue: searchValue,
            }
        }, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (response.status === 200 && response.data) {
            renderCustomers(response.data.customers);
            await getRemainCustomersCountAPI();
        }

    } catch (err) {
        console.log(err);
    }
}

const getRemainCustomersCountAPI = async () => {
    try {
        const response = await axios.get("/SalesManagement/api/customers/remain", {
            params: {
                searchValue: searchValue,
            }
        }, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (response.status === 200 && response.data) {
            // round up
            pagination.totalPage = Math.ceil(response.data.remain / pagination.limit);
            renderPagination(pagination);
        }

    } catch (err) {
        console.log(err);
    }
}

const pageNumberClicked = async (e) => {
    let pageItem = e.target;
    e.preventDefault();
    let page = pageItem.getAttribute("aria-current");
    if (!!page && +page !== pagination.currentPage) {
        pagination.currentPage = +page;
        pagination.offset = (+page - 1) * pagination.limit;

        await getCustomersAPI();
    }
}

const renderPagination = (pagination) => {
    const container = document.querySelector(`#${pagination.containerId}`);
    let {currentPage, totalPage, pageNumberShow} = pagination;
    if (pageNumberShow > totalPage)
        pageNumberShow = totalPage;

    let start = currentPage - Math.floor(pageNumberShow / 2);
    start = Math.max(start, 1);
    start = Math.min(start, 1 + totalPage - pageNumberShow);

    let pagesRange = Array.from({length: pageNumberShow}, (el, i) => start + i);

    let pagesRangeHtml = pagesRange.map(value => {
        return `<li class="page-item${value === currentPage ? ' active' : ''}">
                    <a class="page-link" href="#" aria-current="${value}"
                     onclick="pageNumberClicked(event)">${value}</a>
                </li>`
    }).join('');

    let html = [
        `<li class="page-item${currentPage === 1 ? ' disabled' : ''}">
            <a class="page-link" href="#" aria-current="1" onclick="pageNumberClicked(event)">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>`,
        pagesRangeHtml,
        `<li class="page-item${currentPage === totalPage ? ' disabled' : ''}">
            <a class="page-link" href="#" aria-current="${totalPage}" onclick="pageNumberClicked(event)">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>`
    ];

    container.innerHTML = html.join('');
}

const renderCustomers = (customers) => {
    const customerTable = document.querySelector(`#${state.customersTableId}`);

    let html = customers.map((cus, i) => {
        return `<tr>
                    <td>${i + 1}</td>
                    <td>${cus.name}</td>
                    <td>${cus.identityCard}</td>
                    <td>${cus.phone}</td>
                    <td>${cus.numberCard}</td>
                
                        <td>${cus.promotion ? cus.promotion.name : 'Chưa áp dụng'}</td>
                <td class="d-flex justify-content-center">
                    <div class="btn-group" role="group">
                        <a class="btn btn-sm btn-white" href="/SalesManagement/payment?id=${cus.id}">
                            <i class="fa fa-credit-card" aria-hidden="true"></i> Nạp tiền
                        </a>
                        <a class="btn btn-sm btn-white btn-checkout" href="/SalesManagement/checkout?id=${cus.id}">
                            <i class="fa fa-book" aria-hidden="true"></i> Thanh toán
                        </a>
                        <a class="btn btn-sm btn-white btn-checkout" href="/SalesManagement/customer/update?id=${cus.id}">
                            <i class="fa fa-book" aria-hidden="true"></i> Sửa thông tin
                        </a>
                    </div>
                </td>
            </tr>
        `;
    }).join('');

    customerTable.querySelector('tbody').innerHTML = html;
}