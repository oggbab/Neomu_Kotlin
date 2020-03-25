package com.app.neomu_kotlin.app.map.fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.map.constance.ConstMap.Companion.MAP_DEFAUT_SEARCH_SIZE
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import neomu.kotlin.common.fragment.BaseFragment
import java.io.IOException

class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var mGeocoder: Geocoder
    private lateinit var mMap: GoogleMap

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
        if (map != null) {
            mMap = map
        }
        mGeocoder = Geocoder(activity)
        addMarker(map)
    }

    private fun addMarker(map: GoogleMap?) {
        var seoul = LatLng(37.56, 126.97)
        map?.addMarker(MarkerOptions().position(seoul).title("seoul"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_map_search -> {
                setMarker(getAddressList())
            }
        }
    }

    private fun getSearchKeyword(): String? {
        return et_map_search.text.toString()
    }

    private fun getAddressList() : Array<String> {
        var addrList = mutableListOf<Address>()

        try {
            addrList = mGeocoder.getFromLocationName(getSearchKeyword(),MAP_DEFAUT_SEARCH_SIZE)
        } catch (err : IOException) {
            Log.e("soon2", err.toString())
        }

        var splitChar : Array<String> = addrList.get(0).toString().split(",").toTypedArray()

        return splitChar
    }

    private fun setMarker(splitStr: Array<String>) {
        val point = getMarkerPoint(splitStr)
        val markerOption: MarkerOptions = getMarkerOption(point)
        mMap.addMarker(markerOption)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15f))
    }

    private fun getMarkerPoint(splitStr: Array<String>): LatLng {
        val address: String = splitStr.get(0)
            .substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length - 2) // 주소

        val latitude: String =
            splitStr[10].substring(splitStr[10].indexOf("=") + 1) // 위도

        val longitude: String =
            splitStr[12].substring(splitStr[12].indexOf("=") + 1) // 경도

        val point = LatLng(latitude.toDouble(), longitude.toDouble())

        return point
    }

    private fun getMarkerOption(point: LatLng): MarkerOptions {
        val markerOptions = MarkerOptions()
//        mMarkerOptions.snippet(address)
        markerOptions.title("검색결과")
        markerOptions.position(point)

        return markerOptions
    }


}