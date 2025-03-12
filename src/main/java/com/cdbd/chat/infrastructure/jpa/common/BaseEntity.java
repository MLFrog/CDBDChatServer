package com.cdbd.chat.infrastructure.jpa.common;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
	@Column(updatable = false)
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = Timestamp.from(Instant.now());
		updatedAt = Timestamp.from(Instant.now());
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = Timestamp.from(Instant.now());
	}
}
