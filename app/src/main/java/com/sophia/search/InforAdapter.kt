package com.sophia.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sophia.search.Room.Infor
import com.sophia.search.databinding.ItemBinding

class InforAdapter(private val listener: ItemListener) : ListAdapter<Infor, InforViewHolder>(

    object : DiffUtil.ItemCallback<Infor>() {
        override fun areItemsTheSame(oldItem: Infor, newItem: Infor): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Infor, newItem: Infor): Boolean =
            oldItem.name == newItem.name && oldItem.phnumber == newItem.phnumber

    }

) {
    interface ItemListener {
//        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InforViewHolder =
        InforViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: InforViewHolder, position: Int) {
        holder.updateView(getItem(position))
    }
}

class InforViewHolder(
    private val binding: ItemBinding,
    listener: InforAdapter.ItemListener
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.container.let {
//            it.setOnClickListener {
//                if (adapterPosition != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(adapterPosition)
//                }
//            }
            it.setOnLongClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(adapterPosition)
                }
                return@setOnLongClickListener true
            }
        }
    }

    fun updateView(infor: Infor) {
        binding.run {
            tvName.text = infor.name
            tvPhnumber.text = infor.phnumber
        }
    }

}
