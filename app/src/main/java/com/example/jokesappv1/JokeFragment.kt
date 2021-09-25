package com.example.jokesappv1

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var pickedItem : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pickedItem = it.getString("itemPicked")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJokePicked()
    }

    private fun getJokePicked() {
        val request = ApiHitInterface.getJokeCategory().getPickedJoke(pickedItem)
        request.enqueue(PickedJokeCallBack())
    }

    inner class PickedJokeCallBack : Callback<JokesCategory> {
        override fun onResponse(call: Call<JokesCategory>, response: Response<JokesCategory>) {
            Log.d("JokeFragment"  , response.toString())

            if(response.isSuccessful){
                val jJoke = response.body()
                jJoke?.let {
                    val jokeTitle = view?.findViewById<TextView>(R.id.JokeTitleTV)
                    jokeTitle?.text = pickedItem

                    val jokeIs = view?.findViewById<TextView>(R.id.jokeTV)
                    jokeIs?.text = it.value.toString()
                    val buttonCopy = view?.findViewById<Button>(R.id.copyBt)
                    buttonCopy?.setOnClickListener { a->
                        copyToClipBoard(it.value.toString())
                    }

                }
            }
        }

        override fun onFailure(call: Call<JokesCategory>, t: Throwable) {
            Log.d("JokeFragment" , "Error is : ${t.message}")
        }
    }

    private fun copyToClipBoard(JokeCopy: String) {
        val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted Text" , JokeCopy.replace(", ",""))
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context , "TextCopied"  , Toast.LENGTH_LONG).show()
    }


}