package com.example.elemental.postgre.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(
        name = "created_at",
        columnDefinition = "timestamp(0) WITHOUT TIME ZONE",
        nullable = false,
        updatable = false
    )
    @ColumnDefault("now()")
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "updated_at", columnDefinition = "timestamp(0) WITHOUT TIME ZONE", nullable = true)
    @JsonIgnore
    var updatedAt: LocalDateTime? = null
}