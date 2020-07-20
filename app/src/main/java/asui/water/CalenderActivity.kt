package asui.water

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.calander.*
import java.util.*

class CalenderActivity : AppCompatActivity() {

    var intensity = Resources.getSystem().getDisplayMetrics().density;
    var cal = Calendar.getInstance()
    var mon = cal.get(Calendar.MONTH)
    var year = cal.get(Calendar.YEAR)
    var data:MutableList<CalenderData> = setData()
    init{
        cal.time = Date()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calander)
        var adapter = CalenderAdapter()
        adapter.listData = data
        calender_body.adapter = adapter
        calender_body.layoutManager = GridLayoutManager(this, 7)
        calender_body.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        calender_body.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        calender_back.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    fun setData(): MutableList<CalenderData>{
        var data:MutableList<CalenderData> = mutableListOf()
        data.add(CalenderData("일"))
        data.add(CalenderData("월"))
        data.add(CalenderData("화"))
        data.add(CalenderData("수"))
        data.add(CalenderData("목"))
        data.add(CalenderData("금"))
        data.add(CalenderData("토"))
        return data
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}
