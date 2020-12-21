package com.v15h4l.wardrobe.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.v15h4l.wardrobe.model.Cloth

@Database(entities = [Cloth::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clothDao(): ClothDao
}