package dev.lchang.appdpa.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeamDao {

    @Query("SELECT * FROM favorite_teams")
    fun getAllFavorites(): Flow<List<FavoriteTeamEntity>>

    @Query("SELECT id FROM favorite_teams")
    fun getFavoriteIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(team: FavoriteTeamEntity)

    @Delete
    suspend fun deleteFavorite(team: FavoriteTeamEntity)

    @Query("DELETE FROM favorite_teams WHERE id = :teamId")
    suspend fun deleteFavoriteById(teamId: Int)
}
