package neomu.kotlin.app.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.NearFragment
import com.app.neomu_kotlin.app.club.fragment.NewFragment
import com.app.neomu_kotlin.app.club.fragment.PopularFragment
import com.app.neomu_kotlin.common.activity.BaseActivity
import com.app.neomu_kotlin.common.constanse.ConstCommon
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

open class ToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomToolbar()
        setPagerAdapterInViewPager()
        setContentView(R.layout.activity_main)
    }

    fun setCustomToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_menu_black_24dp))
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, draw_main, toolbar, 0, 0
        )
        draw_main.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    fun setPagerAdapterInViewPager() {
        vp_container.adapter = getFragmentPagerAdapter()
//        tab_main.setupWithViewPager(vp_container)
    }

    fun getFragmentPagerAdapter(): FragmentPagerAdapter {
        val fragmentPagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            val fragmentArray = arrayListOf<Fragment>(
                PopularFragment(), NewFragment(), NearFragment()
            )

            val fragmentName = arrayListOf<String>(
                ConstCommon.FRAGMENT_TAB_NAME_POPULAR,
                ConstCommon.FRAGMENT_TAB_NAME_NEW,
                ConstCommon.FRAGMENT_TAB_NAME_NEAR
            )

            override fun getItem(position: Int): Fragment {
                return fragmentArray[position]
            }

            override fun getCount(): Int {
                return fragmentArray.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragmentName[position]
            }

        }
        return fragmentPagerAdapter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_rightSearch -> {
//                startActivity<SearchActivity>()
                finish()
            }
        }
    }

    //todo 네비게이션 분리
    fun onNavigationClickListener() {

    }
}