package com.example.sang_chalo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_rides.*
import kotlinx.android.synthetic.main.riderequest.*

class AllRides : AppCompatActivity() {
private lateinit var rideAdapter:RideAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_rides)

        var ridelist=ArrayList<Ride>()
        var ride=Ride()

        ride.ridername="Vaibhav"

        ride.from="Mansarovar"
        ride.to="Jhotwara"

        ride.time="10:00"

        ridelist.add(ride)
        var ride1=Ride()

        ride1.ridername="Harshit"

        ride1.to="Mansarovar"
        ride1.from="Jhotwara"

        ride1.time="1:00"

        ridelist.add(ride1)

       // bookride.setOnClickListener {
       //     startActivity(Intent(this,RideStarted::class.java))
       // }


        rideAdapter= RideAdapter(ridelist)
        rideAdapter.setOnItemClickListener(object:RideAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

            }

            override fun onitemcountchanged(position: Int, number: String, oldvalue: String) {
            }

            override fun onDeleteClick(position: Int) {
            }
        })

        ridesrecycle.adapter=rideAdapter

        ridesrecycle.layoutManager=LinearLayoutManager(this)







    }
}
