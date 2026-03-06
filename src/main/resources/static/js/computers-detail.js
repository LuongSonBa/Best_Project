/**
 * Chuyển đổi giữa chế độ XEM và chế độ CHỈNH SỬA
 */
function toggleEditMode(isEditing) {
    const displayStyle = isEditing ? 'none' : 'block';
    const editStyle = isEditing ? 'block' : 'none';

    // 1. Các thành phần hiển thị (View Mode)
    const displayElements = [
        'display-name', 
        'display-price', 
        'display-description', 
        'display-manufacture', 
        'view-mode', 
        'btn-add-cart'
    ];
    
    // 2. Các thành phần nhập liệu (Edit Mode)
    const editElements = [
        'edit-name', 
        'edit-price-wrapper', 
        'edit-description', 
        'edit-manufacture', 
        'edit-mode'
    ];

    displayElements.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.style.display = displayStyle;
    });

    editElements.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.style.display = editStyle;
    });
}

/**
 * Gửi yêu cầu CẬP NHẬT dữ liệu (PUT)
 */
function saveChanges() {
    const id = document.getElementById('computer-id').value;
    const formData = new FormData();

    // Thu thập dữ liệu JSON từ các ô input
    const data = {
        name: document.getElementById('edit-name').value,
        price: document.getElementById('edit-price').value,
        description: document.getElementById('edit-description').value,
        manufactureId: document.getElementById('edit-manufacture').value
    };

    // Bọc JSON vào Blob để khớp với @RequestPart("data") bên Spring Boot
    formData.append('data', new Blob([JSON.stringify(data)], { type: 'application/json' }));
    
    // Kiểm tra và thêm file ảnh mới nếu Admin có chọn
    const imageFile = document.getElementById('edit-image').files[0];
    if (imageFile) {
        formData.append('image', imageFile);
    }

    // Hiển thị loading
    Swal.fire({ 
        title: 'Đang lưu...', 
        allowOutsideClick: false,
        didOpen: () => { Swal.showLoading() } 
    });

    fetch(`/api/computers/${id}`, {
        method: 'PUT',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            Swal.fire('Thành công!', 'Sản phẩm đã được cập nhật.', 'success')
                .then(() => location.reload()); // Reload để cập nhật ảnh và giá tiền mới
        } else {
            Swal.fire('Lỗi!', 'Cập nhật thất bại. Vui lòng kiểm tra lại quyền hạn!', 'error');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        Swal.fire('Lỗi!', 'Không thể kết nối tới server.', 'error');
    });
}

/**
 * Xác nhận XÓA sản phẩm (DELETE)
 */
function confirmDelete(id) {
    Swal.fire({
        title: 'Xác nhận xóa?',
        text: "Bạn không thể hoàn tác hành động này!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d9534f',
        cancelButtonColor: '#003d45',
        confirmButtonText: 'Vẫn xóa',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Hiển thị loading khi đang xóa
            Swal.fire({ title: 'Đang xóa...', didOpen: () => { Swal.showLoading() } });

            fetch(`/api/computers/${id}`, { 
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' }
            })
            .then(response => {
                if (response.ok) {
                    Swal.fire('Đã xóa!', 'Sản phẩm đã biến mất khỏi hệ thống.', 'success')
                        .then(() => window.location.href = '/computers');
                } else {
                    Swal.fire('Lỗi!', 'Bạn không có quyền hoặc sản phẩm không tồn tại.', 'error');
                }
            })
            .catch(error => {
                Swal.fire('Lỗi!', 'Không thể thực hiện lệnh xóa.', 'error');
            });
        }
    });
}