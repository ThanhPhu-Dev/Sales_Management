
$('#historicalCost').on('keyup', function () {
    var n = parseInt($(this).val().replace(/\D/g,''),10);
    $(this).val(n.toLocaleString("it-IT"));
});

function removeErrors() {
    $('#error-sku').text('').css('display', 'none');
    $('#sku').css('borderBottom', '.0625rem solid #e7eaf3');

    $('#error-name').text('').css('display', 'none');
    $('#name').css('borderBottom', '.0625rem solid #e7eaf3');

    $('#error-specification').text('').css('display', 'none');
    $('#specification').css('borderBottom', '.0625rem solid #e7eaf3');

    $('#error-historicalCost').text('').css('display', 'none');
    $('#historicalCost').css('borderBottom', '.0625rem solid #e7eaf3');

    $('#error-tradeDiscount').text('').css('display', 'none');
    $('#tradeDiscount').css('borderBottom', '.0625rem solid #e7eaf3');

    $('#error-promotionsId').text('').css('display', 'none');
    $('#promotionsId').css('borderBottom', '.0625rem solid #e7eaf3');
}

function setErrors(errors) {
    if(errors.sku) {
        $('#error-sku').text(errors.sku).css('display', 'block');
        $('#sku').css('borderBottom', '1px solid red');
    }
    if(errors.name) {
        $('#error-name').text(errors.name).css('display', 'block');
        $('#name').css('borderBottom', '1px solid red');
    }
    if(errors.specification) {
        $('#error-specification').text(errors.specification).css('display', 'block');
        $('#specification').css('borderBottom', '1px solid red');
    }
    if(errors.historicalCost) {
        $('#error-historicalCost').text(errors.historicalCost).css('display', 'block');
        $('#historicalCost').css('borderBottom', '1px solid red');
    }
    if(errors.tradeDiscount) {
        $('#error-tradeDiscount').text(errors.tradeDiscount).css('display', 'block');
        $('#tradeDiscount').css('borderBottom', '1px solid red');
    }
    if(errors.promotionsId) {
        $('#error-promotionsId').text(errors.promotionsId).css('display', 'block');
        $('#promotionsId').css('borderBottom', '1px solid red');
    }
}

function isNumeric(value) {
    const regex = /^[0-9]+$/;
    return value.match(regex);
}


