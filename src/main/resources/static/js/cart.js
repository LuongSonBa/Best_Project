/**
 * Thêm sản phẩm vào giỏ hàng
 * Tham số 'id' nhận vào từ HTML (ví dụ: addToCart(6))
 */
async function addToCart(id) {
    console.log("Đang thêm sản phẩm ID:", id); // Log để kiểm tra xem hàm có chạy không
    
    if (!id) {
        console.error("Lỗi: Không tìm thấy ID sản phẩm!");
        return;
    }

    const userId = 1; 
    const apiUrl = `/api/cart/add?userId=${userId}`;
    
    const requestData = {
        computerId: parseInt(id), // Ép kiểu số cho chắc chắn
        quantity: 1
    };

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Thành công:", data);
            updateBadge();
        } else {
            const err = await response.text();
            console.error("Server trả lỗi:", err);
            alert("Không thể thêm vào giỏ hàng. Kiểm tra log server!");
        }
    } catch (error) {
        console.error("Lỗi kết nối API:", error);
    }
}

function updateBadge() {
    const badge = document.getElementById('cart-badge');
    if (badge) {
        let count = parseInt(badge.innerText) || 0;
        badge.innerText = count + 1;
        badge.classList.add('pulse');
        setTimeout(() => badge.classList.remove('pulse'), 300);
    }
}