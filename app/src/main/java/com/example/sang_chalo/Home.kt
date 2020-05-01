package com.example.sang_chalo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {
   lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap: GoogleMap
     private lateinit var fab_open:Animation
    private lateinit var fab_close:Animation
    private lateinit var fab_clock:Animation
    private lateinit var fab_anticlock:Animation

    var isRotate=false;
    var isFABOpen=false
    private lateinit var fab1:FloatingActionButton

    private lateinit var fab2:FloatingActionButton
    private lateinit var fab3:FloatingActionButton
    private val REQUEST_CODE_ASK_PERMISSIONS = 1
    private val RUNTIME_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE)
var AUTOCOMPLETE_REQUEST_CODE=2
    lateinit var locationManager: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        requestPermissions()

//
        var fab:FloatingActionButton = this.findViewById(R.id.floatoption);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

    fab1 = this.findViewById(R.id.pastride);
    fab2 = this.findViewById(R.id.upcomingride);
    fab3 = this.findViewById(R.id.f2);


        fab.setOnClickListener {
            if(isFABOpen)
            {

                isFABOpen=false
                bookridetext.visibility=View.INVISIBLE

                paasttext.visibility=View.INVISIBLE
                upcomingtext.visibility=View.INVISIBLE
                fab1.startAnimation(fab_close)
                fab2.startAnimation(fab_close)
                fab3.startAnimation(fab_close)
                fab.startAnimation(fab_anticlock)
            }
            else
            {
                isFABOpen=true
                fab1.startAnimation(fab_open)
                fab2.startAnimation(fab_open)
                fab3.startAnimation(fab_open)
                fab.startAnimation(fab_clock)
                bookridetext.visibility=View.VISIBLE

                paasttext.visibility=View.VISIBLE
                upcomingtext.visibility=View.VISIBLE

            }
        }

        fab2.setOnClickListener {
            startActivity(Intent(this,Upcomming::class.java))

        }
        fab1.setOnClickListener {

            startActivity(Intent(this,pastrides::class.java))
        }
        fab3.setOnClickListener {
            startActivity(Intent(this,Myride::class.java))

        }
//        AnimatorView().init(floatoption);
//        AnimatorView().init(floatoption);
//        floatoption.setOnClickListener(object: View.OnClickListener {
//
//   override fun onClick( v:View) {
//
//       isRotate = AnimatorView().rotateFab(v, !isRotate);
//        if(isRotate){
//            AnimatorView().showIn(fab1);
//            AnimatorView().showIn(fab2);
//            AnimatorView().showIn(fab3);
//
//        }else{
//            AnimatorView().showOut(fab1);
//            AnimatorView().showOut(fab2);
//            AnimatorView().showOut(fab3);
//
//        }
//
//    }
//});
//        fab.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(p0: View?) {
//                if(!isFABOpen){
//                    showFABMenu();
//                }else{
//                    closeFABMenu();
//                }
//            }
//        });
        profile.setOnClickListener {

            startActivity(Intent(this,RideStarted::class.java))
        }



            Places.initialize(getApplicationContext(), "AIzaSyAJXPLzaDk9SCoPBgLA0WR7oAwbnzhHEm0");

   // Create a new Places client instance.
   var  placesClient = Places.createClient(this);

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

            try {


                locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            val currentloc = LatLng(location!!.latitude, location!!.longitude)
                            googleMap.addMarker(MarkerOptions().position(currentloc).title("Current Location"))

                            googleMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    currentloc,
                                    12f
                                )
                            )


                            var geocoder = Geocoder(this, Locale.getDefault())
                            var addresses = geocoder.getFromLocation(
                                location!!.latitude,
                                location!!.longitude,
                                1
                            )
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
            }catch (e:Exception)
            {

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

        Offerridebutton.setOnClickListener {
            startActivity(Intent(this,Choose::class.java))

        }
        Getridebutton.setOnClickListener {
            startActivity(Intent(this,GetRide::class.java))
        }

        endridenavigator.setOnClickListener {
            startActivity(Intent(this,RideStarted::class.java))
        }

       var fields = Arrays.asList(Place.Field.ID, Place.Field.NAME) as  List<Place.Field>
// Start the autocomplete intent.
            Frompastid.addTextChangedListener(object:TextWatcher{

                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    var intent =  Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields)
                        .build(this@Home);
                    startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

                }
            })

        Frompastid.setOnClickListener {

            var intent =  Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields)
                .build(this);
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

        }
        Toid.addTextChangedListener(object:TextWatcher{

            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var intent =  Autocomplete.IntentBuilder(
                    AutocompleteActivityMode.OVERLAY, fields)
                    .build(this@Home);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

            }
        })

        Toid.setOnClickListener {

            var intent =  Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields)
                .build(this);
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

        }

    }

    private fun requestPermissions() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this,
                RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS)
        } else {

        }
    }
    private fun String.permissionGranted(ctx: Context) =
        ContextCompat.checkSelfPermission(ctx, this) == PackageManager.PERMISSION_GRANTED

    private fun hasPermissions(): Boolean {
        /**
         * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
         * needs when the app is running.
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return RUNTIME_PERMISSIONS.count { !it.permissionGranted(this) } == 0
    }
override fun onActivityResult( requestCode:Int,  resultCode:Int,  data:Intent?) {
    if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
        if (resultCode == RESULT_OK) {
            var place = Autocomplete.getPlaceFromIntent(data!!);

            Toast.makeText(this,place.name.toString(),Toast.LENGTH_LONG).show()

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // TODO: Handle the error.
            var status = Autocomplete.getStatusFromIntent(data!!    );
println(status)

        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
    }
}


    fun showFABMenu(){
    isFABOpen=true;
    fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
    fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
}

fun closeFABMenu(){
    isFABOpen=false;
    fab1.animate().translationY(0.toFloat());
    fab2.animate().translationY(0.toFloat());
    fab3.animate().translationY(0.toFloat());
}
}
