package com.example.elemental

import com.example.elemental.extension.getFileSize
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@SpringBootApplication
class ElementalApplication

fun main(args: Array<String>) {
    val byteSize: Long = 2091264L
    byteSize.getFileSize()
//    println(byteSize.getFileSize())

    runApplication<ElementalApplication>(*args)
}
