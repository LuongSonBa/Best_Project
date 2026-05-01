// 1. Hàm cập nhật số lượng (delta là +1 hoặc -1)
async function updateQuantity(cartItemId, delta) {
    const row = document.getElementById(`cart-item-${cartItemId}`);
    const qtyInput = row.querySelector('.quantity-input');
    let newQty = parseInt(qtyInput.value) + delta;

    if (newQty <= 0) return; // Không cho giảm xuống dưới 1

    // Cập nhật giao diện trước cho người dùng thấy mượt (Optimistic UI)
    qtyInput.value = newQty;

    // Gọi lưu vào Database
    await saveAllChanges();
}

// 2. Hàm lưu tất cả thay đổi (Số lượng, Trạng thái chọn)
async function saveAllChanges() {
    const requestData = [];

    // Thu thập tất cả dòng trong bảng
    document.querySelectorAll('tr[id^="cart-item-"]').forEach(row => {
        requestData.push({
            cartItemId: parseInt(row.id.split('-').pop()),
            quantity: parseInt(row.querySelector('.quantity-input').value),
            isSelected: row.querySelector('.item-checkbox').checked
        });
    });

    try {
        // URL BÂY GIỜ: Rất gọn, không cần truyền userId
        const response = await fetch(`/api/cart/update-all`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const data = await response.json();
            
            // Cập nhật tổng tiền toàn giỏ hàng (lấy từ CartResponseDto)
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
        console.error("Update failed:", e);
    }
}

// 3. Hàm khi click vào checkbox chọn sản phẩm
function toggleSelection(itemId) {
    saveAllChanges();
}

// 4. Hàm xóa sản phẩm
async function deleteItem(itemId) {
    if (confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
        // Backend sẽ tự check xem itemId này có đúng của người đang login không
        const res = await fetch(`/api/cart/delete/${itemId}`, { 
            method: 'DELETE' 
        });
        
        if (res.ok) {
            // Xóa dòng đó trên giao diện
            document.getElementById(`cart-item-${itemId}`).remove();
            // Tính toán lại tổng tiền
            saveAllChanges();
        } else {
            alert("Không thể xóa sản phẩm. Vui lòng thử lại!");
        }
    }
}