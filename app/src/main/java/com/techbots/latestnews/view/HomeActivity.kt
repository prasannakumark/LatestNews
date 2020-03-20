package com.techbots.latestnews.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.techbots.latestnews.R
import com.techbots.latestnews.view.ui.main.SectionsPagerAdapter

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val searchView:SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(this)
        searchView.requestFocus(0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Toast.makeText(this,"Its still in development", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}