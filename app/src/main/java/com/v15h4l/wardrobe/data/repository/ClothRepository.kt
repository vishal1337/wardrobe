package com.v15h4l.wardrobe.data.repository

import com.v15h4l.wardrobe.data.room.ClothDao
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.model.ClothType
import javax.inject.Inject

class ClothRepository @Inject constructor(private val clothDao: ClothDao) {

    /**
     * Fetch Shirts
     */
    fun getShirts() = clothDao.getCloths(ClothType.SHIRT)

    /**
     * Fetch Pants
     */
    fun getPants() = clothDao.getCloths(ClothType.PANT)

    /**
     * add a cloth to the catalogue
     */
    suspend fun addCloth(cloth: Cloth) {
        clothDao.insertCloth(cloth)
    }

    /**
     * add cloths to the catalogue
     */
    suspend fun addCloths(cloths: List<Cloth>) {
        clothDao.insertCloths(cloths)
    }

}