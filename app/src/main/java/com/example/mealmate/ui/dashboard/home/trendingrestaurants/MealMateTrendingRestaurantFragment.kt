package com.example.mealmate.ui.dashboard.home.trendingrestaurants

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateTrendingRestaurantBinding
import com.example.mealmate.model.MealMateTrendingRestaurant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateTrendingRestaurantFragment : Fragment() {

    private lateinit var binding: FragmentMealmateTrendingRestaurantBinding
    private val timeout: Long = 1000

    private val dataAuctions = listOf(
        MealMateTrendingRestaurant(
            "The Cube 021",
            "https://picsum.photos/248/258",
            15.2,
            "03h : 23m : 15s"
        ),
        MealMateTrendingRestaurant(
            "Circle Puzzle",
            "https://picsum.photos/258/358",
            13.6,
            "03h : 41m : 29s"
        ),
        MealMateTrendingRestaurant(
            "Pink Ice Cream",
            "https://picsum.photos/254/258",
            12.9,
            "04h : 51m : 22s"
        )
    )

    private val adapter = MealMateTrendingRestaurantsAllAdapter(dataAuctions)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealmateTrendingRestaurantBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        binding.apply {
            rvTrendingAuction.setHasFixedSize(true)
            rvTrendingAuction.layoutManager = LinearLayoutManager(binding.root.context)
            rvTrendingAuction.adapter = adapter
        }
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE

        binding.apply {
            //Bottom Sheet when btn filter click

            val sharedPreferences = requireActivity().getSharedPreferences("MealMate", Context.MODE_PRIVATE)
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

           btnFilter.setOnClickListener {
               val bottomSheet = BottomSheetDialog(requireContext(), R.style.MealMateBottomSheet)
               /*
              bottomSheet.setContentView(R.layout.bottom_sheet_mealmate_filter)
               bottomSheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED
               val applyBtn = bottomSheet.findViewById<Button>(R.id.btn_apply)
               val closeBtn = bottomSheet.findViewById<ImageButton>(R.id.btn_close)

               //Handling status item
               val item1 = bottomSheet.findViewById<LinearLayout>(R.id.item_filter_buy_now)
               val item2 = bottomSheet.findViewById<LinearLayout>(R.id.item_filter_on_auction)
               val item3 = bottomSheet.findViewById<LinearLayout>(R.id.item_filter_new)
               val item4 = bottomSheet.findViewById<LinearLayout>(R.id.item_filter_has_offers)

               val tvItem1 = bottomSheet.findViewById<TextView>(R.id.tv_filter_buy_now)
               val tvItem2 = bottomSheet.findViewById<TextView>(R.id.tv_filter_on_auction)
               val tvItem3 = bottomSheet.findViewById<TextView>(R.id.tv_filter_new)
               val tvItem4 = bottomSheet.findViewById<TextView>(R.id.tv_filter_has_offers)

               item1?.setOnClickListener {
                   item1.background = resources.getDrawable(R.drawable.bg_nft_outline_primary, null)
                   item2!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item3!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item4!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   tvItem1?.setTextColor(colorSelected)
                   tvItem2?.setTextColor(colorUnselected)
                   tvItem3?.setTextColor(colorUnselected)
                   tvItem4?.setTextColor(colorUnselected)
               }

               item2?.setOnClickListener {
                   item1!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item2.background = resources.getDrawable(R.drawable.bg_nft_outline_primary, null)
                   item3!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item4!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   tvItem1?.setTextColor(colorUnselected)
                   tvItem2?.setTextColor(colorSelected)
                   tvItem3?.setTextColor(colorUnselected)
                   tvItem4?.setTextColor(colorUnselected)
               }

               item3?.setOnClickListener {
                   item1!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item2!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item3.background = resources.getDrawable(R.drawable.bg_nft_outline_primary, null)
                   item4!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   tvItem1?.setTextColor(colorUnselected)
                   tvItem2?.setTextColor(colorUnselected)
                   tvItem3?.setTextColor(colorSelected)
                   tvItem4?.setTextColor(colorUnselected)
               }

               item4?.setOnClickListener {
                   item1!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item2!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item3!!.background = resources.getDrawable(R.drawable.bg_nft_outline_secondary, null)
                   item4.background = resources.getDrawable(R.drawable.bg_nft_outline_primary, null)
                   tvItem1?.setTextColor(colorUnselected)
                   tvItem2?.setTextColor(colorUnselected)
                   tvItem3?.setTextColor(colorUnselected)
                   tvItem4?.setTextColor(colorSelected)
               }

               applyBtn?.setOnClickListener {
                   bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
               }

               closeBtn?.setOnClickListener {
                   bottomSheet.behavior.state = BottomSheetBehavior.STATE_HIDDEN
               }
               bottomSheet.show()

                */
           }

          //handling btn back
          btnBack.setOnClickListener {
              requireActivity().supportFragmentManager.popBackStack()

              btmNavbar.visibility = View.VISIBLE
              fab.visibility = View.VISIBLE
          }

          //loading and recyclerview
          rvTrendingAuction.visibility = View.GONE
          progressBar.visibility = View.VISIBLE

          Handler(Looper.getMainLooper()).postDelayed({
              rvTrendingAuction.visibility = View.VISIBLE
              progressBar.visibility = View.GONE
          }, timeout)
      }

  }

}