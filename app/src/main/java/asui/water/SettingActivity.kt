package asui.water

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.calander.*
import kotlinx.android.synthetic.main.setting.*

class SettingActivity : AppCompatActivity() {

    var intensity = Resources.getSystem().getDisplayMetrics().density;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        setting_back.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        reset.setOnClickListener{
            reset_Variables()
        }
        increase.setOnClickListener{
            increase_Variables()

        }
    }
    fun increase_Variables(){
        asui.water.Variables.updateVars = true
        asui.water.Variables.water1 = asui.water.Variables.water1*1.1
        asui.water.Variables.water2 = asui.water.Variables.water2*1.1
        asui.water.Variables.water3 = asui.water.Variables.water3*1.1

    }

    fun reset_Variables(){
        asui.water.Variables.updateVars = true
        asui.water.Variables.water1 = 30.0
        asui.water.Variables.water2 = 200.0
        asui.water.Variables.water3 = 500.0

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
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
