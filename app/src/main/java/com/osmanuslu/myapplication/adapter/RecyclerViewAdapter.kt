package com.osmanuslu.myapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.osmanuslu.myapplication.databinding.RowLayoutBinding
import com.osmanuslu.myapplication.model.CryptoModel
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    class RowHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        val customDailyText = holder.binding.dailyText

        holder.binding.subnameText.text = cryptoList[position].subname
        holder.binding.nameText.text = cryptoList[position].name
        val decimal6 = BigDecimal(cryptoList[position].price).setScale(6, RoundingMode.HALF_EVEN)
        val decimal2 = BigDecimal(cryptoList[position].daily).setScale(2, RoundingMode.HALF_EVEN)
        holder.binding.priceText.text = decimal6.toString()
        customDailyText.text = "%" + decimal2.toString()

        if (decimal2 >= BigDecimal(0)) {
            customDailyText.setTextColor(Color.parseColor("#16927C"))
        } else {
            customDailyText.setTextColor(Color.parseColor("#B90E0A"))
        }

        val logoUrl = cryptoList[position].logo

        Picasso.get().load(logoUrl).into(holder.binding.imageview)

    }

    fun updateModelList(data: List<CryptoModel>) {  //listede guncelleme olursa cagirilacak fun
        cryptoList.clear()
        cryptoList.addAll(data)
        notifyDataSetChanged()
    }


}