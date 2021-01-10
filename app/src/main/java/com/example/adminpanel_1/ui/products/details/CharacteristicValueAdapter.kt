package com.example.adminpanel_1.ui.products.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_1.databinding.CharacteristicValueItemBinding

class CharacteristicValueAdapter (internal val values:HashMap<String, String>):RecyclerView.Adapter<CharacteristicValueAdapter.CharacteristicValueViewHolder>() {
    class CharacteristicValueViewHolder(private val binding: CharacteristicValueItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(languageKey: String, characteristicValue: String){

            binding.apply {
                characteristicValueLanguage.setText(languageKey)
                characteristicValueEditText.setText(characteristicValue)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacteristicValueViewHolder {
        val binding = CharacteristicValueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacteristicValueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacteristicValueViewHolder, position: Int) {
        val currentKey = values.keys.elementAt(position)
        val currentValue = values[currentKey]


        if (currentValue != null){
            holder.bind(currentKey, currentValue)
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }
}