package com.example.adminpanel_1.ui.products.details

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_1.api.catalogExport.Characteristic
import com.example.adminpanel_1.api.catalogExport.CharacteristicNumber
import com.example.adminpanel_1.api.catalogExport.CharacteristicValueResponse
import com.example.adminpanel_1.databinding.CharacteristicItemBinding

class CharacteristicAdapter(private val characteristics: List<Characteristic>) :
    RecyclerView.Adapter<CharacteristicAdapter.CharacteristicViewHolder>() {


    class CharacteristicViewHolder(private val binding: CharacteristicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characteristic: Characteristic) {


            binding.apply {
                characteristicName.setText(characteristic.key)
                if (characteristic is CharacteristicValueResponse) {
                    val keys = characteristic.localizationValue
                    characteristicsValueList.adapter = CharacteristicValueAdapter(keys)
                }

                else if (characteristic is CharacteristicNumber) {
                    val characteristicNumber = characteristic as CharacteristicNumber
                    characteristicNumberValue.setText(characteristicNumber.number)
                    characteristicNumberValue.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacteristicViewHolder {
        val binding =
            CharacteristicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacteristicViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CharacteristicViewHolder,
        position: Int
    ) {
        val currentCharacteristic = characteristics.get(position)
        if (currentCharacteristic != null) {
            holder.bind(currentCharacteristic)
        }

    }


    override fun getItemCount(): Int {
        return characteristics.size
    }
}