package com.ukaka.farmerregistration.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ukaka.farmerregistration.data.FarmerEntity
import com.ukaka.farmerregistration.databinding.ItemFarmerBinding

class RegisteredFarmersAdapter : ListAdapter<FarmerEntity, RegisteredFarmersAdapter.RegisteredFarmersVH>(object :
    DiffUtil.ItemCallback<FarmerEntity>() {

    override fun areItemsTheSame(oldItem: FarmerEntity, newItem: FarmerEntity): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: FarmerEntity, newItem: FarmerEntity): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisteredFarmersVH {
        val binding = ItemFarmerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RegisteredFarmersVH(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: RegisteredFarmersVH, position: Int) {
        val itemAtPosition = currentList[position]
        holder.bind(itemAtPosition)
    }

    inner class RegisteredFarmersVH(
        private val binding: ItemFarmerBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(farmer: FarmerEntity) {

            with(binding) {
                // Bind data
                fullNameTV.text = farmer.fullName
                cropTypeTV.text = farmer.cropType
            }
        }
    }
}