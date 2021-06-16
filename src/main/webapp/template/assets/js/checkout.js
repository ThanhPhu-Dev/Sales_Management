const state = {
    funcBtnClassnames: 'btn-table-func',
    addDatasetName: 'add',
    removeDatasetName: 'remove',
    addBtnContent: `<i class="fas fa-plus-square"></i> Thêm`,
    removeBtnContent: `<i class="fas fa-trash-alt"></i> Xoá`,
}

document.addEventListener('DOMContentLoaded', () => {
    // Handle cart button click: show/hide body
    let button = document.querySelector('.btn-cart');
    const cartBody = document.querySelector('.cart-body');
    button.addEventListener('click', (e) => {
        let isHidden = button.dataset.hidden === 'true';
        console.log(isHidden);

        !!isHidden ? cartBody.classList.add('active') : cartBody.classList.remove('active');

        button.dataset.hidden = !isHidden;
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

            if(funcName === state.addDatasetName) {
                handleAddButtonClicked(row, funcButton);
            } else if (funcName === state.removeDatasetName) {
                handleRemoveButtonClicked(row, funcButton);
            }
        });

    });

    // Quantity change
    document.querySelectorAll('.checkout-table__input')?.forEach(input => {
        input.addEventListener('change', (e) => {
            let re = /^[1-9][0-9]+/;
            let value = e.target.value;

            if(!re.test(value)) {
                e.preventDefault();
                e.target.value = 1;
            }
        })
    })
});

const RemoveAndAdd = (tableSend, tableReceive, row) => {
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
}

const handleAddButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector('#productsTable');
    const productsSelectedTable = document.querySelector('#productsSelectedTable');
    RemoveAndAdd(productsTable, productsSelectedTable, row);

    button.dataset.func = state.removeDatasetName;
    button.innerHTML = state.removeBtnContent;
}

const handleRemoveButtonClicked = (row, button) => {
    // Tables
    const productsTable = document.querySelector('#productsTable');
    const productsSelectedTable = document.querySelector('#productsSelectedTable');
    RemoveAndAdd(productsSelectedTable, productsTable, row);

    row.querySelector('.checkout-table__input').value = 1;
    button.dataset.func = state.addDatasetName;
    button.innerHTML = state.addBtnContent;
}