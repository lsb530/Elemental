package com.example.elemental.postgre.model

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Table(name = "board", schema = "public")
@Entity
class Board(
    @Id
    @GeneratedValue
    @ColumnDefault("uuid_generate_v4()")
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var title: String,
    var content: String,
    var author: String,
) : BaseTimeEntity() {
    override fun toString(): String {
        return "Board(id=$id, name='$name', title='$title', content='$content', author='$author')"
    }
}