package com.example.sang_chalo

import android.Manifest
import android.app.DownloadManager
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import kotlinx.android.synthetic.main.activity_ride_started.*
import java.util.*

class RideStarted : AppCompatActivity() {
    lateinit var mapFragment1 : SupportMapFragment
    lateinit var googleMap1: GoogleMap
    private val REQUEST_CODE_ASK_PERMISSIONS = 1
    private val RUNTIME_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE)
    var AUTOCOMPLETE_REQUEST_CODE=2
    lateinit var locationManager1: LocationManager
    private lateinit var fusedLocationClient1: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_started)

        Places.initialize(getApplicationContext(), "AIzaSyAJXPLzaDk9SCoPBgLA0WR7oAwbnzhHEm0");

        // Create a new Places client instance.
        var  placesClient = Places.createClient(this);
        mapFragment1 = supportFragmentManager.findFragmentById(R.id.mapView2) as SupportMapFragment
        mapFragment1.getMapAsync(OnMapReadyCallback {
            googleMap1 = it
            googleMap1.isMyLocationEnabled = true

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


            locationManager1 = getSystemService(LOCATION_SERVICE) as LocationManager
            fusedLocationClient1 = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient1.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if(location!=null) {
                        val currentloc = LatLng(location!!.latitude, location!!.longitude)
                        googleMap1.addMarker(MarkerOptions().position(currentloc).title("Current Location"))

                        googleMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(currentloc,12f))


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

                }


        })
//        var autocompleteFragment = AutocompleteSupportFragment()
//        getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//
//        autocompleteFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener {
//
//            override fun onPlaceSelected( place:Place) {
//                // TODO: Get info about the selected place.
//            }
//
//           override fun onError( status:Status) {
//                // TODO: Handle the error.
//            }
//        });

        endrides.setOnClickListener {
            startActivity(Intent(this,EndRide::class.java))
        }
    }
}
