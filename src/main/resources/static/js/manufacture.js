document.addEventListener("DOMContentLoaded", function () {
	const addBtn = document.getElementById("addManufactureBtn");

	// Lấy token và header CSRF từ meta tag trong <head>
	const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
	const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

	// --- 1️⃣ THÊM NHÀ SẢN XUẤT ---
	if (addBtn) {
		addBtn.addEventListener("click", async function (e) {
			e.preventDefault();

			const { value: formValues } = await Swal.fire({
				title: "➕ Add Manufacturer",
				html: `
				  <input id="swal-name" class="swal2-input" placeholder="Name" required>
				  <input id="swal-address" class="swal2-input" placeholder="Address" required>
				  <input id="swal-phone" class="swal2-input" placeholder="Phone" required>
				  <input id="swal-email" class="swal2-input" placeholder="Email" required>
				  <textarea id="swal-description" class="swal2-textarea" placeholder="Description"></textarea>
				`,
				focusConfirm: false,
				showCancelButton: true,
				confirmButtonText: "Save",
				cancelButtonText: "Cancel",
				preConfirm: () => {
					const name = document.getElementById("swal-name").value.trim();
					const address = document.getElementById("swal-address").value.trim();
					const phone = document.getElementById("swal-phone").value.trim();
					const email = document.getElementById("swal-email").value.trim();
					const description = document.getElementById("swal-description").value.trim();

					if (!name || !address || !phone || !email) {
						Swal.showValidationMessage("Please fill in all required fields!");
						return false;
					}
					return { name, address, phone, email, description };
				},
			});

			if (!formValues) return;

			const manufactureData = {
				name: formValues.name,
				address: formValues.address,
				phone: formValues.phone,
				email: formValues.email,
				description: formValues.description,
			};

			try {
				const response = await fetch("/api/manufactures", {
					method: "POST",
					headers: {
						"Content-Type": "application/json",
						[csrfHeader]: csrfToken,
					},
					body: JSON.stringify(manufactureData),
				});

				if (!response.ok) {
					const errorData = await response.json();

					let errorHtml = "";

					if (errorData.errors && Array.isArray(errorData.errors)) {
						errorHtml = errorData.errors
							.map(e => `• <b>${e.field}</b>: ${e.message}`)
							.join("<br>");
					} else {
						errorHtml = errorData.message || "Unknown error";
					}

					await Swal.fire({
						icon: "error",
						title: "❌ Validation Error",
						html: errorHtml,
					});

					return;
				}

				await Swal.fire({
					icon: "success",
					title: "✅ Manufacturer added successfully!",
					showConfirmButton: false,
					timer: 1500,
				});

				location.reload();

			} catch (error) {
				console.error("Error:", error);
				Swal.fire(
					"❌ Error",
					"Failed to add manufacturer. Please try again!",
					"error"
				);
			}
		});
	}


	// --- 2️⃣ XÓA NHÀ SẢN XUẤT ---
	document.querySelectorAll(".delete-btn").forEach(btn => {
		btn.addEventListener("click", function (e) {
			e.preventDefault();

			const id = this.getAttribute("data-id");

			Swal.fire({
				title: "Are you sure?",
				text: "This action cannot be undone!",
				icon: "warning",
				showCancelButton: true,
				confirmButtonColor: "#d33",
				cancelButtonColor: "#3085d6",
				confirmButtonText: "Yes, delete it!",
			}).then(result => {
				if (result.isConfirmed) {
					fetch(`/api/manufactures/${id}`, {
						method: "DELETE",
						headers: {
							[csrfHeader]: csrfToken,
						},
					})
						.then(response => {
							if (response.ok) {
								Swal.fire({
									icon: "success",
									title: "🗑️ Deleted successfully!",
									showConfirmButton: false,
									timer: 1500,
								});
								setTimeout(() => location.reload(), 1500);
							} else {
								Swal.fire("❌ Error", "Failed to delete manufacturer!", "error");
							}
						})
						.catch(error => {
							console.error("Error:", error);
							Swal.fire("❌ Error", "An error occurred while deleting!", "error");
						});
				}
			});
		});
	});
});
