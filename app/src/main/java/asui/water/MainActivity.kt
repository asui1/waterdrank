package asui.water

import android.content.Context
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
import asui.water.Variables.Companion
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    var mBackWait:Long = 0
    var intensity = Resources.getSystem().getDisplayMetrics().density;
    var water1 = asui.water.Variables.water1
    var water2 = asui.water.Variables.water2
    var water3 = asui.water.Variables.water3
    var total_water = asui.water.Variables.total_water
    var waters = asui.water.Variables.waters
    var cur_depth = asui.water.Variables.cur_depth
    var onlyDate: LocalDate = LocalDate.now()
    override fun onCreate(savedInstanceState: Bundle?) {
        val pref = this.getPreferences(0)
        val editor = pref.edit()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        waters = pref.getInt(onlyDate.toString(), 0).toDouble()
        if(asui.water.Variables.updateVars == true){
            editor.putInt(getString(R.string.water1), water1.toInt()).apply()
            editor.putInt(getString(R.string.water2), water2.toInt()).apply()
            editor.putInt(getString(R.string.water3), water3.toInt()).apply()
            editor.putInt(getString(R.string.total_water), total_water.toInt()).apply()
            asui.water.Variables.updateVars=false
            editor.commit()
        }
        else{
            water1 = pref.getInt(getString(R.string.water1), 30).toDouble()
            water2 = pref.getInt(getString(R.string.water2), 200).toDouble()
            water3 = pref.getInt(getString(R.string.water3), 500).toDouble()
            total_water = pref.getInt(getString(R.string.total_water), 2000).toDouble()
        }
        water_amount_text1.setText(water1.toInt().toString() + "mL")
        water_amount_text2.setText(water2.toInt().toString() + "mL")
        water_amount_text3.setText(water3.toInt().toString() + "mL")
        update_waters()
//        Toast.makeText(this, onlyDate.toString(), Toast.LENGTH_SHORT).show()

        water_amount_button1.setOnClickListener{
            asui.water.Variables.waters += water1
            waters = asui.water.Variables.waters
            asui.water.Variables.adds.add(water1)
            Toast.makeText(this, water1.toString()+"mL를 마셨습니다!", Toast.LENGTH_SHORT).show()
            update_waters()
            editor.putInt(onlyDate.toString(), waters.toInt()).apply()

        }
        water_amount_button2.setOnClickListener{
            asui.water.Variables.waters += water2
            waters = asui.water.Variables.waters
            asui.water.Variables.adds.add(water2)
            Toast.makeText(this, water2.toString()+"mL를 마셨습니다!", Toast.LENGTH_SHORT).show()
            update_waters()
            editor.putInt(onlyDate.toString(), waters.toInt()).apply()

        }
        water_amount_button3.setOnClickListener{
            asui.water.Variables.waters += water3
            asui.water.Variables.adds.add(water3)
            waters = asui.water.Variables.waters
            Toast.makeText(this, water3.toString()+"mL를 마셨습니다!", Toast.LENGTH_SHORT).show()
            update_waters()
            editor.putInt(onlyDate.toString(), waters.toInt()).apply()
        }

        undo.setOnClickListener{
            var last = asui.water.Variables.adds.get(asui.water.Variables.adds.size-1)
            if(last != 0.0){
                Toast.makeText(this, last.toString()+"mL를 취소했습니다!", Toast.LENGTH_SHORT).show()
                asui.water.Variables.waters -= last
                asui.water.Variables.adds.removeAt(asui.water.Variables.adds.size-1)
                waters = asui.water.Variables.waters
                update_waters()
                editor.putInt(onlyDate.toString(), waters.toInt()).apply()

            }
            else{
                Toast.makeText(this, "더는 취소할 수 없습니다!", Toast.LENGTH_SHORT).show()

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

    fun update_waters(){
        water_amount.setText(waters.toInt().toString() + "mL")
        cur_depth = (waters/total_water) * 650.0
        asui.water.Variables.cur_depth = cur_depth
        if(waters > total_water){
            hide_drop.layoutParams.height = 0
        }
        else{
            hide_drop.layoutParams.height = ((650 - cur_depth)*intensity).toInt()
        }

    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
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
