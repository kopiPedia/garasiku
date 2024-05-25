// window.onload = function() {
//     fetchVouchers();
// };
//
// function fetchVouchers() {
//     fetch(`http://localhost:8080/voucher/list`, {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json',
//             'Accept': 'application/json'
//         }
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json();
//         })
//         .then(vouchers => {
//             displayVouchers(vouchers);
//         })
//         .catch(error => {
//             console.error('There was a problem with your fetch operation:', error);
//         });
// }
//
// function displayVouchers(vouchers) {
//     const container = document.getElementById('vouchers');
//     container.innerHTML = ''; // Clear previous items
//     vouchers.forEach(voucher => {
//         const voucherElement = document.createElement('div');
//         voucherElement.innerHTML = `
//             <p>Name: ${voucher.voucherName}, Quantity: ${voucher.voucherQuantity}, Discount: ${voucher.discount}</p>
//             <button onclick="deleteVoucher('${voucher.voucherId}')">Delete</button>
//             <button onclick="updateVoucher('${voucher.voucherId}')">Update</button>
//         `;
//         container.appendChild(voucherElement);
//     });
// }
//
// function deleteVoucher(voucherId) {
//     fetch(`http://localhost:8080/voucher/delete/${voucherId}`, {
//         method: 'DELETE'
//     }).then(response => {
//         if (!response.ok) {
//             throw new Error('Failed to delete voucher');
//         }
//         fetchVouchers();
//     }).catch(error => console.error('Error:', error));
// }
//
// function updateVoucher(voucherId) {
//     const updatedVoucherData = {
//         voucherName: prompt("Enter updated voucher name:"),
//         voucherQuantity: parseInt(prompt("Enter updated voucher quantity:")),
//         discount: parseInt(prompt("Enter updated discount:"))
//     };
//
//     fetch(`http://localhost:8080/voucher/update/${voucherId}`, {
//         method: 'PUT',
//         headers: {
//             'Content-Type': 'application/json',
//             'Accept': 'application/json'
//         },
//         body: JSON.stringify(updatedVoucherData)
//     }).then(response => {
//         if (!response.ok) {
//             throw new Error('Failed to update voucher');
//         }
//         fetchVouchers();
//     }).catch(error => console.error('Error:', error));
// }
//
// function createVoucher() {
//     const newVoucherData = {
//         voucherName: prompt("Enter voucher name:"),
//         voucherQuantity: parseInt(prompt("Enter voucher quantity:")),
//         discount: parseInt(prompt("Enter discount:"))
//     };
//
//     fetch(`http://localhost:8080/voucher/create`, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             'Accept': 'application/json'
//         },
//         body: JSON.stringify(newVoucherData)
//     }).then(response => {
//         if (!response.ok) {
//             throw new Error('Failed to create voucher');
//         }
//         fetchVouchers();
//     }).catch(error => console.error('Error:', error));
// }
