package com.nigamar.androidassignment.ui

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigamar.androidassignment.R
import com.nigamar.androidassignment.data.models.Country
import com.nigamar.androidassignment.data.network.CountriesApi
import com.nigamar.androidassignment.ui.home.adapter.CountriesAdapter
import com.nigamar.androidassignment.ui.home.countrydetails.CountryDetails
import com.nigamar.androidassignment.ui.home.countryweather.CountryWeather
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),CountriesAdapter.OnCountrySelected{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // to test countries my service is working or not
        val countriesApi= CountriesApi.getInstance()
        val countryDetailsFragment=CountryDetails()
        val countryDetailsWeatherFragment=CountryWeather()
        addCountryDetailsFragment(countryDetailsFragment)
        addCountryWeatherDetailsFragment(countryDetailsWeatherFragment)
        val layoutManager = LinearLayoutManager(this)
        country_rv.layoutManager = layoutManager
        populateNavigationDrawer(countriesApi)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun addCountryWeatherDetailsFragment(countryDetailsWeatherFragment: CountryWeather) {
        supportFragmentManager.beginTransaction().add(R.id.weatherDetails,countryDetailsWeatherFragment,"WEATHER").commit()
    }

    private fun addCountryDetailsFragment(countryDetailsFragment: CountryDetails) {
        supportFragmentManager.beginTransaction().add(R.id.countryDetails,countryDetailsFragment,"DETAILS").commit()
    }

    private fun populateNavigationDrawer(countriesApi: CountriesApi) {
        GlobalScope.launch(Dispatchers.Main) {
            // the code is working fine getting the list of countries in the log cat
            val countryList = countriesApi.getAllCountries().await()
            val adapter= CountriesAdapter(countryList,this@MainActivity)
            country_rv.adapter=adapter
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCountrySelected(country: Country) {
        drawer_layout.closeDrawer(GravityCompat.START)
        // pass the message on to the first fragment to load the data
        val countryDetailsFragment = supportFragmentManager.findFragmentByTag("DETAILS") as CountryDetails
        countryDetailsFragment.updateUi(country)
        // pass the message to the second fragment to load the weather
        val countryDetailsWeatherFragment = supportFragmentManager.findFragmentByTag("WEATHER") as CountryWeather
        countryDetailsWeatherFragment.updateUi(country)
    }
}
