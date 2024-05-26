document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('.select-item');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', updateTotalPrice);
    });

    async function updateTotalPrice() {
        let total = 0;
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                const id = checkbox.getAttribute('data-id');
                const quantityElement = document.querySelector(`#quantity-${id}`);
                const priceElement = document.querySelector(`#price-${id}`);
                const quantity = parseInt(quantityElement.textContent.replace('Quantity: ', ''));
                const price = parseFloat(priceElement.textContent.replace('Price: $', '')) / quantity;
                total += price * quantity;
            }
        });
        const totalPriceElement = document.querySelector('#totalPriceCard p');
        totalPriceElement.textContent = `Total Price: $${total.toFixed(2)}`;

        // Save the total price to localStorage
        localStorage.setItem('totalPrice', total.toFixed(2));
    }

    async function increment(id, productId) {
        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        const response = await fetch("/cart/increase/" + id + "/" + productId, {
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

            const response = await fetch("/cart/decrease/" + id + "/" + productId, {
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

    async function deleteProduct(id, productId) {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(`/cart/delete/${id}/${productId}`, {
                method: 'DELETE',
                headers: {
                    [csrfHeader]: csrfToken
                }
            });

            if (!response.ok) {
                throw new Error('Failed to delete product from cart');
            }

            refreshCart2(id);
        } catch (error) {
            console.error('Error deleting product:', error);
            // Handle error gracefully
            // For example, display a message to the user
        }
    }

    async function refreshCart(cartItem, id) {
        if (cartItem) {
            const quantityElement = document.querySelector(`#quantity-${id}`);
            const priceElement = document.querySelector(`#price-${id}`);
            quantityElement.textContent = `Quantity: ${cartItem.quantity}`;
            priceElement.textContent = `Price: $${cartItem.price.toFixed(2)}`;
        } else {
            // delete the card of item
            const itemCard = document.querySelector(`.cart-item[data-id='${id}']`);
            if (itemCard) {
                itemCard.remove();
            }
        }
        updateTotalPrice();
    }

    async function fetchTotalPrice(id) {
        const response = await fetch(`/cart/price/${id}`);
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

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', () => {
            const id = button.getAttribute('data-id');
            const productId = button.getAttribute('data-product-id');
            deleteProduct(id, productId);
        });
    });

    function refreshCart2(deletedItemId) {
        // Find the item card to be deleted
        const itemCard = document.querySelector(`.cart-item[data-id='${deletedItemId}']`);

        if (itemCard) {
            // Remove the item card from the DOM
            itemCard.remove();
        }

        // Update the total price
        const totalPriceElement = document.querySelector('#totalPriceCard p');
        fetchTotalPrice().then(totalPrice => {
            totalPriceElement.textContent = `Total Price: $${totalPrice}`;
        });
    }
});
