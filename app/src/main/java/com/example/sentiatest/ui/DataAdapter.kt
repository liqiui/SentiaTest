package com.example.sentiatest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sentiatest.data.Data
import com.example.sentiatest.databinding.GridViewItemBinding

class DataAdapter(private val onClickListener: OnClickListener) : ListAdapter<Data, DataAdapter.DataViewHolder>(DiffCallback) {
    class OnClickListener(val clickListener: (data: Data) -> Unit) {
        fun onClick( data: Data) = clickListener( data)
    }

    class DataViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder( GridViewItemBinding.inflate( LayoutInflater.from( parent.context)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }
        holder.bind(data)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}
