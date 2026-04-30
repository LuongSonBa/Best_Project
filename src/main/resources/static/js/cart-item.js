async function updateQuantity(cartItemId, delta) {
    const row = document.getElementById(`cart-item-${cartItemId}`);
    const qtyInput = row.querySelector('.quantity-input');
    let newQty = parseInt(qtyInput.value) + delta;

    if (newQty <= 0) return; // Không cho giảm xuống 0

    // Cập nhật giao diện trước cho mượt
    qtyInput.value = newQty;

    // Sau đó gọi lưu vào Database
    await saveAllChanges();
}

async function saveAllChanges() {
    const userId = 1; // ID cố định hoặc lấy từ session
    const requestData = [];

    // Thu thập tất cả dòng trong bảng để đồng bộ 1 lần
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
            // Cập nhật tổng tiền toàn giỏ hàng
            document.getElementById('total-price').innerText = data.totalPrice + ' ¥';
            
            // Cập nhật subtotal cho từng dòng từ dữ liệu server trả về
            data.items.forEach(item => {
                const itemRow = document.getElementById(`cart-item-${item.cartItemId}`);
                if (itemRow) {
                    itemRow.querySelector('.subtotal-cell').innerText = item.subtotal + ' ¥';
                }
            });
        }
    } catch (e) {
        console.error("Update failed", e);
    }
}

function toggleSelection(itemId) {
    saveAllChanges();
}

async function deleteItem(itemId) {
    if (confirm("Bạn có chắc muốn xóa?")) {
        const res = await fetch(`/api/cart/delete/${itemId}`, { method: 'DELETE' });
        if (res.ok) {
            document.getElementById(`cart-item-${itemId}`).remove();
            saveAllChanges();
        }
    }
}