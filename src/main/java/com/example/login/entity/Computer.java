package com.example.login.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import ch.qos.logback.core.status.Status;
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

@Entity
@Audited
@Table(name = "computer")
public class Computer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private BigDecimal price;
	private String name;
	public Status getStatus() {
		return status;
	}
	private String description;
	private LocalDateTime ExpiredDate;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Manufacture getManufacture() {
		return manufacture;
	}
	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getExpiredDate() {
		return ExpiredDate;
	}
	public void setExpiredDate(LocalDateTime expiredDate) {
		ExpiredDate = expiredDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
