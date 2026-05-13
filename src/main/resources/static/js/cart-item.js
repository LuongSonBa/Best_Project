// 1. Hàm cập nhật số lượng (Dùng cho nút + và -)
function updateQuantity(cartItemId, delta) {
    const row = document.getElementById(`cart-item-${cartItemId}`);
    if (!row) return;

    const qtyInput = row.querySelector('.quantity-input');
    let currentQty = parseInt(qtyInput.value) || 0;
    let newQty = currentQty + delta;

    if (newQty <= 0) return; // Không cho giảm dưới 1

    // Bước 1: Cập nhật ngay lập tức trên màn hình (Optimistic UI)
    qtyInput.value = newQty;

    // Bước 2: Gọi hàm lưu mà không dùng 'await' ở đây để tránh khóa UI
    saveAllChanges();
}

// 2. Hàm lưu tất cả thay đổi (Đã được tối ưu)
async function saveAllChanges() {
    const requestData = [];
    // Quét tất cả các dòng đang có trong bảng
    const rows = document.querySelectorAll('tr[id^="cart-item-"]');
    
    if (rows.length === 0) return;

    rows.forEach(row => {
        const idStr = row.id.replace('cart-item-', '');
        const qty = parseInt(row.querySelector('.quantity-input').value);
        const selected = row.querySelector('.item-checkbox').checked;
        
        requestData.push({
            cartItemId: parseInt(idStr),
            quantity: qty,
            isSelected: selected
        });
    });

    try {
        const response = await fetch(`/api/cart/update-all`, {
            method: 'PUT',
            headers: { 
                'Content-Type': 'application/json',
                'Accept': 'application/json' 
            },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const data = await response.json();
            const formatter = new Intl.NumberFormat('ja-JP');
            
            // Cập nhật Tổng tiền (Total Price)
            const totalPriceElem = document.getElementById('total-price');
            if (totalPriceElem) {
                // Đảm bảo data.totalPrice từ server trả về là con số đúng
                totalPriceElem.innerText = formatter.format(data.totalPrice) + ' ¥';
            }
            
            // Cập nhật Subtotal từng dòng từ dữ liệu server trả về
            if (data.items && Array.isArray(data.items)) {
                data.items.forEach(item => {
                    const itemRow = document.getElementById(`cart-item-${item.cartItemId}`);
                    if (itemRow) {
                        const subtotalElem = itemRow.querySelector('.subtotal-cell');
                        if (subtotalElem) {
                            subtotalElem.innerText = formatter.format(item.subtotal) + ' ¥';
                        }
                    }
                });
            }
        }
    } catch (e) {
        console.error("Lỗi cập nhật giỏ hàng:", e);
    }
}

// 3. Hàm khi click vào checkbox
function toggleSelection(itemId) {
    saveAllChanges();
}

// 4. Hàm xóa sản phẩm
async function deleteItem(itemId) {
    if (!confirm("Bạn có chắc muốn xóa sản phẩm này?")) return;
    
    try {
        const res = await fetch(`/api/cart/delete/${itemId}`, { method: 'DELETE' });
        if (res.ok) {
            const row = document.getElementById(`cart-item-${itemId}`);
            if (row) row.remove();
            // Sau khi xóa, tính toán lại tổng tiền của các món còn lại
            saveAllChanges();
        }
    } catch (e) {
        console.error("Lỗi xóa sản phẩm:", e);
    }
}
// File: cart.js (hoặc trong <script> của cart.html)

function checkout() {
    console.log("Đang chuẩn bị chuyển sang trang Checkout...");

    // Gọi hàm lưu tất cả thay đổi (số lượng, tích chọn) mà bạn đã làm
    // Giả sử hàm đó tên là saveAllChanges() và trả về một Promise
    saveAllChanges() 
        .then(response => {
            // Nếu lưu thành công vào DB rồi thì mới chuyển trang
            window.location.href = '/checkout';
        })
        .catch(error => {
            console.error("Lỗi khi lưu giỏ hàng:", error);
            alert("Không thể lưu trạng thái giỏ hàng, vui lòng thử lại!");
        });
}