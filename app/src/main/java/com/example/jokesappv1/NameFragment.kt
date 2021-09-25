package com.example.jokesappv1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ensureActive
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
            //getCategory()
            findNavController().navigate(R.id.action_nameFragment_to_categoryFragment)
        }

    }

//    private fun getCategory() {
//        val request = ApiHitInterface.getJokeCategory().getJoke()
//        Log.d("NameFragment" , "$request")
//        request.enqueue(CategoryCallBack())
//    }
//
//    inner class CategoryCallBack : Callback<List<String>>{
//        override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
//            Log.d("NameFragment" , "${response.body()}")
//            if(response.isSuccessful){
//                val jCat = response.body()
//            }
//        }
//
//        override fun onFailure(call: Call<List<String>>, t: Throwable) {
//            Log.d("NameFragment" , "${t.message}")
//        }
//
//    }

}