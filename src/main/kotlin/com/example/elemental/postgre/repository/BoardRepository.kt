package com.example.elemental.postgre.repository

import com.example.elemental.postgre.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BoardRepository : JpaRepository<Board, UUID>