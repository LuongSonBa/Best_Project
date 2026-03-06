document.addEventListener("DOMContentLoaded", () => {
    const editBtn = document.getElementById("editBtn");
    const updateBtn = document.getElementById("updateBtn");
    const imageInput = document.getElementById("imageInput");
    const previewImg = document.getElementById("previewImg");
    const imageInputSection = document.getElementById("imageInputSection");

    // 1. Hiệu ứng xem trước ảnh khi chọn file (Preview)
    if (imageInput) {
        imageInput.addEventListener("change", function() {
            const [file] = this.files;
            if (file) {
                previewImg.src = URL.createObjectURL(file);
            }
        });
    }

    // 2. Xử lý khi bấm nút Edit
	editBtn.addEventListener("click", () => {
	        // ... (giữ nguyên các đoạn ẩn/hiện text và input của bạn) ...

	        // 1. Ẩn nút Edit
	        editBtn.style.display = "none";

	        // 2. Hiện cái vùng chứa nút Update (thẻ cha của nó)
	        const editActions = document.getElementById("editActions");
	        if (editActions) {
	            editActions.style.display = "inline-block"; // Hiện cả cụm Update & Cancel
	        }

	        // 3. Đảm bảo bản thân nút updateBtn cũng được hiện (phòng hờ)
	        updateBtn.style.display = "inline-block";
	        
	        // Hiện phần upload ảnh (như bạn đã viết)
	        if (imageInputSection) {
	            imageInputSection.style.display = "block";
	        }
	    });

    // 3. Xử lý khi bấm nút Update
    updateBtn.addEventListener("click", () => {
        const id = document.getElementById("productId").value;
        const token = document.querySelector('meta[name="_csrf"]').content;
        const header = document.querySelector('meta[name="_csrf_header"]').content;

        // TẠO FORMDATA ĐỂ GỬI CẢ FILE VÀ JSON
        const formData = new FormData();

        const productData = {
            name: document.getElementById("nameInput").value,
            price: document.getElementById("priceInput").value,
            description: document.getElementById("descInput").value
        };

        const manufactureSelect = document.getElementById("manufactureId");
        if (manufactureSelect) {
            productData.manufactureId = manufactureSelect.value === "0" ? null : manufactureSelect.value;
        }
		const cancelBtn = document.getElementById("cancelBtn");
		if (cancelBtn) {
		    cancelBtn.addEventListener("click", () => {
		        location.reload(); // Cách nhanh nhất để reset lại toàn bộ giao diện và ảnh preview
		    });
		}

        // Thêm dữ liệu JSON dưới dạng Blob để Spring @RequestPart nhận diện được
        formData.append("data", new Blob([JSON.stringify(productData)], { type: "application/json" }));

        // Thêm file ảnh nếu user có chọn file mới
        if (imageInput && imageInput.files[0]) {
            formData.append("image", imageInput.files[0]);
        }

        // Gửi Fetch (Bỏ Content-Type: application/json đi nhé)
        fetch(`/api/products/${id}`, {
            method: "PUT",
            headers: {
                [header]: token
                // Lưu ý: KHÔNG set Content-Type ở đây, để trình duyệt tự lo Multipart
            },
            body: formData
        })
        .then(async res => {
            if (!res.ok) {
                let message = "Cập nhật thất bại!";
                const err = await res.json().catch(() => null);
                if (err) {
                    if (err.errors) {
                        message = err.errors.map(e => e.message).join("\n");
                    } else if (err.message) {
                        message = err.message;
                    }
                }
                alert(message);
                return;
            }

            alert("Cập nhật thành công!");
            location.reload();
        })
        .catch(err => {
            console.error("Fetch error:", err);
            alert("Đã xảy ra lỗi khi gửi yêu cầu!");
        });
    });
});