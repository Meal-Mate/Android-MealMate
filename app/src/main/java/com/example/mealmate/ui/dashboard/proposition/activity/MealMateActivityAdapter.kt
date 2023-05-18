package com.example.mealmate.ui.dashboard.proposition.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.databinding.ItemActivityBinding
import com.example.mealmate.model.MealmateActivity
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateActivityAdapter internal constructor(
    private val listActivity: List<MealmateActivity>,
    private val mode: Int
) : RecyclerView.Adapter<MealMateActivityAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealmateActivity) {
            binding.apply {
                tvNftName.text = item.nftName
                tvUser.text = item.user

                ivActivity.loadImage(item.avatarImage)
                tvStatus.text = item.activity
                tvTimeHistory.text = item.timeHistory

                when(item.activity) {
                    "Cafe"-> {
                        tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorInformation400))
                        cvStatus.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.colorInformation100
                        )
                    }
                    "Casual dining" -> {
                        tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorWarning500))
                        cvStatus.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.colorWarning100
                        )
                    }
                    "Fine dining" -> {
                        tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSuccess500))
                        cvStatus.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            R.color.colorSuccess100
                        )
                    }
                }

                if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
                    cvStatus.backgroundTintList = ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.whiteTransparent
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealMateActivityAdapter.ViewHolder {
        val binding = ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealMateActivityAdapter.ViewHolder, position: Int) {
        holder.bind(listActivity[position])
    }

    override fun getItemCount(): Int {
        return listActivity.size
    }
}