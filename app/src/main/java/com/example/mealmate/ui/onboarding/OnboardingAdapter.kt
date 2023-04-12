package com.example.mealmate.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.databinding.ItemMealmateOnboardingBinding
import com.example.mealmate.model.MealMateOnboarding
class OnboardingAdapter(private val onBoardingData: List<MealMateOnboarding>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            ItemMealmateOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return onBoardingData.size
    }

    override fun onBindViewHolder(
        holder: OnboardingAdapter.OnboardingViewHolder,
        position: Int
    ) {
        holder.bind(onBoardingData[position])
    }

    inner class OnboardingViewHolder(private val binding: ItemMealmateOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoardingData: MealMateOnboarding) {
            with(binding) {
                tvTitle.text = onBoardingData.title
                tvSubtitle.text = onBoardingData.description
            }
        }
    }
}