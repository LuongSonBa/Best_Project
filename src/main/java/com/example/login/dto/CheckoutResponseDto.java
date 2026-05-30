package com.example.login.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Cần thiết để Jackson map JSON
public class CheckoutResponseDto {
    private List<CheckoutItemDto> items;
    private BigDecimal total;

    // Viết thủ công Constructor này để Eclipse chắc chắn nhận diện được
    public CheckoutResponseDto(List<CheckoutItemDto> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

	public List<CheckoutItemDto> getItems() {
		return items;
	}

	public void setItems(List<CheckoutItemDto> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
    
}