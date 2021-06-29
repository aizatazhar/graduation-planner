package com.example.graduation_planner.screens.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module

class SearchRecyclerAdapter(
    val context: Context,
    var onClickCallback: (module: Module) -> Unit,
    var modules: MutableList<Module>
) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moduleCode: TextView = itemView.findViewById(R.id.tvModuleCode)
        var title: TextView = itemView.findViewById(R.id.tvModuleTitle)
        var semesters: TextView = itemView.findViewById(R.id.tvModuleSemesters)
        var viewMoreButton: ImageButton = itemView.findViewById(R.id.viewMoreButton)

        init {
            viewMoreButton.setOnClickListener {
                val position: Int = adapterPosition
                onClickCallback(modules[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.module_list_child_layout, parent, false)

        val cardView: CardView = view.findViewById(R.id.cardView)
        cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow_700))

        val horizontalPaddingDp = 16
        val verticalPaddingDp = 6
        val scale: Float = context.resources.displayMetrics.density
        val horizontalPaddingPx = (horizontalPaddingDp * scale + 0.5f).toInt()
        val verticalPaddingPx = (verticalPaddingDp * scale + 0.5f).toInt()
        val layout: LinearLayout = view.findViewById(R.id.layout)
        layout.setPadding(horizontalPaddingPx, verticalPaddingPx, horizontalPaddingPx, verticalPaddingPx)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleCode.text = modules[position].moduleCode
        holder.title.text = modules[position].title
        holder.semesters.text = semestersToString(modules[position].semesters)
        holder.semesters.visibility = View.VISIBLE
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
            result += "Sem $semester • "
        }
        return result.substring(0, result.length - 2)
    }
}