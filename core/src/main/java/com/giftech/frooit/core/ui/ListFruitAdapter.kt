package com.giftech.frooit.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.core.databinding.ItemFruitBinding
import com.giftech.frooit.core.domain.model.Fruit

class ListFruitAdapter: RecyclerView.Adapter<ListFruitAdapter.ListFruitViewHolder>() {

    private var listFruit = ArrayList<Fruit>()
    var onItemClick: ((Fruit) -> Unit)? = null

    fun setList(list:List<Fruit>){
        listFruit.clear()
        listFruit.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFruitViewHolder {
        val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListFruitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFruitViewHolder, position: Int) {
        val fruit = listFruit[position]
        holder.bind(fruit)
    }

    override fun getItemCount(): Int = listFruit.size

    inner class ListFruitViewHolder(val binding: ItemFruitBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(fruit: Fruit){
            with(binding){
                tvName.text = fruit.name
                tvFamily.text = fruit.family
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listFruit[adapterPosition])
            }
        }
    }

}