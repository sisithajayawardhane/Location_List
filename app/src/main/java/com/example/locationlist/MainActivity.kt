package com.example.locationlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.locationlist.db.LocationEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
             recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
             adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllLocationsObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })
        SaveButton.setOnClickListener{
            val locationName = LocationInput.text.toString()
            val latitude = LatitudeInput.text.toString()
            val longitude = LongitudeInput.text.toString()

            if(SaveButton.text.equals("Save")) {
                val location = LocationEntity(0, locationName, latitude, longitude)
                viewModel.insertLocationInfo(location)
            }else{
                val location = LocationEntity(LocationInput.getTag(LocationInput.id).toString().toInt(),locationName,latitude,longitude)
                viewModel.updateLocationInfo(location)
                SaveButton.setText("Save")
            }
            LocationInput.setText("")
            LatitudeInput.setText("")
        }
    }

    override fun onDeleteLocationClickListener(location: LocationEntity) {
        viewModel.deleteLocationInfo(location)
    }

    override fun onItemClickListener(location: LocationEntity) {
        LocationInput.setText(location.location_name)
        LatitudeInput.setText((location.latitude))
        LocationInput.setTag(LocationInput.id,location.id)

        SaveButton.setText("Update")
    }
}