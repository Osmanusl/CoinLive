package com.osmanuslu.myapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osmanuslu.myapplication.databinding.RowLayout1Binding
import com.osmanuslu.myapplication.databinding.RowLayoutBinding
import com.osmanuslu.myapplication.model.NewsModel
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class RecyclerViewAdapterNews(
    private val newsList: ArrayList<NewsModel>,
) : RecyclerView.Adapter<RecyclerViewAdapterNews.RowHolder>() {

    class RowHolder(val binding: RowLayout1Binding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowLayout1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.binding.captionText.text = newsList[position].caption
        holder.binding.descriptionText.text = newsList[position].description
        holder.binding.dateText.text = newsList[position].date


         val logoUrl = newsList[position].image

          Picasso.get().load(logoUrl).into(holder.binding.newsImageView)

    }

    fun updateNewsList(data1: List<NewsModel>) {  //listede guncelleme olursa cagirilacak fun
        newsList.clear()
        newsList.addAll(data1)
        notifyDataSetChanged()
    }


}