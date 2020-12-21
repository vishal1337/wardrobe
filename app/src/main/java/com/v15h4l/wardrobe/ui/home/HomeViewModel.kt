package com.v15h4l.wardrobe.ui.home

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v15h4l.wardrobe.R
import com.v15h4l.wardrobe.data.repository.ClothRepository
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.model.ClothType
import com.v15h4l.wardrobe.utils.resourceUri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @ApplicationContext private val context: Context,
    private val clothRepository: ClothRepository
) : ViewModel() {

    val shirts = clothRepository.getShirts()
    val pants = clothRepository.getPants()

    // TODO: 22/12/20 This can be moved to database to make the Favorites available across App Restarts.
    private val favoritePairs: MutableMap<String, Boolean> = mutableMapOf()

    fun addClothToCatalogue(cloth: Cloth) {
        viewModelScope.launch {
            clothRepository.addCloth(cloth)
        }
    }

    fun setIsPairFavorite(shirtId: Int, pantId: Int) {
        if (isPairFavorite(shirtId, pantId))
            favoritePairs.remove(getFavPairKey(shirtId, pantId))
        else
            favoritePairs[getFavPairKey(shirtId, pantId)] = true
    }

    fun isPairFavorite(shirtId: Int, pantId: Int): Boolean =
        favoritePairs[getFavPairKey(shirtId, pantId)] == true

    // To avoid using wrong id format
    private fun getFavPairKey(shirtId: Int, pantId: Int) = "$shirtId'_'$pantId"

    init {
        // Initialise DB with default Values
        viewModelScope.launch {
            clothRepository.addCloths(getSampleCloths())
        }
    }

    // Added a dummy Pair
    private fun getSampleCloths() = mutableListOf(
        Cloth(
            id = 1,
            imagePath = context.resourceUri(R.drawable.shirt).toString(),
            ClothType.SHIRT
        ),
        Cloth(
            id = 2,
            imagePath = context.resourceUri(R.drawable.pant).toString(),
            ClothType.PANT
        )
    )

}