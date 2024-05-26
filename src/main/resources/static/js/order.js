document.addEventListener('DOMContentLoaded', () => {
    async function fetchOrders() {
        try {
            const response = await fetch('/order');
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            const orders = await response.json();
            displayOrders(orders);
        } catch (error) {
            console.error('There has been a problem with your fetch operation:', error);
        }
    }


