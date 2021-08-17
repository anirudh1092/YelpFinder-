package com.example.yelpfinder.ui.businesseScreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yelpfinder.R
import com.example.yelpfinder.databinding.CellBusinessResturantsBinding
import com.example.yelpfinder.models.dataModels.BusinessesModel

class BusinessesAdapter(val context: Context, private val businessesModel: BusinessesModel) :
    RecyclerView.Adapter<BusinessesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cell_business_resturants, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBusiness = businessesModel.businesses[position]
        holder.devicePickerTextView.text = currentBusiness.name
        Glide.with(holder.imageView).asBitmap().load(currentBusiness.imageUrl)
            .into(holder.imageView)
        holder.ratings.rating = currentBusiness.ratings.toFloat()
        holder.phone.text = "Phone : " + currentBusiness.phone
        holder.distance.text = currentBusiness.distance.toFloat().toString()
        holder.address.text =
            "Address: " + currentBusiness.location.address1 + currentBusiness.location.address2 + currentBusiness.location.city
    }

    override fun getItemCount(): Int {
        return businessesModel.businesses.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var devicePickerTextView: TextView
        var imageView: ImageView
        var ratings: RatingBar
        var phone: TextView
        var distance: TextView
        var address: TextView

        init {
            val binding = CellBusinessResturantsBinding.bind(itemView)
            devicePickerTextView = binding.cellTitle
            imageView = binding.cellImageView
            ratings = binding.cellRatings
            phone = binding.cellPhone
            distance = binding.cellDistance
            address = binding.cellAddress
        }
    }
}
