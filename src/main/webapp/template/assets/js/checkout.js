const state = {
    funcBtnClassnames: 'btn-table-func',
    addDatasetName: 'add',
    removeDatasetName: 'remove',
    addBtnContent: `<i class="fas fa-plus-square"></i> Thêm`,
    removeBtnContent: `<i class="fas fa-trash-alt"></i> Xoá`,
    productsTableId: 'productsTable',
    productsSelectedTableId: 'productsSelectedTable',
}

document.addEventListener('DOMContentLoaded', () => {
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

    // All rows in products table
    const productRows = document.querySelectorAll('.table-products__row');

    productRows.forEach(row => {
        let funcButton = row.querySelector('.btn-table-func');
        let quantityInput = row.querySelector('td > .checkout-table__input');

        // Handle add/remove product
        funcButton.addEventListener('click', () => {
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
        });

    });

    // Quantity changed
    document.querySelectorAll('.checkout-table__input')?.forEach(input => {
        input.addEventListener('change', (e) => {
            let re = /^[1-9][0-9]+/;
            let value = e.target.value;

            if (!re.test(value)) {
                e.preventDefault();
                e.target.value = 1;
            }
        })
    })
});

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
    if(productCount > 0) {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if(el.classList.contains('hidden')) {
                el.classList.remove('hidden');
            }
            el.innerHTML = productCount;
        })
    } else {
        document.querySelectorAll('.cart-quantity-text').forEach(el => {
            if(!el.classList.contains('hidden')) {
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

    button.dataset.func = state.removeDatasetName;
    button.innerHTML = state.removeBtnContent;
}

const handleRemoveButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector(`#${state.productsTableId}`),
        productsSelectedTable = document.querySelector(`#${state.productsSelectedTableId}`);
    RemoveAndAdd(productsSelectedTable, productsTable, row);

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

const handleCartChanged = () => {
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
    console.log(values);
    const callApi = async () => {
        try {
            const json = JSON.stringify({products: values})
            let response = await axios.post('/SalesManagement/api/cart', json, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            if(response.data && response.status === 200) {
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