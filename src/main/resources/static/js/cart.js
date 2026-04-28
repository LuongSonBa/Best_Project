/**
 * Thêm sản phẩm vào giỏ hàng qua API
 */
async function addToCart(productId) {
    const userId = 1; // Tạm thời fix cứng
    const apiUrl = `/api/cart/add?userId=${userId}`;
    
    const requestData = {
        productId: productId,
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
            console.log("Success:", data);
            
            // Hiệu ứng cập nhật số lượng
            updateBadge();
        } else {
            alert("Lỗi khi thêm vào giỏ hàng!");
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

/**
 * Cập nhật con số trên icon giỏ hàng
 */
function updateBadge() {
    const badge = document.getElementById('cart-badge');
    if (badge) {
        let count = parseInt(badge.innerText) || 0;
        badge.innerText = count + 1;
        
        // Thêm hiệu ứng nháy
        badge.classList.add('pulse');
        setTimeout(() => {
            badge.classList.remove('pulse');
        }, 300);
    }
}