document.addEventListener('DOMContentLoaded', () => {
    async function fetchOrders() {
        try {
            const cartId = localStorage.getItem('cartId');
            if (!cartId) {
                throw new Error('Cart ID not found in localStorage');
            }
            const response = await fetch(`/order`);
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            const orders = await response.json();
            console.log('Fetched orders:', orders); // Debugging statement
            displayOrders(orders);
        } catch (error) {
            console.error('There has been a problem with your fetch operation:', error);
        }
    }



    async function fetchOrders() {
    try {
        const response = await fetch('/order/user/{userId}'); // Replace with actual user ID
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const orders = await response.json();
        console.log('Fetched orders:', orders); // Debugging statement
        displayOrders(orders);
    } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
    }
}

    function displayOrders(orders) {
        const orderItems = document.getElementById('orderItems');
        orderItems.innerHTML = ''; // Clear previous items
        orders.forEach(order => {
            const orderItem = document.createElement('div');
            orderItem.className = 'card order-item';
            orderItem.innerHTML = `
                <div class="mask"></div>
                <p>Cart ID: ${order.cartId}</p>
                <p>Product ID: ${order.productId}</p>
                <p>Order Date: ${new Date(order.orderDate).toLocaleString()}</p>
                <p class="order-status">Status: ${order.status}</p>
            `;
            orderItems.appendChild(orderItem);
        });
    }

    // Fetch orders data on page load
    fetchOrders();

});