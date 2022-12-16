package com.example.core.data.repository.favorites

import com.example.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {

    fun getAllFavorites(): Flow<List<Character>>
    suspend fun isFavorite(characterId: Int): Boolean
    suspend fun saveFavorite(character: Character)
    suspend fun deleteFavorite(character: Character)
}