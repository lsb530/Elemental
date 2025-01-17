package com.example.elemental.config

import com.example.elemental.mongo.converter.StringToObjectIdConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
//        registry.addConverterFactory(StringToObjectIdConverterFactory())
        registry.addConverter(StringToObjectIdConverter())
    }
}