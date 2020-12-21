package com.v15h4l.wardrobe.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.model.ClothType

/**
 * Data Access Object for Cloths
 */
@Dao
interface ClothDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCloth(cloth: Cloth)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCloths(cloths: List<Cloth>)

    @Query("SELECT * FROM cloths WHERE type = :type")
    fun getCloths(type: ClothType): LiveData<List<Cloth>>

    @Query("SELECT * FROM cloths")
    fun getCloths(): LiveData<List<Cloth>>

    @Update
    suspend fun update(cloth: Cloth)

    @Delete
    suspend fun delete(cloth: Cloth)

    @Query("DELETE FROM cloths")
    suspend fun deleteAllCloths()

}