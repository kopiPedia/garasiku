document.addEventListener('DOMContentLoaded', () => {
    const totalPriceElement = document.getElementById('totalPrice');
    const voucherInput = document.getElementById('voucherInput');
    const payButton = document.querySelector('.payButton');
    const confirmButton = document.getElementById('confirmButton');

    let totalPrice = parseFloat(totalPriceElement.textContent.replace('Total Price: $', ''));

    voucherInput.addEventListener('change', () => {
        const selectedOption = voucherInput.options[voucherInput.selectedIndex];
        const discount = parseFloat(selectedOption.getAttribute('data-discount'));
        if (!isNaN(discount) && discount > 0) {
            const discountedPrice = totalPrice - (totalPrice * (discount / 100));
            totalPriceElement.textContent = `Total Price: $${discountedPrice.toFixed(2)}`;
        } else {
            totalPriceElement.textContent = `Total Price: $${totalPrice.toFixed(2)}`;
        }
    });

    payButton.addEventListener('click', () => {
        $('#payModal').modal('show');
    });

    confirmButton.addEventListener('click', () => {
        const selectedVoucherId = voucherInput.value;
        const cartId = payButton.getAttribute('data-cart-id');  // Read cart ID from button data attribute

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        if (selectedVoucherId) {
            // Make an AJAX request to update the voucher quantity and create order
            $.ajax({
                url: `/voucher/decrement/${selectedVoucherId}`,
                method: 'POST',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                success: () => {
                    // Create the order from the cart
                    $.ajax({
                        url: `/create/${cartId}`,
                        method: 'POST',
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader(csrfHeader, csrfToken);
                        },
                        success: () => {
                            window.location.href = `/order`;
                        },
                        error: (err) => {
                            alert('Error creating order: ' + err.responseText);
                        }
                    });
                },
                error: (err) => {
                    alert('Error processing payment by voucher: ' + err.responseText);
                }
            });
        } else {
            // If no voucher selected, directly create the order
            $.ajax({
                url: `/create/${cartId}`,
                method: 'POST',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(csrfHeader, csrfToken);
                },
                success: () => {
                    window.location.href = `/order`;
                },
                error: (err) => {
                    alert('Error creating order: ' + err.responseText);
                }
            });
        }
    });
});