package com.example.locationlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.locationlist.db.LocationEntity
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(val listener:RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<LocationEntity>()
    fun setListData(data:ArrayList<LocationEntity>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater =  LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater,listener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view:View,val listener:RowClickListener): RecyclerView.ViewHolder(view){

        val tvLocation = view.tvLocation
        val tvLatitude = view.tvLatitude
        val tvLongitude = view.tvLongitude
        val deleteButton = view.deleteButton
        fun bind(data: LocationEntity){
            tvLocation.text = data.location_name
            tvLatitude.text = data.latitude
            tvLongitude.text = data.longitude

            deleteButton.setOnClickListener{
                listener.onDeleteLocationClickListener(data)
            }

        }
    }
    interface RowClickListener{
        fun onDeleteLocationClickListener(location:LocationEntity)
        fun onItemClickListener(location: LocationEntity)
    }

}