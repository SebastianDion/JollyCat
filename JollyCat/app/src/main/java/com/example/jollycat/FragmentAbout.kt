package com.example.jollycat

import android.Manifest
import android.content.pm.PackageManager
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jollycat.databinding.FragmentAboutBinding
import com.example.jollycat.databinding.FragmentCartBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FragmentAbout : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        val txtPlace = binding.txtPlace
//        val btnBack = binding.btnBack

        txtPlace.text = "JollyCat’s Store"

//        // Handle back button click
//        btnBack.setOnClickListener {
//            // Replace this with actual navigation logic to navigate to another fragment or activity
//            startActivity(Intent(requireContext(), MainActivity::class.java))
//            requireActivity().finish() // Finish current activity (optional)
//        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val position = LatLng(-6.20175, 106.78208)
        val option = MarkerOptions().position(position).title("JollyCat’s Store")
        mMap.addMarker(option)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
    }
}
