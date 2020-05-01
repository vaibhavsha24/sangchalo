package com.example.sang_chalo

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.endrides.*
import kotlinx.android.synthetic.main.endrides.view.*
import kotlinx.android.synthetic.main.endrides.view.swipeend
import kotlinx.android.synthetic.main.riderequest.view.*

class endrideAdapter(val EndRide: ArrayList<endrideclass>) : RecyclerView.Adapter<endrideAdapter.ViewHolder>() {

        private var mListener: OnItemClickListener? = null

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(EndRide[position],mListener!!)

        }
        fun setOnItemClickListener(listener: OnItemClickListener) {
            mListener = listener
        }

        interface OnItemClickListener {
            fun onItemClick(position: Int)

            fun onitemcountchanged(position: Int,number:String,oldvalue:String)

            fun onDeleteClick(position: Int)
        }

        //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.endrides, parent, false)
            return ViewHolder(v)
        }

        //this method is binding the data on the list

        //this method is giving the size of the list
        override fun getItemCount(): Int {
            return EndRide.size
        }

        //the class is hodling the list view
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(rider: endrideclass,listener:OnItemClickListener) {

                itemView.fromlocation.text=rider.from

                itemView.tolocation.text=rider.to

                itemView.kmid.text=rider.totalkm
                itemView.rateperkm.text=rider.rate
                itemView.totalfare.text=rider.totalfare
                itemView.swipeend.setOnStateChangeListener {
                    itemView.context.startActivity(Intent(itemView.context,Home::class.java))
                }


                // itemView.time.text=rider.time

            }



        }

    }