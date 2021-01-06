package com.example.graduation_planner.screens.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module

class RecyclerAdapter(modules: MutableList<Module>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var modules: MutableList<Module> = modules

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var moduleCode: TextView

        init {
            itemImage = itemView.findViewById(R.id.ivIcon)
            moduleCode = itemView.findViewById(R.id.tvModuleCode)

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Clicked on ${modules[position].moduleCode}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleCode.text = modules[position].moduleCode
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    fun submitList(newData: List<Module>) {
        modules.clear()
        modules.addAll(newData)
        notifyDataSetChanged()
    }
}