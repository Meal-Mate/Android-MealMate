package com.example.mealmate.ui.dashboard.proposition

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.BottomSheetMealmateFilterActivityBinding
import com.example.mealmate.databinding.BottomSheetMealmateFilterStatisticBinding
import com.example.mealmate.databinding.FragmentMealmatePropositionBinding
import com.example.mealmate.ui.dashboard.proposition.activity.MealMateActivityFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MealMatePropositionFragment : Fragment() {
    private lateinit var binding: FragmentMealmatePropositionBinding

    private var isRankings = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmatePropositionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragment_container, MealMateActivityFragment())
        }

        binding.apply {

            btnFilter.setOnClickListener {
                showBottomSheet()
            }

            //spinner
            linearSpinner.setOnClickListener {
                // Initializing the popup menu and giving the reference as current context
                val popupMenu = PopupMenu(requireContext(), linearSpinner)
                // Inflating popup menu from popup_menu.xml file
                popupMenu.menuInflater.inflate(R.menu.menu_popup_statistic, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item?.itemId) {
                        R.id.menu_rankings -> {
                            tvTitle.text = "Mates Proposition"
                            isRankings = true
                            ivSearch.visibility = View.VISIBLE
                            requireActivity().supportFragmentManager.commit {
                                replace(R.id.fragment_container, MealMateActivityFragment())
                            }

                        }
                        R.id.menu_activity -> {
                            tvTitle.text = "My Propositions"
                            isRankings = false
                            ivSearch.visibility = View.GONE
                            requireActivity().supportFragmentManager.commit {
                                replace(R.id.fragment_container, MealMateActivityFragment())
                            }

                        }
                    }
                    true
                }

                // Showing the popup menu
                popupMenu.show()
            }
        }

    }

    private fun showBottomSheet() {
        if (isRankings) {
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.MealMateBottomSheet)
            val bottomSheetFilterRankings = BottomSheetMealmateFilterActivityBinding.inflate(layoutInflater)
            bottomSheet.setContentView(bottomSheetFilterRankings.root)

            bottomSheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED

            bottomSheetFilterRankings.btnApply.setOnClickListener {
                bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
            }

            bottomSheetFilterRankings.btnClose.setOnClickListener {
                bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
            }

            bottomSheet.show()

        } else {
            val sharedPreferences = requireActivity().getSharedPreferences("AKSTY", Context.MODE_PRIVATE)
            val mode = sharedPreferences.getInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_NO)

            val colorSelected = if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
                resources.getColor(R.color.colorBrandMealMate500)
            } else {
                resources.getColor(R.color.colorNeutral500)
            }

            val colorUnselected = if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
                resources.getColor(R.color.colorNeutral300)
            } else {
                resources.getColor(R.color.colorNeutral200)
            }

            //bottom sheet
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.MealMateBottomSheet)
            val bottomSheetFilterRankings = BottomSheetMealmateFilterActivityBinding.inflate(layoutInflater)

            bottomSheet.setContentView(bottomSheetFilterRankings.root)

            bottomSheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED

            bottomSheetFilterRankings.apply {
                itemFilterAll.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    tvFilterAll.setTextColor(colorSelected)
                    tvFilterSales.setTextColor(colorUnselected)
                    tvFilterListing.setTextColor(colorUnselected)
                    tvFilterOffer.setTextColor(colorUnselected)
                    tvFilterCallOffer.setTextColor(colorUnselected)
                    tvFilterTransfer.setTextColor(colorUnselected)
                }

                itemFilterSales.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    tvFilterAll.setTextColor(colorUnselected)
                    tvFilterSales.setTextColor(colorSelected)
                    tvFilterListing.setTextColor(colorUnselected)
                    tvFilterOffer.setTextColor(colorUnselected)
                    tvFilterCallOffer.setTextColor(colorUnselected)
                    tvFilterTransfer.setTextColor(colorUnselected)
                }

                itemFilterListing.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    tvFilterAll.setTextColor(colorUnselected)
                    tvFilterSales.setTextColor(colorUnselected)
                    tvFilterListing.setTextColor(colorSelected)
                    tvFilterOffer.setTextColor(colorUnselected)
                    tvFilterCallOffer.setTextColor(colorUnselected)
                    tvFilterTransfer.setTextColor(colorUnselected)
                }

                itemFilterOffer.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    tvFilterAll.setTextColor(colorUnselected)
                    tvFilterSales.setTextColor(colorUnselected)
                    tvFilterListing.setTextColor(colorUnselected)
                    tvFilterOffer.setTextColor(colorSelected)
                    tvFilterCallOffer.setTextColor(colorUnselected)
                    tvFilterTransfer.setTextColor(colorUnselected)
                }

                itemFilterCallOffer.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    tvFilterAll.setTextColor(colorUnselected)
                    tvFilterSales.setTextColor(colorUnselected)
                    tvFilterListing.setTextColor(colorUnselected)
                    tvFilterOffer.setTextColor(colorUnselected)
                    tvFilterCallOffer.setTextColor(colorSelected)
                    tvFilterTransfer.setTextColor(colorUnselected)
                }

                itemFilterTransfer.setOnClickListener {
                    itemFilterAll.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterSales.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterListing.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterCallOffer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_secondary, null)
                    itemFilterTransfer.background = resources.getDrawable(R.drawable.bg_mealmate_outline_primary, null)
                    tvFilterAll.setTextColor(colorUnselected)
                    tvFilterSales.setTextColor(colorUnselected)
                    tvFilterListing.setTextColor(colorUnselected)
                    tvFilterOffer.setTextColor(colorUnselected)
                    tvFilterCallOffer.setTextColor(colorUnselected)
                    tvFilterTransfer.setTextColor(colorSelected)
                }

                btnApply.setOnClickListener {
                    bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
                }

                btnClose.setOnClickListener {
                    bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
                }

            }

            bottomSheet.show()
        }

    }
}