async function increment(id, productId) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    const response = await fetch(`/cart/increase/${id}/${productId}`, {
        method: "PUT",
        headers: {
            [csrfHeader]: csrfToken
        }
    });
    const cartItem = await response.json();
    if (cartItem) {
        refreshCart(cartItem, id);
    }
}

async function decrement(id, productId) {
    const quantityElement = document.querySelector(`#quantity-${id}`);
    const currentQuantity = parseInt(quantityElement.textContent.replace('Quantity: ', ''));

    if (currentQuantity > 1) {
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const response = await fetch(`/cart/decrease/${id}/${productId}`, {
            method: "PUT",
            headers: {
                [csrfHeader]: csrfToken
            }
        });
        const cartItem = await response.json();
        if (cartItem) {
            refreshCart(cartItem, id);
        }
    }
}

async function refreshCart(cartItem, id) {
    if (cartItem) {
        const quantityElement = document.querySelector(`#quantity-${id}`);
        const priceElement = document.querySelector(`#price-${id}`);
        quantityElement.textContent = `Quantity: ${cartItem.quantity}`;
        priceElement.textContent = `Price: $${cartItem.price}.00`;
    } else {
        // delete the card of item
        const itemCard = document.querySelector(`.cart-item[data-id='${id}']`);
        if (itemCard) {
            itemCard.remove();
        }
    }

    const totalPriceElement = document.querySelector('#totalPriceCard p');
    const totalPrice = await fetchTotalPrice();
    totalPriceElement.textContent = `Total Price: $${totalPrice}`;
}

async function fetchTotalPrice() {
    const userId = document.querySelector('#userId').value;
    const response = await fetch(`/cart/total/${userId}`);
    const totalPrice = await response.text();
    return totalPrice;
}

document.querySelectorAll('.increase-btn').forEach(button => {
    button.addEventListener('click', () => {
        const id = button.getAttribute('data-id');
        const productId = button.getAttribute('data-product-id');
        increment(id, productId);
    });
});

document.querySelectorAll('.decrease-btn').forEach(button => {
    button.addEventListener('click', () => {
        const id = button.getAttribute('data-id');
        const productId = button.getAttribute('data-product-id');
        decrement(id, productId);
    });
});

document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const cartItemId = this.getAttribute('data-id');
            const productId = this.getAttribute('data-product-id');

            fetch(`/cart/${cartItemId}/${productId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                }
            })
                .then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        console.error('Failed to delete the cart item.');
                    }
                })
                .catch(error => console.error('Error:', error));
        });
    })
});