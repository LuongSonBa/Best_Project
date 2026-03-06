function toggleCreateMode(isCreating) {
    const createBox = document.getElementById("create-mode");
    if (createBox) {
        createBox.style.display = isCreating ? "block" : "none";
    }
}

function createProduct() {

    const formData = new FormData();

    const data = {
        name: document.getElementById('create-name').value,
        price: document.getElementById('create-price').value,
        description: document.getElementById('create-description').value,
        manufactureId: document.getElementById('create-manufacture').value
    };

    formData.append(
        'data',
        new Blob([JSON.stringify(data)], { type: 'application/json' })
    );

    const imageFile = document.getElementById('create-image').files[0];
    if (imageFile) {
        formData.append('image', imageFile);
    }

    Swal.fire({
        title: 'Đang tạo...',
        allowOutsideClick: false,
        didOpen: () => { Swal.showLoading() }
    });

    fetch('/api/computers', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            Swal.fire('Thành công!', 'Sản phẩm mới đã được tạo.', 'success')
                .then(() => window.location.href = '/computers');
        } else {
            Swal.fire('Lỗi!', 'Không thể tạo sản phẩm. Kiểm tra quyền ADMIN!', 'error');
        }
    })
    .catch(error => {
        Swal.fire('Lỗi!', 'Không thể kết nối tới server.', 'error');
    });
}