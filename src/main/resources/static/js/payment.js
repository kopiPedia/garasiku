document.addEventListener('DOMContentLoaded', () => {
    const totalPrice = localStorage.getItem('totalPrice');
    document.getElementById('totalPrice').textContent = `Total Price: $${totalPrice}`;
});

document.addEventListener('DOMContentLoaded', () => {
    const totalPriceElement = document.getElementById('totalPrice');
    const voucherInput = document.getElementById('voucherInput');

    let totalPrice = parseFloat(localStorage.getItem('totalPrice'));
    totalPriceElement.textContent = `Total Price: $${totalPrice.toFixed(2)}`;

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
});

// Future Modal
// const deleteButtons = document.querySelectorAll('.deleteButton');
// const deleteModal = document.getElementById('deleteModal');
// const cancelButton = document.getElementById('cancelButton');
// const confirmButton = document.getElementById('confirmButton');
// let deleteForm = null;
//
// deleteButtons.forEach(button => {
//     button.addEventListener('click', (e) => {
//         const sessionPlanId = e.target.getAttribute('data-id');
//         deleteForm = document.getElementById('deleteForm_' + sessionPlanId);
//         deleteModal.classList.remove('hidden');
//     });
// });
//
// cancelButton.addEventListener('click', () => {
//     deleteModal.classList.add('hidden');
// });
//
// confirmButton.addEventListener('click', () => {
//     if (deleteForm) {
//         deleteForm.submit();
//     }
// });