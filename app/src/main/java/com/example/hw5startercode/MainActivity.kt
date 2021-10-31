package com.example.hw5startercode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_FRAGMENTS = 7

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this)
        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = position.toString()
        }.attach()
    }

    private inner class ViewPagerAdapter(activity : MainActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return NUM_FRAGMENTS
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> HomeFragment.newInstance()
                else -> WallpaperFragment.newInstance(position)
            }
        }

    }
}