document.addEventListener("DOMContentLoaded", () => {
    const editBtn = document.getElementById("editBtn");
    const updateBtn = document.getElementById("updateBtn");

    editBtn.addEventListener("click", () => {
        // Ẩn text, hiện input
        document.getElementById("nameText").style.display = "none";
        document.getElementById("addressText").style.display = "none";
        document.getElementById("phoneText").style.display = "none";
        document.getElementById("emailText").style.display = "none";
        document.getElementById("descText").style.display = "none";

        document.getElementById("nameInput").style.display = "inline";
        document.getElementById("addressInput").style.display = "inline";
        document.getElementById("phoneInput").style.display = "inline";
        document.getElementById("emailInput").style.display = "inline";
        document.getElementById("descInput").style.display = "block";

        editBtn.style.display = "none";
        updateBtn.style.display = "inline";
    });

    updateBtn.addEventListener("click", () => {
        const updatedManufacture = {
            name: document.getElementById("nameInput").value,
            address: document.getElementById("addressInput").value,
            phone: document.getElementById("phoneInput").value,
            email: document.getElementById("emailInput").value,
            description: document.getElementById("descInput").value
        };

        const id = document.getElementById("manufactureId").value;

        const token = document.querySelector('meta[name="_csrf"]').content;
        const header = document.querySelector('meta[name="_csrf_header"]').content;

        fetch(`/api/manufactures/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                [header]: token
            },
            body: JSON.stringify(updatedManufacture)
        })
        .then(res => {
            if (res.ok) {
                alert("Cập nhật thành công!");
                location.reload(); // reload lại để cập nhật dữ liệu mới
            } else {
                alert("Cập nhật thất bại!");
            }
        })
        .catch(err => console.error("Error:", err));
    });
});
