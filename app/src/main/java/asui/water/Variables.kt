package asui.water

import android.app.Application

class Variables: Application() {
    companion object {
        var total_water = 2000.0
        var water1 = 30.0
        var water2 = 200.0
        var cur_depth = 0.0
        var water3 = 500.0
        var waters = 0.0
        var adds = mutableListOf(0.0)
    }

}