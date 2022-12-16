package com.example.marvelapp.framework.local

import com.example.core.data.repository.favorites.FavoritesLocalDataSource
import com.example.core.domain.model.Character
import com.example.marvelapp.framework.db.dao.FavoriteDao
import com.example.marvelapp.framework.db.entity.toCharactersModel
import com.example.marvelapp.framework.db.entity.toFavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getAllFavorites(): Flow<List<Character>> {
        return favoriteDao.loadFavorites().map {
            it.toCharactersModel()
        }
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return favoriteDao.hasFavorite(characterId) != null
    }

    override suspend fun saveFavorite(character: Character) {
        return favoriteDao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(character: Character) {
        return favoriteDao.deleteFavorite(character.toFavoriteEntity())
    }
}