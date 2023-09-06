package com.example.elemental.mongo.mapper

import com.example.elemental.mongo.document.Board
import com.example.elemental.mongo.dto.request.CreateBoardRequest
import com.example.elemental.mongo.dto.response.BoardResponse
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers


@Mapper
interface MongoBoardMapper {
    companion object {
        val INSTANCE: MongoBoardMapper = Mappers.getMapper(MongoBoardMapper::class.java)
    }

    @Mapping(target = "id", expression = "java(board.getId().toString())")
    fun convertDocumentToResponse(board: Board): BoardResponse

    @InheritInverseConfiguration
    @Mapping(target = "id", expression = "java(new ObjectId())")
    fun createReqToDocument(createBoardRequest: CreateBoardRequest): Board

}