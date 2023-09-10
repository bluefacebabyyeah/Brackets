package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter (
): ListAdapter<ResultItem, CustomRecyclerAdapter.MyViewHolder>(
    object: DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem == newItem
        }
    }
){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val inputTextView: TextView = itemView.findViewById(R.id.textViewInput)
        val outputTextView: TextView = itemView.findViewById(R.id.textViewOutput)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.inputTextView.text = "${item.input} "
        holder.outputTextView.text = item.output.toString()
    }
}