package com.example.mealmate.ui.dashboard.createProposition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mealmate.R
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.GetRestaurantMapsResponse
import com.example.mealmate.databinding.ActivityMapsBinding
import com.example.mealmate.databinding.FragmentMealmateCreateItemBinding
import com.example.mealmate.viewmodel.PropositionViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var results: List<GetRestaurantMapsResponse> = emptyList()
    private var _binding: ActivityMapsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PropositionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityMapsBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        viewModel.getRestaurantResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    val results=it.data?.data
                }

                is BaseResponse.Error -> {
                }
                else -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val restaurants = listOf(
            mapOf("lat" to 36.86686890000001, "lng" to 10.1704947, "title" to "Chef Ayhan turkich grill rustik"),
            mapOf("lat" to 36.8900504, "lng" to 10.179964, "title" to "Che Gusto"),
            mapOf("lat" to 36.8619147, "lng" to 10.1642069, "title" to "THE SQUARE"),
            mapOf("lat" to 36.8593331, "lng" to 10.1886356, "title" to "Brins restaurant"),
            mapOf("lat" to 36.8674649, "lng" to 10.1741816, "title" to "Leo Restaurant Lounge"),
            mapOf("lat" to 36.855767, "lng" to 10.1595089, "title" to "Gringo's"),
            mapOf("lat" to 36.8593625, "lng" to 10.1885098, "title" to "Restaurant - البرنس"),
            mapOf("lat" to 36.858904, "lng" to 10.1972786, "title" to "Full Food restaurant"),
            mapOf("lat" to 36.8902464, "lng" to 10.1807117, "title" to "Restaurant JAMROCK"),
            mapOf("lat" to 36.8991701, "lng" to 10.1832854, "title" to "Restaurant COUZINA"),
            mapOf("lat" to 36.8626204, "lng" to 10.1656451, "title" to "Cooking restaurant"),
            mapOf("lat" to 36.8670379, "lng" to 10.1701713, "title" to "Piacere"),
            mapOf("lat" to 36.8991509, "lng" to 10.1840513, "title" to "Restaurant Hafood"),
            mapOf("lat" to 36.8589377, "lng" to 10.1886884, "title" to "Restaurant ZEMNI"),
            mapOf("lat" to 36.8990231, "lng" to 10.1833815, "title" to "Silver Wings"),
            mapOf("lat" to 36.8579118, "lng" to 10.1915553, "title" to "Fratelli :: Restaurant/Fast Food/Café")
        )

        // Add markers for restaurants here
        for (restaurant in restaurants) {
            val position = LatLng(restaurant["lat"] as Double, restaurant["lng"] as Double)
            val title = restaurant["title"] as String
            mMap.addMarker(MarkerOptions().position(position).title(title))
        }

        // Move camera to show all markers
        val boundsBuilder = LatLngBounds.builder()
        for (restaurant in restaurants) {
            val position = LatLng(restaurant["lat"] as Double, restaurant["lng"] as Double)
            boundsBuilder.include(position)
        }
        val bounds = boundsBuilder.build()
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

        mMap.setOnMarkerClickListener { marker ->
            val title = marker.title
            val bundle = Bundle().apply {
                putString("restaurant_title", title)
            }
            val createPropositionFragment = MealMateCreateItemFragment().apply { arguments = bundle }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.host_fragment_activity_main, createPropositionFragment)
                .addToBackStack(null)
                .commit()
            true // indicate that the listener has consumed the event
        }

    }
    fun getRestaurantMaps(){

        viewModel.getRestaurantsMap(long= "34.7937032", lat = "10.7249664", keyword = "Restaurant", radius = "5000")
    }


}

