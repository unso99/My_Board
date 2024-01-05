package com.myboard.presentation.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.myboard.databinding.ItemContentBinding
import com.myboard.domain.model.Content

class ContentViewHolder(private val binding : ItemContentBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Content){
        binding.item = item
    }
}