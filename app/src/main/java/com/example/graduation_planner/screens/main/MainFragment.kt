package com.example.graduation_planner.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.graduation_planner.R
import com.example.graduation_planner.screens.module_list.ModuleListFragment
import com.example.graduation_planner.screens.requirements.RequirementsFragment
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {
    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainPagerAdapter = MainPagerAdapter(childFragmentManager)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = mainPagerAdapter
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

}

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var fragmentList = arrayListOf(ModuleListFragment(), RequirementsFragment())
    var fragmentTitles = arrayListOf("Saved Modules", "Requirements")

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitles[position]
    }
}
