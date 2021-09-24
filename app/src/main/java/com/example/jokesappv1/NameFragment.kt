package com.example.jokesappv1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NameFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryButton  = view.findViewById<Button>(R.id.jokeCategoryBt)

        categoryButton.setOnClickListener {
            Log.d("NameFragment"  , " Button is clicked")
            getCategory()
        }

    }

    private fun getCategory() {
        val request = ApiHitInterface.getJokeCategory().getJoke()
        Log.d("NameFragment" , "$request")
        request.enqueue(CategoryCallBack())

    }

    inner class CategoryCallBack : Callback<JokesCategory>{
        override fun onResponse(call: Call<JokesCategory>, response: Response<JokesCategory>) {
            Log.d("NameFragment" , "$response")
        }

        override fun onFailure(call: Call<JokesCategory>, t: Throwable) {
            Log.d("NameFragment" , "${t.message}")
        }

    }

}