package com.myboard.presentation.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.myboard.databinding.ItemContentBinding
import com.myboard.domain.model.Content
import com.myboard.presentation.ui.MainActivity

class ContentViewHolder(
    private val binding : ItemContentBinding,
    private val handler : MainActivity.Handler) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Content){
        binding.item = item
        binding.handler = handler
    }
}