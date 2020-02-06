package com.adeel.githubrepos.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adeel.githubrepos.R
import com.adeel.githubrepos.databinding.ItemFilterBinding
import com.adeel.githubrepos.model.Filter

class FilterAdapter(
    private val onClick: View.OnClickListener,
    private val filterList: ArrayList<Filter>
) :
    RecyclerView.Adapter<FilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val contentView = DataBindingUtil.inflate<ItemFilterBinding>(
            layoutInflater,
            R.layout.item_filter,
            parent,
            false
        )
        return FilterViewHolder(contentView)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.view.filter = filterList[holder.adapterPosition]

        holder.itemView.tag = filterList[holder.adapterPosition]
        holder.itemView.setOnClickListener(onClick)
    }

    fun updateFilter(updatedList: List<Filter>) {
        filterList.clear()
        filterList.addAll(updatedList)
        notifyDataSetChanged()
    }

}

class FilterViewHolder(val view: ItemFilterBinding) : RecyclerView.ViewHolder(view.root)