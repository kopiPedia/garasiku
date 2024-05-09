window.onload = function() {
    fetchOrders();
};

function fetchOrders() {
    fetch(`http://localhost:8080/order/list`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(orders => {
            displayOrders(orders);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

function displayOrders(orders) {
    const container = document.getElementById('orders');
    container.innerHTML = ''; // Clear previous items
    orders.forEach(order => {
        const orderElement = document.createElement('div');
        orderElement.innerHTML = `
            <p>Order ID: ${order.id}, Status: ${order.status}</p>
            <button onclick="deleteOrder('${order.id}')">Delete</button>
            <button onclick="updateOrderStatus('${order.id}', 'newStatus')">Update Status</button>
        `;
        container.appendChild(orderElement);
    });
}

function deleteOrder(orderId) {
    fetch(`http://localhost:8080/order/delete/${orderId}`, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to delete order');
        }
        fetchOrders();
    }).catch(error => console.error('Error:', error));
}

function updateOrderStatus(orderId, newStatus) {
    fetch(`http://localhost:8080/order/update/${orderId}/${newStatus}`, {
        method: 'PUT'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to update order status');
        }
        fetchOrders();
    }).catch(error => console.error('Error:', error));
}

function createOrder(orderData) {
    fetch(`http://localhost:8080/order/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(orderData)
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to create order');
        }
        fetchOrders();
    }).catch(error => console.error('Error:', error));
}