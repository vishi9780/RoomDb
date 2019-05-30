package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class F1 : Fragment() {
    lateinit var tv:TextView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e(TAG, "onAttach: ")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e(TAG, "onCreateView: ")
        val view = inflater!!.inflate(R.layout.activity_main,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<TextView>(R.id.tv).setText("Fragment")
        tv=view.findViewById(R.id.tv)
        tv.setText("Fragment1")
//        tv.setOnClickListener {
            Toast.makeText(activity,"34",Toast.LENGTH_SHORT)
            val newFragment = F2()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.add(R.id.framen, newFragment)
//        transaction.addToBackStack(null)
            transaction.commit()
//        }
        Log.e(TAG, "onViewCreated: ")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG, "onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach: ")
    }

    companion object {
        val TAG = F1::class.java!!.getSimpleName()
    }
}

