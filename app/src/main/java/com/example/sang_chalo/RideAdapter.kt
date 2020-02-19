package com.example.sang_chalo


import android.content.Intent
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.riderequest.view.*


class RideAdapter(val ridelist: ArrayList<Ride>) : RecyclerView.Adapter<RideAdapter.ViewHolder>() {

    private var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(ridelist[position],mListener!!)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.riderequest, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return ridelist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(rider: Ride,listener:OnItemClickListener) {

         itemView.From.text=rider.from

            itemView.to.text=rider.to

            itemView.time.text=rider.time

        }



    }


}