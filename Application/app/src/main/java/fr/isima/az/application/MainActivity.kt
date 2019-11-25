package fr.isima.az.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var toolbar: Toolbar? = null
    private var drawerLayout : DrawerLayout? = null
    private var navigationView : NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*configureToolbar()
        configureDrawerLayout()
        configureNavigationView()*/
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        /*
        val id = item.getItemId()

        when(id) {
            R.id.activity_new_travel ->
            R.id.activity_stats ->
            R.id.settings_activity ->
        }
        */
        this.drawerLayout?.closeDrawer(GravityCompat.START)

        return true
    }

    private fun configureToolbar() {
        toolbar = findViewById(R.id.activity_main_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun configureDrawerLayout() {
        drawerLayout = findViewById(R.id.activity_main_drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun configureNavigationView() {
        navigationView = findViewById(R.id.activity_main_nav_view)
        navigationView?.setNavigationItemSelectedListener(this)
    }
}