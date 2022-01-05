$(document).ready(function () {
    let max_fields = 30;
    let index = 1;


    $('.add-product').click(function () {

        if (index < max_fields) {
            let newRow = jQuery('<div class="product row">' +
                ' <div class="col-lg-6 mb-3">' +
                ' Name' +
                ' <input name="products[' + index + '].name" type="text" class="form-control form-control-lg"/>' +
                ' </div>' +
                ' <div class="col-lg-3 mb-3">' +
                '  Quantity\n' +
                ' <input name="products[' + index + '].quantity" type="number" step="0.1" min="0"' +
                ' class="form-control form-control-lg"/>' +
                '</div>' +
                '<div class="col-lg-3 mb-3">' +
                'Measurement\n' +
                '<select class="form-control form-control-lg" name="products[' + index + '].measurement">' +
                '<option value="">-</option>' +
                '<option value="kg">kg</option>' +
                '<option value="g">g</option>' +
                '<option value="tablespoons">tablespoons</option>' +
                '<option value="cups">cups</option>' +
                '<option value="pints">pints</option>' +
                '<option value="ml">ml</option>' +
                '<option value="teaspoon">teaspoon</option>' +
                '<option value="large">large</option>' +
                '<option value="medium">medium</option>' +
                '<option value="small">small</option>' +
                '</select>' +
                '</div>' +
                '</div>');

            index++;

            $('.products').append(newRow)
        }

    });
});