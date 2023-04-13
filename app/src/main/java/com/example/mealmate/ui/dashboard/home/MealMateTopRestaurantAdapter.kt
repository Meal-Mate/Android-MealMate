package com.example.mealmate.ui.dashboard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.databinding.ItemMealmateTopCollectionBinding
import com.example.mealmate.model.MealMateTopMates
import com.example.mealmate.model.MealMateTopRestaurants
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateTopRestaurantAdapter internal constructor(private val listCollections: List<MealMateTopRestaurants>) : RecyclerView.Adapter<MealMateTopRestaurantAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMealmateTopCollectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealMateTopRestaurants) {
            binding.apply {
                tvItemName.text = item.itemName
                ivItemCollection.loadImage(item.imageItem)
                val number = adapterPosition + 1
                tvNumber.text = number.toString()

                if (item.verified) {
                    ivVerify.visibility = View.VISIBLE
                } else {
                    ivVerify.visibility = View.GONE
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealMateTopRestaurantAdapter.ViewHolder {
        val binding = ItemMealmateTopCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealMateTopRestaurantAdapter.ViewHolder, position: Int) {
        holder.bind(listCollections[position])
    }

    override fun getItemCount(): Int {
        return listCollections.size
    }

}