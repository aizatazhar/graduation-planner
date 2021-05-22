package com.example.graduation_planner.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.graduation_planner.R
import com.example.graduation_planner.screens.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var mainFragmentAdapter: MainFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainFragmentAdapter = MainFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = mainFragmentAdapter

        val tabTitles = arrayListOf("Modules", "Requirements")
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = (tabTitles[position])
        }.attach()
    }
}

class MainFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList = arrayListOf(HomeFragment(), HomeFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
