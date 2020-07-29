package asui.water

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.day.view.*
import java.util.*

class CalenderAdapter : RecyclerView.Adapter<Holder>(){
    var listData = mutableListOf<CalenderData>()
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        cal1.set(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), 1)
        var startday = cal1.get(Calendar.DAY_OF_WEEK)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int){
        val data = listData.get(position)
        holder.setListData(data)
    }

}


class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setListData(listdata: CalenderData){
        itemView.day_cell_ll_background.day.text = listdata.date
        when(listdata.cor){
            "red" -> itemView.day_cell_ll_background.day.setTextColor(Color.RED)
            "blue" -> itemView.day_cell_ll_background.day.setTextColor(Color.BLUE)
            "grey" -> itemView.day_cell_ll_background.day.setTextColor(Color.GRAY)
            else -> itemView.day_cell_ll_background.day.setTextColor(Color.BLACK)

        }
    }
}