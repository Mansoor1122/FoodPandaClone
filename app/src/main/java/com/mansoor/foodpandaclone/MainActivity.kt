package com.mansoor.foodpandaclone

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mansoor.foodpandaclone.utils.MyUtils
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var drawer: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    var prof_name_tv: TextView? = null
    var prof_desg_tv: TextView? = null
    lateinit var circleImageView: CircleImageView
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar)
        MyUtils.setStatusBarColor(this)


        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        drawer = findViewById(R.id.drawer_layout)
        drawer.setScrimColor(Color.TRANSPARENT)
        //toggle = new ActionBarDrawerToggle(this,drawer)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = false
        val headerView = navigationView.getHeaderView(0)
        //  prof_country_tv = headerView.findViewById(R.id.prof_country_tv)
        prof_name_tv = headerView.findViewById(R.id.prof_name_tv)
        prof_desg_tv = headerView.findViewById(R.id.prof_desg_tv)
        circleImageView = headerView.findViewById(R.id.prof_iv)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_toggle, this.theme)
        drawable!!.setTint(getColor(R.color.white))
        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener { v ->
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {

            R.id.nav_home -> {
                drawer.closeDrawers()
            }

            R.id.nav_notification -> {
//                val intent = Intent(this, NotificationActivity::class.java)
//                startActivity(intent)
            }

        }
        return true
    }
}