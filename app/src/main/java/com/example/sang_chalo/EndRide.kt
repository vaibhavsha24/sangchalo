package com.example.sang_chalo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shitij.goyal.slidebutton.SwipeButton
import kotlinx.android.synthetic.main.activity_end_ride.*
import kotlinx.android.synthetic.main.activity_myride.*
import kotlinx.android.synthetic.main.endrides.*

class EndRide : AppCompatActivity() {

   // SwipeButton  swipebutton;
     //SwipeButton swipetoend
    private lateinit var rideAdapter:endrideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_ride)




        var EndRide=ArrayList<endrideclass>()
        var ride=endrideclass()

        ride.ridername1="Vaibhav sharma"

        ride.from="Mansarovar"
        ride.to="Jhotwara"
       // ride.date="2/20/2020"

       // ride.time="10:00"
        ride.totalkm="9"
        ride.rate="3"
        ride.totalfare="27"

        EndRide.add(ride)
        //var ride1=myrideclass()

        // ride1.ridername="Harshit"

        //  ride1.to="Mansarovar"
        // ride1.from="Jhotwara"
        // ride1.date="2/21/2020"
        //     ride1.time="1:00"

        // myride.add(ride1)


        rideAdapter= endrideAdapter(EndRide)
        rideAdapter.setOnItemClickListener(object:endrideAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

            }

            override fun onitemcountchanged(position: Int, number: String, oldvalue: String) {
            }

            override fun onDeleteClick(position: Int) {
            }
        })

        endriderecycler.adapter=rideAdapter

        endriderecycler.layoutManager= LinearLayoutManager(this)


    }
}
