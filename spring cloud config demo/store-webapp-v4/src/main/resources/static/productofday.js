$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/store/api/products/1"
    }).then(function(data, status, jqxhr) {
       $('.product-name').append(data.name);
       $('.product-quantity').append(data.quantity);
       console.log(jqxhr);
    });
});