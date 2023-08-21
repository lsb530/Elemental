package com.example.elemental

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@SpringBootApplication
class ElementalApplication

fun main(args: Array<String>) {
    runApplication<ElementalApplication>(*args)
}
