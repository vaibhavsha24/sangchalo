package com.example.sang_chalo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.upcomming.view.*

class upcommingAdapter(val upcomming: ArrayList<upcommingclass>) : RecyclerView.Adapter<upcommingAdapter.ViewHolder>() {


    private var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(upcomming[position],mListener!!)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.upcomming, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return upcomming.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(ride: upcommingclass,listener:OnItemClickListener) {

            itemView.fromupcoming.text=ride.from

            itemView.toupcoming.text=ride.to

            itemView.dateupcoming.text=ride.date

            itemView.timeupcoming.text=ride.time

            itemView.amountupcoming.text=ride.estimatedamount
            itemView.statusupcoming.text=ride.status
            itemView.nameupcoming.text=ride.name

        }



    }

}