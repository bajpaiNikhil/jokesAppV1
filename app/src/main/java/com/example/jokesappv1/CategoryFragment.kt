package com.example.jokesappv1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.HORIZONTAL
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.intellij.lang.annotations.JdkConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rView)
        recyclerView.layoutManager = GridLayoutManager(context ,2)
        getCategory()
    }

    private fun getCategory() {
        val request = ApiHitInterface.getJokeCategory().getJoke()
        Log.d("NameFragment" , "$request")
        request.enqueue(CategoryCallBack())
    }

    inner class CategoryCallBack : Callback<List<String>> {
        override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
            Log.d("NameFragment" , "${response.body()}")
            if(response.isSuccessful){
                val jCat = response.body()

                fun onItemSelected(s: String) {
                    Log.d("NameFragment" , s)
                    val bundle = bundleOf("itemPicked" to s)
                    findNavController().navigate(R.id.action_categoryFragment_to_jokeFragment , bundle)

                }
                jCat?.let {
                    recyclerView.adapter = JokeAdapter(jCat , ::onItemSelected)
                }
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            Log.d("NameFragment" , "${t.message}")
        }

    }

}