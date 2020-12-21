package com.v15h4l.wardrobe.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cloths")
data class Cloth(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var imagePath: String?,
    var type: ClothType?
)

enum class ClothType(val id: Int) {
    SHIRT(1),
    PANT(2);

    companion object {
        fun fromId(id: Int): ClothType? = values().find { it.id == id }
    }
}