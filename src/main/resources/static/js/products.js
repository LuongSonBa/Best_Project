btn.addEventListener('click', () => {	
    const id = btn.getAttribute('data-id');
    if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này?")) {
        
        // Thêm trạng thái chờ
        btn.disabled = true;
        btn.innerText = "Deleting...";

        fetch(`/api/products/${id}`, {
            method: "DELETE",
            headers: {
                [csrfHeader]: csrfToken
            }
        })
        .then(res => {
            if (res.ok) {
                const row = btn.closest('tr');
                // Hiệu ứng mờ dần trước khi biến mất (nếu có CSS)
                row.style.opacity = '0.5'; 
                setTimeout(() => row.remove(), 300);
            } else {
                alert("Xóa thất bại!");
                btn.disabled = false;
                btn.innerText = "Delete";
            }
        })
        .catch(err => {
            console.error("Error:", err);
            btn.disabled = false;
        });
    }
});