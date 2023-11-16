package com.sergio.jwt.backend.entites;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "created_by", nullable = false, updatable = false)
	@CreatedBy
	private String createdBy = "ADM";

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamp")
	@CreationTimestamp
	private OffsetDateTime createdAt;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_at", columnDefinition = "timestamp")
	@UpdateTimestamp
	private OffsetDateTime updatedAt;

}
