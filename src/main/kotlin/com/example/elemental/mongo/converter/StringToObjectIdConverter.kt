package com.example.elemental.mongo.converter

import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StringToObjectIdConverter : Converter<String, ObjectId> {
    override fun convert(source: String): ObjectId? {
        return if (source.isEmpty()) null else ObjectId(source)
    }
}