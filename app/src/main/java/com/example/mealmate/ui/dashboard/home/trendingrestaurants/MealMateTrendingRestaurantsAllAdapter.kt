package com.example.mealmate.ui.dashboard.home.trendingrestaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.databinding.ItemMealmateTrendingRestaurantsAllBinding
import com.example.mealmate.model.MealMateTrendingRestaurant
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateTrendingRestaurantsAllAdapter internal constructor(private val listAuction: List<MealMateTrendingRestaurant>) : RecyclerView.Adapter<MealMateTrendingRestaurantsAllAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMealmateTrendingRestaurantsAllBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealMateTrendingRestaurant) {
            binding.apply {
                ivItem.loadImage(item.imageItem)
                tvTitleItem.text = item.itemName
                tvBid.text = item.bidItem.toString()
                tvEndIn.text = item.date

                btnPlaceBid.setOnClickListener {
                    it.toDetail("Place Bid")
                }
            }

            itemView.setOnClickListener {
                it.toDetail("Detail")
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealMateTrendingRestaurantsAllAdapter.ViewHolder {
        val binding = ItemMealmateTrendingRestaurantsAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealMateTrendingRestaurantsAllAdapter.ViewHolder, position: Int) {
        holder.bind(listAuction[position])
    }

    override fun getItemCount(): Int {
        return listAuction.size
    }

    private fun View.toDetail(data: String) {
        val activity = this.context as AppCompatActivity
        activity.supportFragmentManager.setFragmentResult(
            "DETAIL_TITLE",
            bundleOf("detail_title" to data)
        )
        activity.supportFragmentManager.commit {
            addToBackStack(null)
               // .replace(R.id.host_fragment_activity_main, NftDetailFragment())
        }
    }
}