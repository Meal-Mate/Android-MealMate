package com.example.mealmate.ui.dashboard.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.databinding.ItemMealmateSearchResultBinding
import com.example.mealmate.model.MealMateTopMates
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateTopMatesAdapter internal constructor(private val listItem: List<MealMateTopMates>): RecyclerView.Adapter<MealMateTopMatesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMealmateSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: MealMateTopMates) {
            binding.apply {
                tvNumber.visibility = View.VISIBLE
                ivProfile.loadImage(item.imageItem)
                tvItemName.text = item.itemName
                tvFollower.text = String.format("%1d followers", item.itemFollow)

                val position = adapterPosition + 1
                tvNumber.text = position.toString()

                if (item.isVerified) {
                    ivVerify.visibility = View.VISIBLE
                } else {
                    ivVerify.visibility = View.GONE
                }

                val followed = item.isFollowed
                btnFollow.setOnClickListener {
                    listItem[adapterPosition].isFollowed = !item.isFollowed
                    notifyDataSetChanged()
                    notifyItemChanged(adapterPosition)
                }

                if (followed) {
                    btnFollow.text = "Unfollow"
                    btnFollow.setTextColor(itemView.resources.getColor(R.color.colorNeutral500))
                    btnFollow.background =
                        itemView.resources.getDrawable(R.drawable.bg_mealmate_btn_unfollow, null)
                } else {
                    btnFollow.text = "Follow"
                    btnFollow.setTextColor(itemView.resources.getColor(R.color.white))
                    btnFollow.background =
                        itemView.resources.getDrawable(R.drawable.bg_mealmate_btn_follow, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealMateTopMatesAdapter.ViewHolder {
        val binding = ItemMealmateSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealMateTopMatesAdapter.ViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}