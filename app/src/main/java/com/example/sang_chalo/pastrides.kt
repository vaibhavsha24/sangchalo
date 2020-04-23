package com.example.sang_chalo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_myride.*
import kotlinx.android.synthetic.main.activity_pastrides.*

class pastrides : AppCompatActivity() {
    private lateinit var rideAdapter:pastridesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pastrides)

        var pastrides=ArrayList<pastridesclass>()
        var ride=pastridesclass()

        ride.name="Vaibhav"
        ride.from="Mansarovar"
        ride.to="Jhotwara"
        ride.date="2/20/2020"
        ride.time="10:00"
        ride.Amount="50rs"
        ride.status="paid"

        pastrides.add(ride)
        //var ride1=myrideclass()

        // ride1.ridername="Harshit"

        //  ride1.to="Mansarovar"
        // ride1.from="Jhotwara"
        // ride1.date="2/21/2020"
        //     ride1.time="1:00"

        // myride.add(ride1)


        rideAdapter= pastridesAdapter(pastrides)
        rideAdapter.setOnItemClickListener(object:pastridesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

            }

            override fun onitemcountchanged(position: Int, number: String, oldvalue: String) {
            }

            override fun onDeleteClick(position: Int) {
            }
        })

        pastridesrecycle.adapter=rideAdapter

        pastridesrecycle.layoutManager= LinearLayoutManager(this)




    }
}

