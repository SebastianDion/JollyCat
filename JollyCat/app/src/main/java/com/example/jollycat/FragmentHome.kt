

package com.example.jollycat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.jollycat.databinding.FragmentHomeBinding
import org.json.JSONArray
import org.json.JSONException

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var catAdapter: CatsAdapter
    private var catList = ArrayList<Cats>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.catsRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        catAdapter = CatsAdapter(requireContext(), listCats = catList)
        recyclerView.adapter = catAdapter

        fetchCatData()

        return binding.root
    }

    private fun fetchCatData() {
        val url = "https://api.npoint.io/3fa9a95557f89f097063"
        val requestQueue = Volley.newRequestQueue(context)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                Log.d("FragmentHome", "Response received: $response")
                catList.clear() // Clear the list before adding new items
                catList.addAll(parseJSON(response))
                catAdapter.notifyDataSetChanged()// Notify adapter of data change
                Global.listCats = catList
            },
            { error ->
                Log.e("FragmentHome", "Error: ${error.message}")
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    private fun parseJSON(jsonArray: JSONArray): ArrayList<Cats> {
        val catList = ArrayList<Cats>()
        try {
            for (i in 0 until jsonArray.length()) {
                val catObject = jsonArray.getJSONObject(i)
                val catID = catObject.getString("CatID")
                val catName = catObject.getString("CatName")
                val catImage = catObject.getString("CatImage")
                val catPrice = catObject.getInt("CatPrice")
                val catDescription = catObject.getString("CatDescription")

                Log.d("FragmentHome", "Parsed cat: $catName")
                catList.add(Cats(catID, catName, catDescription, catImage, catPrice))
            }
        } catch (e: JSONException) {
            Log.e("FragmentHome", "JSON Parsing error: ${e.message}")
            e.printStackTrace()
        }

        return catList
    }
}
