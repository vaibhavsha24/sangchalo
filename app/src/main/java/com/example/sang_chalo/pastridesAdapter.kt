package com.example.sang_chalo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pastride.view.*
import kotlinx.android.synthetic.main.pastride.view.datempastid

class pastridesAdapter(val pastrides: ArrayList<pastridesclass>) : RecyclerView.Adapter<pastridesAdapter.ViewHolder>() {


    private var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(pastrides[position],mListener!!)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pastride, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return pastrides.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(ride: pastridesclass,listener:OnItemClickListener) {

            itemView.Frompastid.text=ride.from

            itemView.topastid.text=ride.to

            itemView.datempastid.text=ride.date

            itemView.timepastid.text=ride.time

            itemView.Amountpastid.text=ride.Amount
            itemView.Statuspastid.text=ride.status
            itemView.namepastid.text=ride.name

        }



    }

}