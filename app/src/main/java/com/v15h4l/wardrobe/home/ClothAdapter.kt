package com.v15h4l.wardrobe.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.v15h4l.wardrobe.databinding.ItemClothBinding
import com.v15h4l.wardrobe.model.Cloth

/**
 * Cloth Adapter used to Display  Cloth Items
 */
class ClothAdapter : ListAdapter<Cloth, ClothViewHolder>(ClothDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothViewHolder =
        ClothViewHolder(
            ItemClothBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ClothViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ClothViewHolder(private val binding: ItemClothBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cloth: Cloth) {
        binding.cloth = cloth
        binding.executePendingBindings()
    }
}

private class ClothDiffCallback : DiffUtil.ItemCallback<Cloth>() {
    override fun areItemsTheSame(
        oldItem: Cloth,
        newItem: Cloth
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Cloth,
        newItem: Cloth
    ): Boolean =
        oldItem.toString() == newItem.toString()

}