package com.example.login.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/* thay đổi quick buy thành add cart  - đẩy vào giỏi hàng 	-- thêm giỏi hàng ở main page ở tùy chọn -- khi bấm vào thì vào list product order - 
 * add tick nhiều sp - cuối cùng có total giá - thực hiện đặt đơn thì đổi trạng thái - đang chờ xác nhận - (đặt xong thì )
 *  user-cart : onetomany -
 *  - khi đặt xong thì muốn xác nhận xem đơn hàng đã đặt chưa thì vào table cart tìm đơn hàng thằng user ...
 * .. 1 món 1 shipper - card cartitem order - order history 
 * 
*/
@Entity
@Audited
@Table(name = "computer")
public class Computer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;
    private String name;
    // 
    private String description;
    

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    // 🔥 NEW FIELD
    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    
    @Column(name = "stock_quantity")
    private Integer stockQuantity; // Số lượng tồn kho

    // ===== getter/setter =====

    public Long getId() {
        return id;
    }

    public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}