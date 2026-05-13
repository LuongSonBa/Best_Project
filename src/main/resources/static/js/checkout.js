function fetchCheckoutData() {
    // 1. Phải gọi đúng API của Checkout mà mình vừa viết
    fetch('/api/checkout/data') 
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok");
            return response.json();
        })
        .then(data => {
            // data lúc này chính là CheckoutResponseDto của bạn
            // Nó có 2 trường: data.items và data.total
            const items = data.items || [];
            const total = data.total || 0; 

            const container = document.getElementById('checkoutItems');

            if (items.length === 0) {
                alert("No items selected for checkout.");
                window.location.href = '/cart';
                return;
            }

            // 2. Render danh sách món đồ (Dùng data.items đã được lọc từ Backend)
            container.innerHTML = items.map(item => {
                // Lưu ý: Backend dùng BigDecimal thì JS nhận về vẫn là số
                const price = item.price || 0;
                const quantity = item.quantity || 0;
                const subTotal = price * quantity;
                
                return `
                    <div class="row" style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                        <span style="color: #666;">${item.computerName} x${quantity}</span>
                        <span style="font-weight: bold;">${subTotal.toLocaleString()} ¥</span>
                    </div>
                `;
            }).join('');

            // 3. Cập nhật tổng tiền (Lấy trực tiếp data.total từ Backend cho chính xác)
            const formattedTotal = total.toLocaleString() + " ¥";
            document.getElementById('subtotal').innerText = formattedTotal;
            document.getElementById('finalTotal').innerText = formattedTotal;
        })
        .catch(error => {
            console.error('Error fetching checkout data:', error);
            document.getElementById('checkoutItems').innerHTML = "<p style='color:red;'>Error loading checkout summary.</p>";
        });
		
		
}
document.addEventListener("DOMContentLoaded", function () {
    fetchCheckoutData();
});