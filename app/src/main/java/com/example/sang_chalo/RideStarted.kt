package com.example.sang_chalo

import  android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_ride_started.*
import org.xml.sax.Parser
import java.net.URL
import java.util.*
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

import com.google.android.gms.maps.model.Polyline
import kotlin.collections.ArrayList


class RideStarted : AppCompatActivity() {
    lateinit var mapFragment1 : SupportMapFragment
    lateinit var googleMap1: GoogleMap
    private val REQUEST_CODE_ASK_PERMISSIONS = 1
    private val currentPolyline: Polyline? = null

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
        endride.setOnClickListener {
            startActivity(Intent(this,EndRide::class.java))
        }
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
            val location1 = LatLng(26.8595,75.7664)
            googleMap1.addMarker(MarkerOptions().position(location1).title("Maiet"))
            var  currentloc:LatLng?=null
            locationManager1 = getSystemService(LOCATION_SERVICE) as LocationManager
            fusedLocationClient1 = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient1.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if(location!=null) {
                         currentloc = LatLng(location!!.latitude, location!!.longitude)
                        googleMap1.addMarker(MarkerOptions().position(currentloc!!).title("Current Location"))

                        googleMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(currentloc,10f))

                        Log.d("GoogleMap", "before URL")
                        val URL = getDirectionURL(location1,currentloc!!)
                        Log.d("GoogleMap", "URL : $URL")
                        GetDirection(URL).execute()
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


//            googleMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

            Log.d("GoogleMap", "before location2")
//            val location2 = LatLng(9.89,78.11)
//            googleMap1.addMarker(MarkerOptions().position(location2).title("Madurai"))
//
//            Log.d("GoogleMap", "before location3")
//
//            val location3 = LatLng(13.029727,77.5933021)
//            googleMap1.addMarker(MarkerOptions().position(location3).title("Bangalore"))

        })
//
    }


    fun getDirectionURL(origin:LatLng,dest:LatLng) : String{
            return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${dest.latitude},${dest.longitude}&key=AIzaSyAJXPLzaDk9SCoPBgLA0WR7oAwbnzhHEm0&sensor=false&mode=driving"
    }


    @SuppressLint("StaticFieldLeak")
    private inner class GetDirection(val url : String) : AsyncTask<Void,Void,List<List<LatLng>>>(){
        override fun doInBackground(vararg params: Void?): List<List<LatLng>> {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body()!!.string()
            Log.d("GoogleMap" , " data : $data")
            val result =  ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data,GoogleMapDTO::class.java)

                val path =  ArrayList<LatLng>()

                for (i in 0..(respObj.routes[0].legs[0].steps.size-1)){
//                    val startLatLng = LatLng(respObj.routes[0].legs[0].steps[i].start_location.lat.toDouble()
//                            ,respObj.routes[0].legs[0].steps[i].start_location.lng.toDouble())
//                    path.add(startLatLng)
//                    val endLatLng = LatLng(respObj.routes[0].legs[0].steps[i].end_location.lat.toDouble()
//                            ,respObj.routes[0].legs[0].steps[i].end_location.lng.toDouble())
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
                println("error")
            }
            return result
        }

        override fun onPostExecute(result: List<List<LatLng>>) {

            val lineoption = PolylineOptions()
            for (i in result.indices){
                lineoption.addAll(result[i])
                lineoption.width(10f)
                lineoption.color(Color.BLUE)
                lineoption.geodesic(true)
            }
            println("here on post execute")
            googleMap1.addPolyline(lineoption)

        }

    }

     fun decodePolyline(encoded: String): List<LatLng> {

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }

        return poly
    }
    fun dothis()
    {


    }
}
