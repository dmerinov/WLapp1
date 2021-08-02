package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.MonumentDomainModel
import kotlinx.android.synthetic.main.item_monument.view.*

class MonumentAdapter(
    private val items: MutableList<MonumentDomainModel> = mutableListOf(),
    private val onItemClicked: (MonumentDomainModel) -> Unit
) :
    RecyclerView.Adapter<MonumentAdapter.MonumentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_monument, parent, false)

        return MonumentHolder(view) {
            onItemClicked(items[it])
        }
    }

    override fun onBindViewHolder(holder: MonumentHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun replaceAll(newItems: List<MonumentDomainModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class MonumentHolder(
        view: View,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(monument: MonumentDomainModel) {
            itemView.monument.text =
                itemView.context.getString(
                    R.string.id_name_composition,
                    monument.id,
                    monument.title
                )
            monument.geocoordinates.also { itemView.coordinates.text = it }
        }

    }

}