window.onload = function() {
    fetchCart('john_doe');
};

function fetchCart(user) {
    fetch(`http://localhost:8080/cart/${user}/list`, {
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
        .then(cartItems => {
            displayCartItems(cartItems);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

function displayCartItems(cartItems) {
    const container = document.getElementById('cartItems');
    container.innerHTML = ''; // Clear previous items
    cartItems.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.innerHTML = `
            <p>Product: ${item.product}, Quantity: ${item.quantity}, Price: $${item.price}</p>
            <button onclick="updateItemQuantity('${item.id}', ${item.quantity - 1})">-</button>
            <button onclick="updateItemQuantity('${item.id}', ${item.quantity + 1})">+</button>
            <button onclick="deleteItem('${item.id}')">Delete</button>
            <button onclick="editItem('${item.id}')">Edit</button>
        `;
        container.appendChild(itemElement);
    });
    const deleteAllButton = document.createElement('button');
    deleteAllButton.textContent = 'Delete All Items';
    deleteAllButton.onclick = () => deleteAllItems('username');
    container.appendChild(deleteAllButton);
}

function deleteItem(itemId) {
    fetch(`http://localhost:8080/cart/delete/${itemId}`, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to delete item');
        }
        fetchCart('username');
    }).catch(error => console.error('Error:', error));
}

function updateItemQuantity(itemId, newQuantity) {
    if(newQuantity < 1) {
        alert("Quantity cannot be less than 1");
        return;
    }
    fetch(`http://localhost:8080/cart/update/${itemId}/${newQuantity}`, {
        method: 'PUT'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to update item quantity');
        }
        fetchCart('username');
    }).catch(error => console.error('Error:', error));
}

function deleteAllItems(user) {
    fetch(`http://localhost:8080/cart/delete/all/${user}`, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Failed to delete all items');
        }
        fetchCart('username');
    }).catch(error => console.error('Error:', error));
}

function editItem(itemId) {
    console.log(`Edit functionality not implemented: ${itemId}`);
}
