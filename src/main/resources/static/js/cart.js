async function addToCart(id) {
    console.log("Đang thêm sản phẩm ID:", id);
    
    if (!id) {
        console.error("Lỗi: Không tìm thấy ID sản phẩm!");
        return;
    }

    // URL SẠCH: Không còn ?userId=1 nữa
    const apiUrl = `/api/cart/add`;
    
    const requestData = {
        computerId: parseInt(id),
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
            
            // Hiệu ứng badge
            updateBadge();
            
            // Có thể thêm thông báo nhỏ cho người dùng
            alert("Đã thêm sản phẩm vào giỏ hàng!");
        } else if (response.status === 403 || response.status === 401) {
            // Nếu chưa login thì Server sẽ trả về 401 hoặc 403
            alert("Vui lòng đăng nhập để thêm vào giỏ hàng!");
            window.location.href = "/login";
        } else {
            console.error("Lỗi server:", await response.text());
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