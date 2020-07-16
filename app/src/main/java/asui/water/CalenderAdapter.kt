package asui.water

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.day.view.*

class CalenderAdapter : RecyclerView.Adapter<Holder>(){
    var listData = mutableListOf<CalenderData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
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
        itemView.day_cell_ll_background.day.text = listdata.date.toString()
    }
}