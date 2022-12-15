package com.example.marvelapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.DbConstants

@Entity(tableName = DbConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String,
)

fun List<FavoriteEntity>.toCharactersModel() = map {
    com.example.core.domain.model.Character(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl
    )
}


fun com.example.core.domain.model.Character.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}
