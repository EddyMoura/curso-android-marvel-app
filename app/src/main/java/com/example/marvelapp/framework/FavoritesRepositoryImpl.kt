package com.example.marvelapp.framework

import com.example.core.data.repository.favorites.FavoritesLocalDataSource
import com.example.core.data.repository.favorites.FavoritesRepository
import com.example.core.domain.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesLocalDataSource: FavoritesLocalDataSource
): FavoritesRepository {

    override fun getAllFavorites(): Flow<List<Character>> {
        return favoritesLocalDataSource.getAllFavorites()
    }

    override suspend fun saveFavorite(character: Character) {
        return favoritesLocalDataSource.saveFavorite(character)
    }

    override suspend fun deleteFavorite(character: Character) {
        return favoritesLocalDataSource.deleteFavorite(character)
    }
}