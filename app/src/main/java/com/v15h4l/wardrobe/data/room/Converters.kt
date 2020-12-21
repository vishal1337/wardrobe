package com.v15h4l.wardrobe.data.room

import androidx.room.TypeConverter
import com.v15h4l.wardrobe.model.ClothType

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun tyoToInt(type: ClothType?): Int? = type?.id

        @TypeConverter
        @JvmStatic
        fun stringToImage(id: Int?): ClothType? =
            id?.let { ClothType.fromId(it) }
    }
}