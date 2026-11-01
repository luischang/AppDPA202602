package dev.lchang.appdpa.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_teams")
data class FavoriteTeamEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val code: String?,
    val logo: String,
    val country: String,
    val founded: Int?,
    val national: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)
