package com.v15h4l.wardrobe.ui.home

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v15h4l.wardrobe.R
import com.v15h4l.wardrobe.data.repository.ClothRepository
import com.v15h4l.wardrobe.model.Cloth
import com.v15h4l.wardrobe.model.ClothType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    @ApplicationContext private val context: Context,
    private val clothRepository: ClothRepository
) : ViewModel() {

    val shirts = clothRepository.getShirts()
    val pants = clothRepository.getPants()

    fun addClothToCatalogue(cloth: Cloth) {
        viewModelScope.launch {
            clothRepository.addCloth(cloth)
        }
    }

    init {
        // Initialise DB with default Values
        viewModelScope.launch {
            clothRepository.addCloths(getSampleCloths())
        }
    }

    private fun getSampleCloths() = mutableListOf<Cloth>().apply {
        repeat(10) {
            add(
                Cloth(
                    id = it,
                    imgRes = if (it % 2 == 0) R.drawable.lake else R.drawable.mountain,
                    if (it % 2 == 0) ClothType.PANT else ClothType.SHIRT
                )
            )
        }
    }

}