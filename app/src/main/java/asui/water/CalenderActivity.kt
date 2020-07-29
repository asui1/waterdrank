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
        cal.set(Calendar.DATE, 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calander)
        calender_title.setText(cal.get(Calendar.YEAR).toString()+"년 "+(cal.get(Calendar.MONTH)+1).toString()+"월")
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
        calender_prev.setOnClickListener{
            cal.add(Calendar.MONTH, -1)
            adapter.listData = setData()
            calender_title.setText(cal.get(Calendar.YEAR).toString()+"/"+(cal.get(Calendar.MONTH)+1).toString())
            calender_body.adapter = adapter

        }
        calender_next.setOnClickListener{
            cal.add(Calendar.MONTH, 1)
            adapter.listData = setData()
            calender_title.setText(cal.get(Calendar.YEAR).toString()+"/"+(cal.get(Calendar.MONTH)+1).toString())
            calender_body.adapter = adapter
        }


    }


    fun setData(): MutableList<CalenderData>{
        var data:MutableList<CalenderData> = mutableListOf()

        data.add(CalenderData("일", "red"))
        data.add(CalenderData("월", "black"))
        data.add(CalenderData("화", "black"))
        data.add(CalenderData("수", "black"))
        data.add(CalenderData("목", "black"))
        data.add(CalenderData("금", "black"))
        data.add(CalenderData("토", "blue"))
        val end_month =cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        var bef = cal.get(Calendar.DAY_OF_WEEK)-1
        var temp_cal = cal.clone() as Calendar
        temp_cal.add(Calendar.MONTH, -1)
        var temp_end_month = temp_cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        var count = 0
        for(i in temp_end_month-bef+1 .. temp_end_month){
            data.add(CalenderData(i.toString(), "grey"))
            count += 1
        }
        for(i in 1.. end_month){
            when(count % 7){
                0 -> data.add(CalenderData(i.toString(), "red"))
                6 -> data.add(CalenderData(i.toString(), "blue"))
                else -> data.add(CalenderData(i.toString(), "black"))

            }
            count += 1
        }
        for(i in 1 .. 42-end_month-bef){
            data.add(CalenderData(i.toString(), "grey"))
            count += 1
        }
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
