package com.example.elemental.mongo.converter

import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory
import org.springframework.stereotype.Component

@Component
class StringToObjectIdConverterFactory : ConverterFactory<String, ObjectId> {
    override fun <T : ObjectId> getConverter(targetType: Class<T>): Converter<String, T> {
        return StringToObjectIdConverter(targetType)
    }

    private class StringToObjectIdConverter<T>(private val targetType: Class<T>) :
        Converter<String, T> {

        override fun convert(source: String): T {
            try {
                return targetType.getDeclaredConstructor(String::class.java).newInstance(source)
            } catch (ex: Exception) {
                throw RuntimeException("Failed to convert String to ObjectId", ex)
            }
        }
    }
}