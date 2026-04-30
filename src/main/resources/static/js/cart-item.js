async function saveAllChanges() {
    const userId = 1;
    const requestData = [];

    document.querySelectorAll('tr[id^="cart-item-"]').forEach(row => {
        requestData.push({
            cartItemId: parseInt(row.id.split('-').pop()),
            quantity: parseInt(row.querySelector('.quantity-input').value),
            isSelected: row.querySelector('.item-checkbox').checked
        });
    });

    try {
        const response = await fetch(`/api/cart/update-all?userId=${userId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const data = await response.json();
            document.getElementById('total-price').innerText = data.totalPrice + ' ¥';
            data.items.forEach(item => {
                const itemRow = document.getElementById(`cart-item-${item.cartItemId}`);
                if (itemRow) itemRow.querySelector('.subtotal-cell').innerText = item.subtotal + ' ¥';
            });
        }
    } catch (e) { console.error("Update failed", e); }
}

function updateQuantity(itemId, delta) {
    const input = document.querySelector(`#cart-item-${itemId} .quantity-input`);
    let val = parseInt(input.value) + delta;
    if (val > 0) {
        input.value = val;
        saveAllChanges();
    }
}

function toggleSelection(itemId) {
    saveAllChanges();
}

async function deleteItem(itemId) {
    if (confirm("Remove item?")) {
        const res = await fetch(`/api/cart/delete/${itemId}`, { method: 'DELETE' });
        if (res.ok) {
            document.getElementById(`cart-item-${itemId}`).remove();
            saveAllChanges();
        }
    }
}