$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	// ==== Xem thông tin người dùng ====
	$("#btnUserInfo").click(function() {
		$("#userInfoResult").html("<p>Đang tải thông tin...</p>");

		$.ajax({
			type: "GET",
			url: "/api/user/info",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data) {	
				$("#userInfoResult").html(
					`<p><strong>Tên:</strong> ${data.username}</p>
                     <p><strong>ID:</strong> ${data.id}</p>
                     <p><strong>Vai trò:</strong> ${data.role}</p>`
				);
			},
			error: function() {
				$("#userInfoResult").text("Có lỗi xảy ra.");
			},
		});
	});
	
	// ==== Hiện form đổi mật khẩu ====
	$("#show-change-password").click(function() {
		$("#change-password-form").toggle();
	});

	// ==== Đổi mật khẩu ====
	$("#submit-change-password").click(function() {
		const oldPass = $("#oldPassword").val();
		const newPass = $("#newPassword").val();

		if (newPass.length < 6 || newPass === oldPass) {
			alert("Mật khẩu mới phải ít nhất 6 ký tự và không trùng mật khẩu cũ!");
			return;
		}

		$.ajax({
			type: "PUT",
			url: "/api/user/change-password",
			contentType: "application/json",
			data: JSON.stringify({
				oldPassword: oldPass,
				newPassword: newPass,
			}),
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(response) {
				$("#changePasswordResult").text(response);
				$("#oldPassword").val("");
				$("#newPassword").val("");
			},
			error: function(xhr) {
				alert("Lỗi: " + xhr.responseText);
			},
		});
	});

	// ==== Xem tất cả sản phẩm (không phân trang) ====
	$("#btnGetProducts").click(function() {
		$("#productList").toggle();

		$.ajax({
			type: "GET",
			url: "/api/product/all",
			beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
			},
			success: function(data) { // 
				let html =
					"<table class='product-table'>" +
					"<tr><th>ID</th><th>Tên sản phẩm</th><th>Giá (USD)</th><th>Mô tả</th></tr>";

				data.forEach((p) => {
					html += `<tr>
                               <td>${p.id}</td>
                               <td>${p.name}</td>
                               <td>${p.price}</td>
                               <td>${p.description}</td>
                             </tr>`;
				});

				html += "</table>";
				$("#productList").html(html);
			},
			error: function(xhr) {
				alert("Lỗi: " + xhr.responseText);
			},
		});
	});
	});