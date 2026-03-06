package com.example.login.entity;

import java.time.Instant;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	  public BaseEntity() {}
	 @CreatedDate
	  @Column(name = "created_at")
	  private Instant createdAt;

	  @LastModifiedDate
	  @Column(name = "modified_at")
	  private Instant modifiedAt;

	  @CreatedBy
	  @Column(name = "created_by")
	  private String createdBy;

	  @LastModifiedBy
	  @Column(name = "modified_by")
	  private String modifiedBy;
	
	  @Version
	  @Column(name = "version")
	  private Long version = 0L;


	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Instant modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	



}