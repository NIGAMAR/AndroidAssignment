package com.nigamar.androidassignment.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nigamar.androidassignment.R
import com.nigamar.androidassignment.data.models.Country
import kotlinx.android.synthetic.main.nav_item.view.*

class CountriesAdapter(val countries:List<Country>, val listener: OnCountrySelected?) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    interface OnCountrySelected {
        fun onCountrySelected(country: Country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.nav_item,parent,false)
        return CountriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bindUi(countries[position])
        holder.itemView.setOnClickListener {
            listener?.onCountrySelected(countries[position])
        }
    }

    inner class CountriesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindUi(country: Country){
            itemView.countryName.text = country.name
            itemView.region.text=country.region
            val url= "http://www.geognos.com/api/en/countries/flag/${country.alpha2Code}.png"
            println(url)
            Glide.with(itemView).load(url).into(itemView.flagImage)
        }
    }
}