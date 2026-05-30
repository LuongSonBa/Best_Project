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
    const rows = document.querySelectorAll('tr[id^="cart-item-"]');

    // Nếu không còn item nào → reset totalPrice và thoát
    if (rows.length === 0) {
        const totalPriceElem = document.getElementById('total-price');
        if (totalPriceElem) {
            totalPriceElem.innerText = '0 ¥';
        }
        return;
    }

    rows.forEach(row => {
        const idStr = row.id.replace('cart-item-', '');
        const qty = parseInt(row.querySelector('.quantity-input').value);

        requestData.push({
            cartItemId: parseInt(idStr),
            quantity: qty,
            isSelected: true
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

            // Cập nhật tổng tiền
            const totalPriceElem = document.getElementById('total-price');
            if (totalPriceElem) {
                totalPriceElem.innerText = formatter.format(data.totalPrice) + ' ¥';
            }

            // Cập nhật subtotal từng dòng
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
    if (!confirm("Xóa nhé?")) return;
    
    try {
        const res = await fetch(`/api/cart/delete/${itemId}`, { method: 'DELETE' });
        if (res.ok) {
            const row = document.getElementById(`cart-item-${itemId}`);
            if (row) row.remove();
            
            const remainingItems = document.querySelectorAll('tr[id^="cart-item-"]');
            if (remainingItems.length === 0) {
                location.reload(); 
            } else {
                saveAllChanges();
            }
        }
    } catch (e) {
        console.error("Lỗi xóa:", e);
    }
}
let cartItems = Array.from(document.querySelectorAll('tr[id^="cart-item-"]'));
function getCartItems() {
    return Array.from(document.querySelectorAll('tr[id^="cart-item-"]'));
}
function checkout() {
    console.log("Preparing to navigate to the Checkout page...");

    // LẤY DỮ LIỆU MỚI NHẤT TẠI THỜI ĐIỂM BẤM NÚT
    const currentItems = document.querySelectorAll('tr[id^="cart-item-"]');

    // 1. Kiểm tra giỏ hàng có trống không
    if (currentItems.length === 0) {
        alert("Giỏ hàng của mày đang trống không, mua cái gì mà đòi thanh toán? Kkk");
        return;
    }

    // 2. Kiểm tra xem có món nào được CHỌN (isSelected) không? 
    // Nếu mày có logic checkbox, thì phải check xem có thằng nào được tích không nữa mới chuẩn.
    
    // 3. Lưu toàn bộ rồi mới đi
    saveAllChanges()
        .then(() => {
            window.location.href = '/checkout';
        })
        .catch(error => {
            console.error("Failed to save cart:", error);
            alert("Không lưu được giỏ hàng, thử lại xem nào!");
        });
}