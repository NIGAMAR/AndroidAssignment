package com.nigamar.androidassignment.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nigamar.androidassignment.ui.current.CurrentWeatherFragment
import com.nigamar.androidassignment.ui.future.FutureWeatherFragment

class FragmentAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        lateinit var fragment:Fragment
        when(position){
            0 -> {
                fragment = CurrentWeatherFragment()
            }
            1->{
                fragment = FutureWeatherFragment()
            }
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }

    override fun getCount() = 2
}