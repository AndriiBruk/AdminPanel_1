package com.example.adminpanel_1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_1.R
import com.example.adminpanel_1.databinding.LoadStateFooterViewItemBinding

class OverviewLoadStateViewHolder(
    private val binding: LoadStateFooterViewItemBinding,
    retry: () -> Unit
):RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }


    fun bind(loadState: LoadState){
        if (loadState is LoadState.Error){
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState !is LoadState.Loading
            errorMsg.isVisible = loadState !is LoadState.Loading
        }
    }
    companion object{

        fun create(parent: ViewGroup, retry: () -> Unit): OverviewLoadStateViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_footer_view_item,parent,false)
            val binding = LoadStateFooterViewItemBinding.bind(view)
            return OverviewLoadStateViewHolder(binding, retry)
        }
    }

}