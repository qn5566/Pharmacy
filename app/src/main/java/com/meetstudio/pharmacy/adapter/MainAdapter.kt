package com.meetstudio.pharmacy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetstudio.pharmacy.data.Feature
import com.meetstudio.pharmacy.databinding.ItemViewBinding

class MainAdapter(private val itemClickListener: IItemClickListener) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    var pharmacyList: List<Feature> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemViewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemViewBinding.tvName.text = pharmacyList[position].properties.name

        holder.itemViewBinding.tvAdultAmount.text = pharmacyList[position].properties.mask_adult.toString()
        holder.itemViewBinding.tvChildAmount.text = pharmacyList[position].properties.mask_child.toString()

        // 設定
        holder.itemViewBinding.layoutItem.setOnClickListener {
            itemClickListener.onItemClickListener(pharmacyList[position])
        }
    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }

    class MyViewHolder(val itemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    interface IItemClickListener {
        fun onItemClickListener(data: Feature)
    }
}