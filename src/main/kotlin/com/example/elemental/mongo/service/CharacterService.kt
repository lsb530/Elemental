package com.example.elemental.mongo.service
import com.example.elemental.mongo.dto.CharacterDTO
import com.example.elemental.mongo.dto.CreateCharacterDTO
import com.example.elemental.mongo.model.Character
import com.example.elemental.mongo.repository.CharacterRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class CharacterService(
    private val characterRepository: CharacterRepository
) {
    private fun toDTO(character: Character): CharacterDTO {
        return CharacterDTO(id = character.id.toString(), name = character.name, age = character.age)
    }
    fun saveCharacter(createCharacterDTO: CreateCharacterDTO): CharacterDTO {
        val character = Character(
            id = ObjectId(),
            name = createCharacterDTO.name,
            age = createCharacterDTO.age
        )
        return toDTO(characterRepository.save(character))
    }

    fun getAllCharacters(): List<CharacterDTO> {
        return characterRepository.findAll().map {
            toDTO(it)
        }
    }

    fun getCharacterById(id: ObjectId): CharacterDTO {
        val character =
            characterRepository.findById(id).orElseThrow { RuntimeException("데이터 없음") }
        return toDTO(character)
    }
}
