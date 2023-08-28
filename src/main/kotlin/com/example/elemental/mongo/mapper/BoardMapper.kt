package com.example.elemental.mongo.mapper

import com.example.elemental.mongo.document.Board
import com.example.elemental.mongo.dto.request.CreateBoard
import com.example.elemental.mongo.dto.response.BoardResponse
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers


@Mapper
interface BoardMapper {
    companion object {
        val INSTANCE: BoardMapper = Mappers.getMapper(BoardMapper::class.java)
    }

    @Mapping(target = "id", expression = "java(board.getId().toString())")
    fun convertDocumentToResponse(board: Board): BoardResponse

    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(new ObjectId())")
    fun createReqToDocument(createBoard: CreateBoard): Board

}