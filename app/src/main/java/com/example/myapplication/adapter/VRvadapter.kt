package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.model.room.Task
import android.widget.TextView


public class VRvadapter (val mContext: Context,val task:List<Task>) : RecyclerView.Adapter<VRvadapter.MyViewHolder>() {

    var clickWithTxt:ClickWithTxt? = null
    fun performClicke(clickWithTxt:ClickWithTxt){
        this.clickWithTxt=clickWithTxt
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
    val v = LayoutInflater.from(parent.context)
            .inflate(com.example.myapplication.R.layout.animal_list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_displaytxt.setText("${task.get(position).id} \n" +
                                    " ${task.get(position).task}\n " +
                                    "${task.get(position).desc}\n " +
                "${task.get(position).finishBy}")
        holder.itemView.setOnClickListener {
            Log.e("35","<<<onClick>>"+task.get(position).id)
            clickWithTxt!!.onClickWidTxt(position,"abcs")
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_displaytxt: TextView=view.findViewById(com.example.myapplication.R.id.tv_displaytxt)
    }
}