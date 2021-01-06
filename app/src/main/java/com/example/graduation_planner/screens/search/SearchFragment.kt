package com.example.graduation_planner.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_planner.R
import com.example.graduation_planner.models.Module
import com.example.graduation_planner.models.SampleModules
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var modules: List<Module>
    private lateinit var displayList: List<Module>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root: View = inflater.inflate(R.layout.search_fragment, container, false)

        modules = readJsonFromAsset("moduleInfoSample.json")
        displayList = readJsonFromAsset("moduleInfoSample.json")

        recyclerView = root.findViewById(R.id.rvModules)
        recyclerAdapter = RecyclerAdapter(displayList)
        recyclerView.adapter = recyclerAdapter

        return root
    }

    private fun fetchSampleData(): MutableList<Module> {
        val result: MutableList<Module> = mutableListOf()
        result.addAll(SampleModules.getSampleModules())
        return result
    }

    private fun fetchJsonFromApi() {
        val url = "https://api.nusmods.com/v2/2020-2021/modules/CS1231S.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to get response")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val module: Module = gson.fromJson(body, Module::class.java)
                val x = (module.semesterData)

                val y = readJsonFromAsset("moduleInfoSample.json")
            }
        })
    }

    private fun readJsonFromAsset(fileName: String) : List<Module> {
        val jsonString: String? = activity?.applicationContext
                ?.assets
                ?.open(fileName)
                ?.bufferedReader().use { it?.readText() }
        val gson = GsonBuilder().create()

        return gson.fromJson(jsonString, Array<Module>::class.java).toList()
    }

}