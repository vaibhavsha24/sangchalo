package com.example.sang_chalo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_rides.*
import kotlinx.android.synthetic.main.activity_myride.*
import kotlinx.android.synthetic.main.myride.view.*

class Myride : AppCompatActivity() {
    private lateinit var rideAdapter:myrideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myride)




    var myride=ArrayList<Myrideclass>()
    var ride=Myrideclass()

    //ride.ridername="Vaibhav"

    ride.from="Mansarovar"
    ride.to="Jhotwara"
    ride.date="2/20/2020"

    ride.time="10:00"
    ride.status="pending"

    myride.add(ride)
    //var ride1=myrideclass()

   // ride1.ridername="Harshit"

  //  ride1.to="Mansarovar"
   // ride1.from="Jhotwara"
   // ride1.date="2/21/2020"
   //     ride1.time="1:00"

   // myride.add(ride1)


    rideAdapter= myrideAdapter(myride)
    rideAdapter.setOnItemClickListener(object:myrideAdapter.OnItemClickListener{
        override fun onItemClick(position: Int) {

        }

        override fun onitemcountchanged(position: Int, number: String, oldvalue: String) {
        }

        override fun onDeleteClick(position: Int) {
        }
    })

        myriderecycle.adapter=rideAdapter

        myriderecycle.layoutManager= LinearLayoutManager(this)




}
}