document.addEventListener('DOMContentLoaded', function() {
    loadOrderDetail();
});

async function loadOrderDetail() {
    try {
        const response = await fetch(`/api/orders/${ORDER_ID}`);
        if (!response.ok) throw new Error("Không thể tải thông tin đơn hàng");
        
        const order = await response.json();
        renderOrder(order);
    } catch (error) {
        console.error("Lỗi:", error);
        alert(error.message);
    }
}

function renderOrder(order) {
    // 1. Header & Status
    document.getElementById('displayOrderId').innerText = order.id;
    const statusBadge = document.getElementById('statusBadge');
    
    // Mapping trạng thái sang tiếng Việt cho thân thiện
    const statusMap = {
        'PENDING': { text: 'Chờ xác nhận', class: 'bg-warning text-dark' },
        'SHIPPING': { text: 'Đang giao hàng', class: 'bg-info text-white' },
        'COMPLETED': { text: 'Đã hoàn thành', class: 'bg-success text-white' },
        'CANCELLED': { text: 'Đã hủy', class: 'bg-danger text-white' }
    };
    
    const statusInfo = statusMap[order.status] || { text: order.status, class: 'bg-secondary text-white' };
    statusBadge.innerText = statusInfo.text;
    statusBadge.className = `badge rounded-pill ${statusInfo.class}`;

    // 2. Customer Info
    document.getElementById('displayPhone').innerText = order.phoneNumber || 'N/A';
    document.getElementById('displayAddress').innerText = order.shippingAddress || 'N/A';
    document.getElementById('displayDate').innerText = new Date(order.createdAt).toLocaleString('vi-VN');

    // 3. Items list - ĐÃ SỬA ĐỂ HIỆN ẢNH BASE64
    const container = document.getElementById('orderItemsContainer');
    container.innerHTML = order.items.map(item => `
        <div class="d-flex align-items-center mb-3 p-2 border-bottom">
            <img src="${item.imageBase64 || '/images/default-pc.png'}" 
                 class="img-fluid rounded me-3" 
                 style="width: 80px; height: 80px; object-fit: cover;" 
                 alt="product"
                 onerror="this.src='/images/default-pc.png'"> <div class="flex-grow-1">
                <h6 class="mb-1">${item.computerName}</h6>
                <small class="text-muted">Số lượng: ${item.quantity}</small>
                <p class="mb-0 text-primary small">${item.priceAtPurchase.toLocaleString()} ¥ / sản phẩm</p>
            </div>
            <div class="text-end">
                <p class="mb-0 fw-bold">${(item.priceAtPurchase * item.quantity).toLocaleString()} ¥</p>
            </div>
        </div>
    `).join('');

    // 4. Totals
    const totalStr = order.totalAmount.toLocaleString() + " ¥";
    document.getElementById('subTotal').innerText = totalStr;
    document.getElementById('finalTotal').innerText = totalStr;

    // 5. Action Button (Hủy đơn)
    const actionArea = document.getElementById('actionArea');
    if (order.status === 'PENDING') {
        actionArea.innerHTML = `
            <button onclick="cancelOrder()" class="btn btn-danger btn-lg shadow-sm w-100">
                <i class="fas fa-times-circle me-2"></i> Hủy đơn hàng
            </button>
        `;
    } else {
        actionArea.innerHTML = `
            <button class="btn btn-outline-primary btn-lg w-100" onclick="window.print()">
                <i class="fas fa-print me-2"></i> In hóa đơn
            </button>
        `;
    }
}

async function cancelOrder() {
    if (!confirm("Mày có chắc chắn muốn hủy đơn hàng này không?")) return;

    try {
        const response = await fetch(`/api/orders/${ORDER_ID}/cancel`, {
            method: 'POST'
        });

        if (response.ok) {
            alert("Đơn hàng đã được hủy thành công!");
            location.reload();
        } else {
            const msg = await response.text();
            alert("Lỗi: " + msg);
        }
    } catch (error) {
        alert("Lỗi hệ thống khi hủy đơn!");
    }
}