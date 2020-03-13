package com.app.neomu_kotlin.app.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.neomu_kotlin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import neomu.kotlin.common.fragment.BaseFragment

class MapFragment : BaseFragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setGoogleMap()
    }

    private fun setGoogleMap() {
        var manager = (activity?.supportFragmentManager?.findFragmentById(R.id.fr_google_map)) as SupportMapFragment
        manager?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        addMarker(map)
    }

    private fun addMarker(map: GoogleMap?) {
        var seoul = LatLng(37.56, 126.97)
        map?.addMarker(MarkerOptions().position(seoul).title("seoul"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }

}