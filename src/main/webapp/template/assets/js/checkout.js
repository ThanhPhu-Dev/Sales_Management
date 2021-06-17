const state = {
    funcBtnclasss: 'btn-table-func',
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

    await getProductsAPI();
});

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
            console.log(response);
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
                excludeIds: selectedProductIds.join(',')
            }
        }, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
        if (response.status === 200 && response.data) {
            renderProducts(response.data.products);
            await getRemainProductsCountAPI();
        }

    } catch (err) {
        console.log(err);
    }
}

const getRemainProductsCountAPI = async () => {
    try {
        const response = await axios.get("/SalesManagement/api/products/remain", {
            params: {
                excludeIds: selectedProductIds.join(',')
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
    // count product selected
    let productCount = productsSelectedTable.querySelectorAll('tbody > tr')?.length || 0;
    if (productCount > 0) {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if (el.classList.contains('hidden')) {
                el.classList.remove('hidden');
            }
            el.innerHTML = productCount;
        })
    } else {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if (!el.classList.contains('hidden')) {
                el.classList.add('hidden');
            }
            el.innerHTML = productCount;
        })
    }

    // sort table receive
    sortTableById(tableReceive);
}

const handleAddButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector(`#${state.productsTableId}`),
        productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    RemoveAndAdd(productsTable, productsSelectedTable, row);

    //add id to array
    selectedProductIds.push(row.dataset.id);

    button.dataset.func = state.removeDatasetName;
    button.innerHTML = state.removeBtnContent;
}

const handleRemoveButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector(`#${state.productsTableId}`),
        productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    RemoveAndAdd(productsSelectedTable, productsTable, row);

    selectedProductIds = selectedProductIds.filter(id => id !== row.dataset.id);
    row.querySelector('.checkout-table__input').value = 1;
    button.dataset.func = state.addDatasetName;
    button.innerHTML = state.addBtnContent;
}

const sortTableById = (table) => {
    const rows = table.querySelectorAll('tbody > tr');
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
                renderCartBody(res.origin, res.discount, res.sale, res.total);

            }
        } catch (error) {
            console.log(error);
        }
    }

    callApi();
}

function renderCartBody(origin, discount, sale, total) {
    const originElement = document.querySelector('#cart-origin');
    const discountElement = document.querySelector('#cart-discount');
    const saleElement = document.querySelector('#cart-sale');
    const totalElement = document.querySelector('#cart-total');

    [originElement, discountElement, saleElement, totalElement].map((element, i) => {
        element.innerHTML =
            `${[...arguments][i]}`.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.") + "VNĐ";
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
        <td class="lead">${(product.promotions && product.percentDiscount) || 0}</td>
        <td class="lead">${product.productSalePrice}</td>
        <td class="lead">
            <input type="number"
                   class="js-masked-input form-control checkout-table__input"
                   name=""
                   value="1"
                   min="1"
                   onchange="quantityInputChanged(event)"
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