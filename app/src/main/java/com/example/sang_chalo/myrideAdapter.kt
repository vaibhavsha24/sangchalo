package com.example.sang_chalo

import android.content.Intent
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.myride.view.*

class myrideAdapter(val myride: ArrayList<Myrideclass>) : RecyclerView.Adapter<myrideAdapter.ViewHolder>() {


    private var mListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(myride[position],mListener!!)

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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.myride, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return myride.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(rider: Myrideclass,listener:OnItemClickListener) {

            itemView.Fromride.text=rider.from

            itemView.tomride.text=rider.to

            itemView.datempastid.text=rider.date

            itemView.timemride.text=rider.time
             itemView.Statuscheck.text=rider.status
            itemView.startridebitton.setOnClickListener {
                savend("true")
                itemView.context.startActivity(Intent(itemView.context,Home::class.java))

            }

        }

        fun savend(list: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(itemView.context)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString("Ride", json)
            editor.apply()     // This line is IMPORTANT !!!
            println("cartlist stored succesfully")
        }

    }

}