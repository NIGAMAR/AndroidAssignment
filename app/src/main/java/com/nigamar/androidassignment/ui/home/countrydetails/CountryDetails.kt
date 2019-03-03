package com.nigamar.androidassignment.ui.home.countrydetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.nigamar.androidassignment.R
import com.nigamar.androidassignment.data.models.Country
import kotlinx.android.synthetic.main.fragment_country_details.*
import kotlinx.android.synthetic.main.nav_item.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CountryDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }


    fun updateUi(country:Country){
        val url= "http://www.geognos.com/api/en/countries/flag/${country.alpha2Code}.png"
        Glide.with(this).load(url).into(countryFlag)
        name.text = country.name
        region.text=country.region
        capital.text=country.capital
        population.text= " ${country.population} "
    }
}
