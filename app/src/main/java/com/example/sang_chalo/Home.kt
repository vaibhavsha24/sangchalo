package com.example.sang_chalo

import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {
   lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap: GoogleMap

    lateinit var locationManager: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        profile.setOnClickListener {

            startActivity(Intent(this,Details::class.java))
        }
        mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            googleMap.isMyLocationEnabled = true

//            val location1 = LatLng(13.03,77.60)
//            googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))
//
//            val location2 = LatLng(9.89,78.11)
//            googleMap.addMarker(MarkerOptions().position(location2).title("Madurai"))
//
//
//            val location3 = LatLng(13.00,77.00)
//            googleMap.addMarker(MarkerOptions().position(location3).title("Bangalore"))


            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    val currentloc = LatLng(location!!.latitude,location!!.longitude)

                    googleMap.addMarker(MarkerOptions().position(currentloc).title("Current Location"))

                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentloc,12f))


                    var geocoder = Geocoder(this, Locale.getDefault())
                    var addresses = geocoder.getFromLocation(location!!.latitude, location!!.longitude, 1)
                    var address = ""
                    if (addresses.get(0).getAddressLine(0) != null) {
                        address = addresses.get(0)
                            .getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    }

                    var city = addresses.get(0).getLocality();
                    var state = addresses.get(0).getAdminArea();
                    var country = addresses.get(0).getCountryName();
                    var postalCode = addresses.get(0).getPostalCode();
                    var knownName = addresses.get(0).getFeatureName()

                }


        })
        Offerridebutton.setOnClickListener {
            startActivity(Intent(this,Choose::class.java))

        }


    }

}
