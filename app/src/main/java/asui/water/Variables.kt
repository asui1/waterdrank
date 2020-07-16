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
    fun get_water1():Double{
        return water1
    }
    fun set_water1(water: Double){
        water1 = water
    }
    fun get_water2():Double{
        return water2
    }
    fun set_water2(water: Double){
        water2 = water
    }
    fun get_water3():Double{
        return water3
    }
    fun set_water3(water: Double){
        water3 = water
    }
    fun add_water(water: Double):Double{
        waters += water
        return waters
    }

}