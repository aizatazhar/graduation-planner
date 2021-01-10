package com.example.graduation_planner.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module

class HomeRecyclerAdapter(var deleteModuleCallback: (module: Module) -> Unit, var modules: List<Module>) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCode: TextView = itemView.findViewById(R.id.tvModuleCode)
        var title: TextView = itemView.findViewById(R.id.tvModuleTitle)
        var iconButton: ImageView = itemView.findViewById(R.id.ivClearButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.module_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerAdapter.ViewHolder, position: Int) {
        holder.moduleCode.text = modules[position].moduleCode
        holder.title.text = "${modules[position].title} (${modules[position].moduleCredit} MCs)"
        holder.iconButton.setOnClickListener {
            deleteModuleCallback(modules[position])
        }
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    fun submitList(newData: List<Module>) {
        modules = newData
        notifyDataSetChanged()
    }
}