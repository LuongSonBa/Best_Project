function fetchCheckoutData() {
	fetch('/api/checkout/data')
		.then(response => {
			if (!response.ok) throw new Error("Không thể lấy dữ liệu thanh toán");
			return response.json();
		})
		.then(data => {
			// data là CheckoutResponseDto { items: [], total: ... }
			const items = data.items || [];
			const total = data.total || 0;

			const container = document.getElementById('checkoutItems');

			if (items.length === 0) {
				alert("Giỏ hàng trống hoặc chưa chọn sản phẩm nào!");
				window.location.href = '/cart';
				return;
			}

			// Render danh sách món đồ
			container.innerHTML = items.map(item => `
                <div class="row" style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #666;">${item.computerName} (x${item.quantity})</span>
                    <span style="font-weight: bold;">${(item.subtotal || (item.price * item.quantity)).toLocaleString()} ¥</span>
                </div>
            `).join('');

			// Cập nhật tổng tiền chốt từ Backend
			const formattedTotal = total.toLocaleString() + " ¥";
			document.getElementById('subtotal').innerText = formattedTotal;
			document.getElementById('finalTotal').innerText = formattedTotal;
		})
		.catch(error => {
			console.error('Error:', error);
			document.getElementById('checkoutItems').innerHTML = "<p style='color:red;'>Lỗi tải thông tin đơn hàng.</p>";
		});
}

// Hàm đặt hàng (Mày nhớ thêm hàm này vào cùng file JS luôn nhé)
function placeOrder() {
	const phone = document.getElementById('phoneNumber').value.trim();
	const address = document.getElementById('shippingAddress').value.trim();

	if (!phone || !address) {
		alert("Vui lòng nhập đầy đủ SĐT và Địa chỉ giao hàng!");
		return;
	}

	const orderRequest = {
		phoneNumber: phone,
		shippingAddress: address
	};

	fetch('/api/orders/create', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(orderRequest)
	})
		.then(async res => {
			if (res.ok) {
				const order = await res.json();
				alert("Đặt hàng thành công! Mã đơn: #" + order.id);
				window.location.href = "/orders/detail/" + order.id ; // Hoặc trang thành công
			} else {
				const msg = await res.text();
				alert("Lỗi: " + msg);
			}
		})
		.catch(err => alert("Lỗi hệ thống!"));
}

document.addEventListener("DOMContentLoaded", fetchCheckoutData);