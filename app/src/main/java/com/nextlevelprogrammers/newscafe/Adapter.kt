package com.nextlevelprogrammers.newscafe

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val listner:itemClick): RecyclerView.Adapter<Viewholder>() {
    val items:ArrayList<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.news_items,parent,false)
        val viewholder=Viewholder(view)
        view.setOnClickListener {
            listner.onClick(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val current=items[position]
        Glide.with(holder.itemView.context).load(current.urlToImage).into(holder.image)
        holder.title.text=current.title
        holder.author.text=current.author
    }
    fun update(update:ArrayList<Data>){
        items.clear()
        items.addAll(update)
        notifyDataSetChanged()
    }
}

class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image=itemView.findViewById<ImageView>(R.id.image)
    val title=itemView.findViewById<TextView>(R.id.title)
    val author=itemView.findViewById<TextView>(R.id.author)
    val bar=itemView.findViewById<ProgressBar>(R.id.bar)
}

interface itemClick{
    fun onClick(items:Data)
}