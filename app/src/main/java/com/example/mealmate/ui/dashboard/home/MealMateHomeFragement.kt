package com.example.mealmate.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateHomeBinding
import com.example.mealmate.model.MealMateTopMates
import com.example.mealmate.model.MealMateTopRestaurants
import com.example.mealmate.model.MealMateTrendingRestaurant
import com.example.mealmate.ui.dashboard.home.trendingrestaurants.MealMateTrendingRestaurantFragment
import com.example.mealmate.ui.dashboard.profile.settings.MealMateSettingFragment
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateHomeFragement: Fragment() {
    private lateinit var binding: FragmentMealmateHomeBinding

    private val dataAuction = listOf(
        MealMateTrendingRestaurant(
            "The Cube 021",
            "https://picsum.photos/258/258",
            15.2,
            "03h : 23m : 15s"
        ),
        MealMateTrendingRestaurant(
            "Circle Puzzle",
            "https://picsum.photos/258/255",
            43.7,
            "12h : 12m : 11s"
        )
    )

    private val dataCollection = listOf(
        MealMateTopRestaurants(
            "Sarafuru",
            1,
            "https://picsum.photos/45/48",
            3.5,
            "15,341.53",
            true,
            "+ 71,9%",
            true
        ),
        MealMateTopRestaurants(
            "AnakPunk",
            2,
            "https://picsum.photos/48/47",
            2.6,
            "8,341.53",
            false,
            "- 64,1%",
            true
        ),
        MealMateTopRestaurants(
            "CoolDog",
            3,
            "https://picsum.photos/42/48",
            3.9,
            "9,131.53",
            true,
            "+ 81,2%",
            true
        ),
        MealMateTopRestaurants(
            "Wesiyowesi",
            4,
            "https://picsum.photos/52/48",
            3.2,
            "8,128.76",
            true,
            "+ 45,6%",
            true
        ),
        MealMateTopRestaurants(
            "Hootenan",
            5,
            "https://picsum.photos/54/48",
            3.1,
            "10,137.41",
            true,
            "+ 54.1%",
            true
        )
    )

    private val dataTopCreator = listOf(
        MealMateTopMates(
            "https://picsum.photos/48/48",
            "@Hello",
            105,
            true,
            isFollowed = false
        ),
        MealMateTopMates(
            "https://picsum.photos/48/48",
            "@Genope",
            412,
            false,
            isFollowed = true
        )
    )

    private val adapterAuction = MealMateTrendingRestaurantsAdapter(dataAuction)

    private val adapterTopCollection = MealMateTopRestaurantAdapter(dataCollection)

    private val adapter = MealMateTopMatesAdapter(dataTopCreator)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateHomeBinding.inflate(layoutInflater, container, false)

        binding.apply {
            ivProfile.loadImage("https://picsum.photos/41/48")

            //Trending Auctions RecyclerView
            rvTrendingAuction.setHasFixedSize(true)
            rvTrendingAuction.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            rvTrendingAuction.adapter = adapterAuction

            //Top Collections RecyclerView
            rvTopCollection.setHasFixedSize(true)
            rvTopCollection.layoutManager = LinearLayoutManager(requireActivity())
            rvTopCollection.adapter = adapterTopCollection

            //Top Creator RV
            rvTopCreator.setHasFixedSize(true)
            rvTopCreator.layoutManager = LinearLayoutManager(container?.context)
            rvTopCreator.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvSeeAllAuction.setOnClickListener {
                setCurrentFragment(MealMateTrendingRestaurantFragment())
            }

            etSearch.setOnClickListener {
             // setCurrentFragment(MealmateSearchFragment())
            }

            ivProfile.setOnClickListener {
                setCurrentFragment(MealMateSettingFragment())
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.host_fragment_activity_main, fragment)
                .addToBackStack(null)
        }
    }
}