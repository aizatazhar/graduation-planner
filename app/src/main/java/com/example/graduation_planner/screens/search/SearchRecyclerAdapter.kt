package com.example.graduation_planner.screens.search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module
import com.google.android.material.snackbar.Snackbar


class SearchRecyclerAdapter(
        var addModuleCallback: (module: Module) -> Unit,
        var modules: MutableList<Module>) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCode: TextView = itemView.findViewById(R.id.tvModuleCode)
        var title: TextView = itemView.findViewById(R.id.tvModuleTitle)
        var semesters: TextView = itemView.findViewById(R.id.tvModuleSemesters)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                addModuleCallback(modules[position])

                val snackBar = Snackbar.make(it, "Added ${modules[position].moduleCode}",
                        Snackbar.LENGTH_LONG)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.BLACK)
                val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                snackBar.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleCode.text = modules[position].moduleCode
        holder.title.text = modules[position].title
        holder.semesters.text = semestersToString(modules[position].semesters)
    }

    override fun getItemCount(): Int {
        return modules.size
    }

    fun submitList(newData: List<Module>) {
        modules.clear()
        modules.addAll(newData)
        notifyDataSetChanged()
    }

    private fun semestersToString(semesters: List<Int>): String {
        var result = ""
        for (semester in semesters) {
            result += "Sem $semester | "
        }
        return result.substring(0, result.length - 2)
    }
}