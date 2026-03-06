document.addEventListener("DOMContentLoaded", function () {
    const saveBtn = document.getElementById("saveBtn");

    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

    saveBtn.addEventListener("click", async function () {
        // Lấy giá trị các input
        let manufactureId = document.getElementById("manufactureId").value;
        if (manufactureId === "") manufactureId = null;

        const imageFile = document.getElementById("image").files[0];
        if (!imageFile) {
            alert("Image is required!");
            return;
        }

        const product = {
            name: document.getElementById("name").value,
            price: parseFloat(document.getElementById("price").value),
            description: document.getElementById("description").value,
            active: document.getElementById("active").value === "true",
            manufactureId: manufactureId
        };

        // FormData để gửi file + JSON
        const formData = new FormData();
        formData.append("image", imageFile);
        formData.append("data", new Blob([JSON.stringify(product)], { type: "application/json" }));

        try {
            const res = await fetch("http://localhost:9095/api/products", {
                method: "POST",
                headers: {
                    [csrfHeader]: csrfToken
                },
                body: formData
            });

            if (res.ok) {
                const data = await res.json();
                alert("Product created! ID: " + data.id);
                window.location.href = "/products";
            } else {
                const error = await res.text();
                alert("Save failed: " + error);
            }
        } catch (err) {
            console.error(err);
            alert("Save failed: " + err.message);
        }
    });
});
