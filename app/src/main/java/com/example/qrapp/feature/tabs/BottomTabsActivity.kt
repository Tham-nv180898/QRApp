package com.example.qrapp.feature.tabs

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qrapp.BuildConfig
import com.example.qrapp.R
import com.example.qrapp.feature.tabs.create.CreateBarcodeFragment
import com.example.qrapp.feature.tabs.history.BarcodeHistoryFragment
import com.example.qrapp.feature.tabs.scan.ScanBarcodeFromCameraFragment
import com.example.qrapp.feature.tabs.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomTabsActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var bottomNavigationView: BottomNavigationView

    companion object {
        private const val ACTION_CREATE_BARCODE = "${BuildConfig.APPLICATION_ID}.CREATE_BARCODE"
        private const val ACTION_HISTORY = "${BuildConfig.APPLICATION_ID}.HISTORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tabs)
        initView()
        supportEdgeToEdge()
        initBottomNavigationView()

        if (savedInstanceState == null) {
            showInitialFragment()
        }
    }

    private fun initBottomNavigationView() {
        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener(this@BottomTabsActivity)
        }
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }

    private fun supportEdgeToEdge() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == bottomNavigationView.selectedItemId) {
            return false
        }
        showFragment(item.itemId)
        return true
    }

    private fun showFragment(itemId: Int) {
        val fragment: Fragment? = when (itemId) {
            R.id.item_scan -> ScanBarcodeFromCameraFragment()
            R.id.item_create -> CreateBarcodeFragment()
            R.id.item_history -> BarcodeHistoryFragment()
            R.id.item_settings -> SettingsFragment()
            else -> null
        }
        fragment?.apply(::replaceFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }

    private fun showInitialFragment() {
        when (intent?.action) {
            ACTION_CREATE_BARCODE -> bottomNavigationView.selectedItemId = R.id.item_create
            ACTION_HISTORY -> bottomNavigationView.selectedItemId = R.id.item_history
            else -> showFragment(R.id.item_scan)
        }
    }

    override fun onBackPressed() {
        if (bottomNavigationView.selectedItemId == R.id.item_scan) {
            super.onBackPressed()
        } else {
            bottomNavigationView.selectedItemId = R.id.item_scan
        }
    }
}