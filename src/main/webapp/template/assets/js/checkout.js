const state = {
    funcBtnClass: 'btn-table-func',
    addDatasetName: 'add',
    removeDatasetName: 'remove',
    addBtnContent: `<i class="fas fa-plus-square"></i> Thêm`,
    removeBtnContent: `<i class="fas fa-trash-alt"></i> Xoá`,
    productsTableId: 'productsTable',
    productsSelectedTableId: 'productsSelectedTable',
}

const pagination = {
    containerId: 'checkout-products-pagination',
    currentPage: 1,
    totalPage: 1,
    offset: 0,
    limit: 5,
    pageNumberShow: 5,
}

let selectedProductIds = [];
const urlParams = new URLSearchParams(window.location.search);
const customerId = urlParams.get('id');

document.addEventListener('DOMContentLoaded', async () => {
    // Variables
    // Handle cart button click: show/hide body
    const cartButton = document.querySelector('.btn-cart'),
        cartBody = document.querySelector('.cart-body')



    cartButton.addEventListener('click', (e) => {
        let isHidden = cartButton.dataset.hidden === 'true';

        !!isHidden ? cartBody.classList.add('active') : cartBody.classList.remove('active');
        if (isHidden) {
            handleCartChanged();
        }

        cartButton.dataset.hidden = !isHidden;
    });

    document.querySelector('#btn-clear-cart').addEventListener('click', () => {
        window.location.reload();
    })

    document.querySelector('#btn-checkout').addEventListener('click', () => {
        checkout();
    })

    // regex cho ưu đãi thêm
    document.querySelector('#extraPromotions').addEventListener('change', (e) => {
        let re = /^[0-1][0-9]*$/;
        let value = e.target.value;
        if(!re.test(value)) {
            e.preventDefault();
            e.target.value = 0;
        }

        // call api
        handleCartChanged();
    })

    document.querySelector('#toast-close').addEventListener('click', () => {
        $('#checkout-toast').toast('hide');
    })

    //search event
    document.querySelector('#btn-search').addEventListener('click', async() => {
        pagination.offset = 0;
        pagination.currentPage = 1;
        await getProductsAPI();
    })

    await getProductsAPI();
});

// API
const checkout = async () => {
    try {
        let values = mapProductQuantity();
        const json = JSON.stringify({
            products: values,
            customerId: +customerId,
            extraPromotions: document.querySelector('#extraPromotions').value || 0,
        })
        const response = await axios.post('/SalesManagement/api/checkout', json, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
        if (response.status === 200) {
            const res = response.data;
            handleCheckoutResult(res?.success, res?.id);
            document.querySelector('.checkout-success-total').innerHTML =
                res?.total.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");
        }

    } catch (err) {
        console.log(err);
    }
}

// get products pagination
const getProductsAPI = async () => {
    try {
        const response = await axios.get("/SalesManagement/api/products", {
            params: {
                offset: pagination.offset,
                limit: pagination.limit,
                excludeIds: selectedProductIds.join(','),
                searchId: document.querySelector('#product-search').value || ''
            }
        }, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (response.status === 200 && response.data) {
            renderProducts(response.data.products);
            await getRemainProductsCountAPI();
            sortTableById(state.productsTableId);
        }

    } catch (err) {
        console.log(err);
    }
}

const getRemainProductsCountAPI = async () => {
    try {
        const response = await axios.get("/SalesManagement/api/products/remain", {
            params: {
                excludeIds: selectedProductIds.join(','),
                searchId: document.querySelector('#product-search').value || ''
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

const handleCartChanged = () => {
    const values = mapProductQuantity();

    const callApi = async () => {
        try {
            const json = JSON.stringify({
                products: values,
                customerId: +customerId,
                extraPromotions: document.querySelector('#extraPromotions').value || 0,
            });
            let response = await axios.post('/SalesManagement/api/cart', json, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            if (response.data && response.status === 200) {
                const res = response.data;
                renderCartBody(res.origin, res.discount,
                    res.productSale, res.customerSale, res.total);
                if(res.isDeptor === 'true') {
                    showToast('Cảnh báo',
                        'Nếu mua hàng, khách hàng sẽ có công nợ vượt mức quy định');
                }

                if(res.success === "false") {
                    showToast('Cảnh báo',
                        res.message);
                    document.querySelector('#btn-checkout').disabled = true;
                } else {
                    document.querySelector('#btn-checkout').disabled = false;
                }

            }
        } catch (error) {
            console.log(error);
        }
    }

    callApi();
}
// END API

const handleCheckoutResult = (isSuccess, billId) => {
    if(isSuccess) {
        let btn = document.querySelector('#btn-to-detail');
        let href = btn.getAttribute('href');
        btn.setAttribute('href', `${href}${billId}`);
        document.querySelector('#checkout-form').classList.add('d-none');
        document.querySelector('#checkoutStepSuccessMessage').classList.remove('d-none');
    } else {

    }
}

const showToast = (header, message) => {
    document.querySelector('.toast-title').innerHTML = header;
    document.querySelector('.toast-body').innerHTML = message;
    $('#checkout-toast').toast('show');
}

const funcButtonClicked = (funcButton) => {
    let row = funcButton.closest('tr');
    let quantityInput = row.querySelector('td > .checkout-table__input');

    // Handle add/remove product
    let productId = row.dataset.id;
    let quantity = quantityInput.value;
    if (!productId || !quantity) return;
    // get function name
    let funcName = funcButton.dataset.func;

    if (funcName === state.addDatasetName) {
        handleAddButtonClicked(row, funcButton);
    } else if (funcName === state.removeDatasetName) {
        handleRemoveButtonClicked(row, funcButton);
    }
}

const quantityInputChanged = (e) => {
    let re = /^[1-9][0-9]*/;
    let value = e.target.value;
    if (!re.test(value)) {
        e.preventDefault();
        e.target.value = 1;
    }

    if(e.target.getAttribute('aria-location') === 'selected') {
        // call api
        handleCartChanged();
    }
}

const pageNumberClicked = async (e) => {
    let pageItem = e.target;
    e.preventDefault();
    let page = pageItem.getAttribute("aria-current");
    if(!!page && +page !== pagination.currentPage) {
        pagination.currentPage = +page;
        pagination.offset = (+page - 1) * pagination.limit;

        await getProductsAPI();
    }

}

const RemoveAndAdd = (tableSend, tableReceive, row) => {
    const productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    // remove row selected
    let hasRemoved = tableSend.querySelector('tbody').removeChild(row);
    if (!hasRemoved) {
        console.log('remove row error');
    }

    // add row selected to table receive
    let hasAdded = tableReceive.querySelector('tbody').appendChild(row);
    if (!hasAdded) {
        console.log('add row error');
    }

    // sort table receive
    // sortTableById(tableReceive);
}

const handleCartCountChanged = () => {
    // count product selected
    let productCount = selectedProductIds.length || 0;
    if (productCount > 0) {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if (el.classList.contains('hidden')) {
                el.classList.remove('hidden');
            }
            el.innerHTML = productCount;
        })

        document.querySelector('#btn-checkout').disabled = false;
    } else {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if (!el.classList.contains('hidden')) {
                el.classList.add('hidden');
            }
            el.innerHTML = productCount;
        });

        document.querySelector('#btn-checkout').disabled = true;
    }

    // call api
    handleCartChanged();
}

const handleAddButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector(`#${state.productsTableId}`),
        productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    RemoveAndAdd(productsTable, productsSelectedTable, row);
    row.querySelector('td > .checkout-table__input').setAttribute('aria-location', 'selected')
    //add id to array
    selectedProductIds.push(row.dataset.id);
    handleCartCountChanged();

    button.dataset.func = state.removeDatasetName;
    button.innerHTML = state.removeBtnContent;

    getProductsAPI();
}

const handleRemoveButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector(`#${state.productsTableId}`),
        productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    // RemoveAndAdd(productsSelectedTable, productsTable, row);
    productsSelectedTable.querySelector('tbody').removeChild(row);
    selectedProductIds = selectedProductIds.filter(id => id !== row.dataset.id);

    handleCartCountChanged();

    row.querySelector('.checkout-table__input').value = 1;
    button.dataset.func = state.addDatasetName;
    button.innerHTML = state.addBtnContent;
    getProductsAPI();
}

const sortTableById = (tableId) => {
    const table = document.querySelector(`#${tableId}`);
    const rows = table.querySelectorAll(`tbody > tr`);
    Array.from(rows)
        .sort((firstRow, secondRow) => {
            // Id là int
            let firstId = +firstRow.dataset.id;
            let secondId = +secondRow.dataset.id;

            return (firstId || 0) - (secondId || 0);
        }).forEach(tr => {
        table.querySelector('tbody').appendChild(tr);
    });
}

const mapProductQuantity = () => {
    const productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);

    // Map row thành array [{id: , quantity: },...]
    let values = Array.from(productsSelectedTable.querySelectorAll('tbody > tr'))
        .map(tr => {
            let id = tr.dataset.id;
            let quantity = tr.querySelector('td > .checkout-table__input').value;

            return {
                id, quantity
            }
        })

    return values;
}

function renderCartBody(origin, discount, productSale, customerSale, total) {
    const originElement = document.querySelector('#cart-origin');
    const discountElement = document.querySelector('#cart-discount');
    const productSaleElement = document.querySelector('#cart-product-sale');
    const customerSaleElement = document.querySelector('#cart-customer-sale');
    const totalElement = document.querySelector('#cart-total');

    [originElement, discountElement, productSaleElement,
        customerSaleElement, totalElement].map((element, i) => {
        element.innerHTML =
            `${parseInt([...arguments][i])}`.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.") + " VNĐ";
    })

}

const renderPagination = (pagination) => {
    const container = document.querySelector(`#${pagination.containerId}`);
    let {currentPage, totalPage, pageNumberShow} = pagination;
    if (pageNumberShow > totalPage) pageNumberShow = totalPage;

    let start = currentPage - Math.floor(pageNumberShow / 2);
    start = Math.max(start, 1);
    start = Math.min(start, 1 + totalPage - pageNumberShow);

    let pagesRange = Array.from({length: pageNumberShow},(el, i) => start + i);

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

const renderProducts = (products) => {
    const productsTable = document.querySelector(`#${state.productsTableId}`);

    let html = products.map(product => {
        return `<tr class="table-products__row"
        data-id="${product.id}"
    >
        <td class="lead">${product.id}</td>
        <td class="lead">${product.name}</td>
        <td class="lead">${product.specification}</td>
        <td class="lead">${product.historicalCost.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.")}</td>
        <td class="lead">${product?.promotions?.percentDiscount || 0}</td>
        <td class="lead">${product.productSalePrice.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.")}</td>
        <td class="lead">
            <input type="number"
                   class="js-masked-input form-control checkout-table__input"
                   name=""
                   value="1"
                   min="1"
                   onchange="quantityInputChanged(event)"
                   aria-location="origin"
            >
        </td>
        <td class="text-center">
            <button type="button" 
                    class="btn btn-sm btn-white btn-table-func"
                    data-func="add"
                    onclick="funcButtonClicked(this)">
                <i class="fas fa-plus-square"></i>
                Thêm
            </button>
        </td>
    </tr>`;
    }).join('');

    productsTable.querySelector('tbody').innerHTML = html;
}