package poc.dynamictemplatepoc.views.funds.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import poc.dynamictemplatepoc.R
import poc.dynamictemplatepoc.model.DownInfoItem

class DownInfoAdapter (context: Context, infoItems: List<DownInfoItem>) : RecyclerView.Adapter<DownInfoAdapter.MyViewHolder>() {


    var context: Context? = null
    var infoItems: List<DownInfoItem>? = null

    init {
        this.context = context
        this.infoItems = infoItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // val view = LayoutInflater.inflate(R.layout.papular_events_item, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inforecyclerlayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        if(infoItems != null){
            p0.name!!.text = infoItems!!.get(p1).name
            p0.value!!.text = infoItems!!.get(p1).data.toString()
        }

    }

    override fun getItemCount(): Int {
        return infoItems!!.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView? = null
        var value: TextView? = null

        init {
            name = itemView.findViewById(R.id.name)
            value = itemView.findViewById(R.id.value)
        }

    }
}