//
//package com.example.jollycat
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.jollycat.databinding.CatsCardBinding
//
//class CatsAdapter(private val context: Context, val listCats: List<Cats>) : RecyclerView.Adapter<CatsAdapter.MainViewHolder>() {
//    inner class MainViewHolder(private val itemBinding: CatsCardBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        val catImage = itemBinding.catImageIV
//        val catName = itemBinding.catNameTV
//        val catPrice = itemBinding.catPriceTV
//        val catDesc = itemBinding.catDescTV
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//        return MainViewHolder(CatsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//    }
//
//    override fun getItemCount(): Int {
//        return listCats.size
//    }
//
//    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        val cat = listCats[position]
//        Glide.with(context)
//            .load(cat.catImage)
//            .into(holder.catImage)
//        holder.catName.text = cat.catName
//        holder.catDesc.text = cat.catDescription
//        holder.catPrice.text = cat.catPrice.toString()
//
//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, ActivityCatsDetailPage::class.java)
//            intent.putExtra("CatID", cat.catID)
//            it.context.startActivity(intent)
//        }
//    }
//}

package com.example.jollycat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jollycat.databinding.CatsCardBinding

class CatsAdapter(private val context: Context, val listCats: List<Cats>) : RecyclerView.Adapter<CatsAdapter.MainViewHolder>() {
    inner class MainViewHolder(private val itemBinding: CatsCardBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val catImage = itemBinding.catImageIV
        val catName = itemBinding.catNameTV
        val catPrice = itemBinding.catPriceTV
        val catDesc = itemBinding.catDescTV
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(CatsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listCats.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val cat = listCats[position]
        Glide.with(context)
            .load(cat.catImage)
            .into(holder.catImage)
        holder.catName.text = cat.catName
        holder.catDesc.text = cat.catDescription
        holder.catPrice.text = cat.catPrice.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ActivityCatsDetailPage::class.java)
            intent.putExtra("CatID", cat.catID)
            it.context.startActivity(intent)
        }
    }
}
