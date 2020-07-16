package asui.water

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var total_water = 2000.0
    var water1 = 30.0
    var water2 = 200.0
    var mBackWait:Long = 0
    var cur_depth = 0.0
    var water3 = 500.0
    var waters = 0.0
    var intensity = Resources.getSystem().getDisplayMetrics().density;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        water_amount_button1.setOnClickListener{
            waters += water1
            Toast.makeText(this, waters.toString()+"mL를 마셨습니다!", Toast.LENGTH_SHORT).show()
            water_amount.setText(waters.toInt().toString() + "mL")
            cur_depth = (waters/total_water) * 650.0
            if(waters > 2000){
                hide_drop.layoutParams.height = 0
            }
            else{
                hide_drop.layoutParams.height = ((650 - cur_depth)*intensity).toInt()
            }

        }
        water_amount_button2.setOnClickListener{
            waters += water2
            water_amount.setText(waters.toInt().toString() + "mL")
            cur_depth = (waters/total_water) * 650.0
            if(waters > 2000){
                hide_drop.layoutParams.height = 0
            }
            else{
                hide_drop.layoutParams.height = ((650 - cur_depth)*intensity).toInt()
            }

        }
        water_amount_button3.setOnClickListener{
            waters += water3
            water_amount.setText(waters.toInt().toString() + "mL")
            cur_depth = (waters/total_water) * 650.0
            if(waters > 2000){
                hide_drop.layoutParams.height = 0
            }
            else{
                hide_drop.layoutParams.height = ((650 - cur_depth)*intensity).toInt()
            }

        }

        calender.setOnClickListener{
            startActivity(Intent(this, CalenderActivity::class.java))
            finish()
        }

        setting.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
            finish()
        }


    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            finish() //액티비티 종료
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
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
