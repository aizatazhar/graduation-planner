package com.example.graduation_planner.screens.modules

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module

class ModulesExpandableListViewAdapter(
    private val context: Context,
    private val groupNames: List<String>,
    private var map: HashMap<String, MutableList<Module>>,
    private var deleteModule: (module: Module) -> Unit
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return groupNames.size
    }

    override fun getChildrenCount(i: Int): Int {
        return map[groupNames[i]]?.size ?: 0
    }

    override fun getGroup(i: Int): Any {
        return groupNames[i]
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Module? {
        return map[groupNames[listPosition]]?.get(expandedListPosition)
    }

    override fun getGroupId(i: Int): Long {
        return i.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return false
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        view: View?,
        viewGroup: ViewGroup?
    ): View? {
        val v = view ?: View.inflate(context, R.layout.module_list_group_layout, null)

        v?.findViewById<TextView>(R.id.title)?.apply {
            text = groupNames[listPosition]
        }

        return v
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        view: View?,
        viewGroup: ViewGroup?
    ): View? {
        val v = view ?: View.inflate(context, R.layout.module_list_child_layout, null)

        val module = getChild(listPosition, expandedListPosition)

        v?.findViewById<TextView>(R.id.tvModuleCode)?.apply {
            text = module?.moduleCode ?: ""
        }

        v?.findViewById<TextView>(R.id.tvModuleTitle)?.apply {
            val title = module?.title ?: ""
            val mcs = module?.moduleCredit ?: ""
            text = "$title ($mcs MCs)"
        }

        v?.findViewById<ImageView>(R.id.ivClearButton)?.apply {
            setOnClickListener {
                module?.apply { deleteModule(this) }
            }
        }

        return v
    }

    fun setModuleMap(newData: HashMap<String, MutableList<Module>>) {
        map = newData
        notifyDataSetChanged()
    }
}
