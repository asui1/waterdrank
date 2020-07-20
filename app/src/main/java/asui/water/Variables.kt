package asui.water

import android.app.Application
import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.security.AccessController.getContext

class Variables: Application() {
    companion object {
        var total_water = 2000.0
        var water1 = 30.0
        var water2 = 200.0
        var cur_depth = 0.0
        var water3 = 500.0
        var waters = 0.0
        var adds = mutableListOf(0.0)
        var updateVars = false
    }

}