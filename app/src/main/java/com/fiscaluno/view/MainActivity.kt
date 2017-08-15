package com.fiscaluno.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.fiscaluno.R
import com.fiscaluno.contracts.MainContract
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.MainPresenter
import com.fiscaluno.view.adapter.TopInstitutionsAdapter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), MainContract.View, NavigationView.OnNavigationItemSelectedListener{

    private var presenter: MainContract.Presenter? =  null
    private var topInstitutionsAdapter: TopInstitutionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        presenter = MainPresenter()
        presenter?.bindView(this)
        //TODO: Call in different threads?
        presenter?.loadTopInstitutions()
        //TODO: Fix it!
        presenter?.loadUserInstitutionInfo(getSharedPreferences("userpref", Context.MODE_PRIVATE))
    }

    override fun showTopInstitutions(institutions: List<Institution>) {
        topInstitutionsAdapter = TopInstitutionsAdapter(ArrayList(institutions))
        topInstitutionsRv.adapter = topInstitutionsAdapter

    }

    override fun showUserInstitutionInfo(userInstitution: Institution) {
        institutionNameTv.text = userInstitution.name
        ratedBy.text = userInstitution.reviewdBy.toString()
        average.text = userInstitution.averageRating.toString()
        institutionRating.rating = userInstitution.averageRating!!
    }

    fun fbReviewClick(view: View) {
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }

    fun fbSearchInstitutionClick(view: View) {
        //TODO: Go to search passing type as institution
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }

    fun fbSearchCourseClick(view: View) {
        //TODO: Go to search passing type as course
        val intent = Intent(this, RatingActivity::class.java)
        startActivity(intent)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var intent: Intent? = null
        when (item.itemId) {

            R.id.nav_profile -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_review -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_info -> {
                intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }

        }
        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
