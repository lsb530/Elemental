package com.example.elemental.mongo

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

abstract class BaseTimeEntity {
    @CreatedDate
    var createdAt: LocalDateTime? = null
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
}