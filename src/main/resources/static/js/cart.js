async function increment(id, productId) {
    const response = await fetch("/cart/increase/" + id + "/" + productId, {
        method: "PUT",
    });
    const cartItem = await response.json();
    if (cartItem) {
        refreshCart(cartItem, id);
    }
}

async function decrement(id, productId) {
    const response = await fetch("/cart/decrease/" + id + "/" + productId, {
        method: "PUT",
    });
    const cartItem = await response.json();
    if (cartItem) {
        refreshCart(cartItem, id);
    }
}

async function refreshCart(cartItem, id) {
    if (cartItem){
    const quantityElement = document.querySelector(`#quantity-${id}`);
    const priceElement = document.querySelector(`#price-${id}`);
    quantityElement.textContent = `Quantity: ${cartItem.quantity}`;
    priceElement.textContent = `Price: $${cartItem.price}.00`;}
    else {
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
    const response = await fetch("/cart/price/john_doe");
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

async function deleteProduct(id, productId) {
    try {
        const response = await fetch(`/cart/delete/${id}/${productId}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Failed to delete product from cart');
        }

        // Refresh the cart after successful deletion
        refreshCart2(id);
    } catch (error) {
        console.error('Error deleting product:', error);
        // Handle error gracefully
        // For example, display a message to the user
    }
}

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